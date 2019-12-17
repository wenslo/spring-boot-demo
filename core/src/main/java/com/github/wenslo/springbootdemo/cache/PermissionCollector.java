package com.github.wenslo.springbootdemo.cache;

import com.github.wenslo.fluent.core.domain.SimpleEnum;
import com.github.wenslo.springbootdemo.enums.BaseEnum;
import com.github.wenslo.springbootdemo.model.system.Permission;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public static final Map<String, List<Permission>> permissionMap = Maps.newHashMap();
    public static final Set<String> permissionSet = Sets.newHashSet();
    @Autowired
    private EventBus eventBus;


    private void putPermissionIfPresent(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Map<String, List<Permission>> map = Arrays.stream(fields)
            .filter(s -> Objects.nonNull(s.getAnnotation(com.github.wenslo.springbootdemo.annotation.permission.Permission.class)))
            .map(s -> {
                com.github.wenslo.springbootdemo.annotation.permission.Permission annotation = s
                    .getAnnotation(com.github.wenslo.springbootdemo.annotation.permission.Permission.class);
                try {
                    String name = (String) s.get(clazz);
                    logger.trace("get name is {}", name);
                    permissionSet.add(name);
                    return new Permission(annotation.value(), name, annotation.group());
                } catch (IllegalAccessException e) {
                    logger.error("convert permissionMap label is error", e);
                    return null;
                }
            }).collect(Collectors.toList()).stream().collect(Collectors.groupingBy(Permission::getGroup));
        permissionMap.putAll(map);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.debug("------------------permission collect preparing-------------------------");
        Reflections reflections = new Reflections("com.github.wenslo.springbootdemo.permissions");
        Set<Class<? extends com.github.wenslo.springbootdemo.permissions.Permission>> types = reflections
            .getSubTypesOf(com.github.wenslo.springbootdemo.permissions.Permission.class);
        for (Class<? extends com.github.wenslo.springbootdemo.permissions.Permission> it : types) {
            Method valuesMethod = it.getMethod("values");
            Method labelMethod = it.getMethod("getDescribe");

            Enum<? extends BaseEnum>[] result = (Enum<? extends BaseEnum>[]) valuesMethod.invoke(it, new Object[]{});
            List<SimpleEnum> list = Lists.newArrayList();
            for (Enum<? extends BaseEnum> anEnum : result) {
                list.add(new SimpleEnum(anEnum.ordinal(), anEnum.name(), (String) labelMethod.invoke(anEnum, new Object[]{})));
            }
            enums.put(it.getSimpleName(), list);
            logger.trace("enums is {}", enums);
        }
    }
}
