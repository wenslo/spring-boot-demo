package com.github.wenslo.springbootdemo.controller;

import static com.github.wenslo.springbootdemo.permissions.Permission.SEPARATOR;

import com.github.wenslo.fluent.core.domain.Response;
import com.github.wenslo.fluent.security.SecurityUtil;
import com.github.wenslo.springbootdemo.cache.EnumCollector;
import com.github.wenslo.springbootdemo.cache.PermissionCollector;
import com.github.wenslo.springbootdemo.model.system.Permission;
import com.github.wenslo.springbootdemo.model.system.User;
import com.github.wenslo.springbootdemo.permissions.AdminPermission;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年11月29日 下午5:52
 * @description
 */
@RestController
public class LoginRegController {

    private static final Logger logger = LoggerFactory.getLogger(LoginRegController.class);
    @Autowired
    private EnumCollector enumCollector;

    @RequestMapping("/login_page")
    public Response loginPage() {
        return Response.UNAUTHORIZED;
    }

    @RequestMapping("/me")
    public Response me() {
        Map<String, Object> map = Maps.newHashMap();
        User user = (User) SecurityUtil.getLoginUser();
        user.setPassword(null);
        map.put("user", user);
        List<String> userPermissions = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        boolean isAdministrator = userPermissions.stream().anyMatch(it -> StringUtils.contains(it, AdminPermission.ADMIN.getAction()));
//        if (isAdministrator) {
//            map.put("permission", PermissionCollector.permissionList);
//        } else {
        List<Permission> result = permissionConvert(userPermissions);
        map.put("permission", result);
//        }
        map.put("enums", enumCollector.enums);
        return Response.success(map);
    }

    private List<Permission> permissionConvert(List<String> userPermissions) {
        List<Permission> result = Lists.newArrayList();
        Map<String, List<String>> splitByUnderline = userPermissions.stream().collect(Collectors.groupingBy(it -> it.split(SEPARATOR)[0]));
        logger.info("collect is {}", splitByUnderline);
        PermissionCollector.permissionList.forEach(p -> {
            List<String> actions = splitByUnderline.get(p.getAction());
            if (CollectionUtils.isNotEmpty(actions)) {
                Permission permission = p.clone();
                List<Permission> elderlyPermission = p.getActions();
                List<Permission> subPermission = Lists.newArrayList();
                permission.setActions(subPermission);
                actions.forEach(action -> {
                    Permission containsPermission = elderlyPermission.stream().filter(it -> StringUtils.isNotBlank(it.getParentAction()))
                        .filter(it -> Objects
                            .equals(StringUtils.join(it.getParentAction().toLowerCase(), SEPARATOR + it.getAction().toLowerCase()), action))
                        .findFirst().orElse(null);
                    if (Objects.nonNull(containsPermission)) {
                        subPermission.add(containsPermission);
                    }
                });
                result.add(permission);
            }
        });
        return result;
    }


}
