package com.github.wenslo.springbootdemo.util;

import com.github.wenslo.springbootdemo.cache.PermissionCollector;
import com.github.wenslo.springbootdemo.model.system.Permission;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;


/**
 * @author wenhailin
 * @create 24/4/2020-17:54
 */
public class PermissionUtil {
    /**
     * 权限转为对象
     *
     * @param userPermissions 权限数据
     */
    public static Map<String, List<Permission>> convertPermission(List<String> userPermissions) {
        Map<String, List<Permission>> result = Maps.newHashMap();
        PermissionCollector.permissionMap.forEach((group, permissions) -> {
            List<Permission> leather = Lists.newArrayList();
            result.putIfAbsent(group, leather);
            permissions.forEach(it -> {
                if (userPermissions.contains(it.getValue())) {
                    leather.add(it);
                }
            });
        });
        return result;
    }

    /**
     * 权限转为字符串
     *
     * @param userPermissions 权限数据
     */
    public static Map<String, List<String>> convertPermissionGroup(List<String> userPermissions) {
        Map<String, List<String>> result = Maps.newHashMap();
        PermissionCollector.permissionMap.forEach((group, permissions) -> {
            List<String> leather = Lists.newArrayList();
            result.putIfAbsent(group, leather);
            permissions.forEach(it -> {
                if (userPermissions.contains(it.getValue())) {
                    leather.add(it.getValue());
                }
            });
        });
        return result;
    }
}
