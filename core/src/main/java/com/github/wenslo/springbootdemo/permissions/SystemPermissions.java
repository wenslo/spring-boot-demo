package com.github.wenslo.springbootdemo.permissions;

import com.github.wenslo.springbootdemo.annotation.permission.Permission;
import com.github.wenslo.springbootdemo.annotation.permission.PermissionGroup;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月09日 下午7_37
 * @description
 */
@PermissionGroup
public class SystemPermissions {
    //-------------------------超级管理员权限------------------------------
    public static final String ADMINISTRATOR = "ADMINISTRATOR";
    //-------------------------用户相关权限------------------------------
    private static final String USER_PERMISSION = "用户权限";

    @Permission(value = "用户查看", group = USER_PERMISSION)
    public static final String USER_VIEW = "USER_VIEW";

    @Permission(value = "用户添加", group = USER_PERMISSION)
    public static final String USER_ADD = "USER_ADD";

    @Permission(value = "用户修改", group = USER_PERMISSION)
    public static final String USER_UPDATE = "USER_UPDATE";

    @Permission(value = "用户删除", group = USER_PERMISSION)
    public static final String USER_DELETE = "USER_DELETE";

    //-------------------------角色相关权限------------------------------
    private static final String ROLE_PERMISSION = "角色权限";

    @Permission(value = "角色查看", group = ROLE_PERMISSION)
    public static final String ROLE_VIEW = "ROLE_VIEW";

    @Permission(value = "角色添加", group = ROLE_PERMISSION)
    public static final String ROLE_ADD = "ROLE_ADD";

    @Permission(value = "角色修改", group = ROLE_PERMISSION)
    public static final String ROLE_UPDATE = "ROLE_UPDATE";

    @Permission(value = "角色删除", group = ROLE_PERMISSION)
    public static final String ROLE_DELETE = "ROLE_DELETE";
}
