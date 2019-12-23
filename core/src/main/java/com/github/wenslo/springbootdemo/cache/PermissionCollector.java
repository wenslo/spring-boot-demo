package com.github.wenslo.springbootdemo.cache;

import static com.github.wenslo.springbootdemo.permissions.Permission.SEPARATOR;

import com.github.wenslo.springbootdemo.model.system.Permission;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月10日 上午11:18
 * @description 权限收集器
 */
@Component
public class PermissionCollector implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(PermissionCollector.class);
    public static final List<Permission> permissionList = Lists.newArrayList();
    public static final Set<String> permissionSet = Sets.newHashSet();

    @SuppressWarnings("unchecked")
    @Override
    public void run(String... args) throws Exception {
        logger.debug("-------------------------------------------permission collect preparing");
        Reflections reflections = new Reflections("com.github.wenslo.springbootdemo.permissions");
        Set<Class<? extends com.github.wenslo.springbootdemo.permissions.Permission>> types = reflections
            .getSubTypesOf(com.github.wenslo.springbootdemo.permissions.Permission.class);
        for (Class<? extends com.github.wenslo.springbootdemo.permissions.Permission> it : types) {
            Method valuesMethod = it.getMethod("values");
            Method describeMethod = it.getMethod("getDescribe");
            Method groupMethod = it.getMethod("getGroup");
            Method groupDescribeMethod = it.getMethod("getGroupDescribe");

            Enum<? extends com.github.wenslo.springbootdemo.permissions.Permission>[] result = (Enum<? extends com.github.wenslo.springbootdemo.permissions.Permission>[]) valuesMethod
                .invoke(it, new Object[]{});
            List<Permission> list = Lists.newArrayList();
            for (Enum<? extends com.github.wenslo.springbootdemo.permissions.Permission> anEnum : result) {
                String name = anEnum.name();
                String describe = (String) describeMethod.invoke(anEnum, new Object[]{});
                String groupName = (String) groupMethod.invoke(anEnum, new Object[]{});
                String groupDescribe = (String) groupDescribeMethod.invoke(anEnum, new Object[]{});
                list.add(new Permission(name, describe).builderParent(groupName, groupDescribe));
                permissionSet.add(StringUtils.join(groupName, SEPARATOR, name.toLowerCase()));
            }
            Map<String, List<Permission>> permissionCollect = list.stream().collect(Collectors.groupingBy(Permission::getParentAction));
            permissionCollect.forEach((k, v) -> {
                permissionList.add(new Permission(k, v.stream().findAny().get().getParentDescribe()).buildActions(v));
            });
            logger.debug("permissionSet  is {}", permissionSet);
            logger.debug("permissionsCollect  is {}", permissionList);

        }
        logger.debug("-------------------------------------------permission collect is end");
    }
}
