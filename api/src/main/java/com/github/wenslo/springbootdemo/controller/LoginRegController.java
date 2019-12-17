package com.github.wenslo.springbootdemo.controller;

import com.github.wenslo.fluent.core.domain.Response;
import com.github.wenslo.fluent.security.SecurityUtil;
import com.github.wenslo.springbootdemo.cache.EnumCollector;
import com.github.wenslo.springbootdemo.cache.PermissionCollector;
import com.github.wenslo.springbootdemo.model.system.User;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
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
        boolean isAdministrator = userPermissions.stream().anyMatch(it -> Objects.equals(it, SystemPermission.ADMINISTRATOR));
        if (isAdministrator) {
            map.put("permission", PermissionCollector.permissionSet);
        } else {
            map.put("permission", userPermissions);
        }
        map.put("enums", enumCollector.enums);
        return Response.success(map);
    }


}
