package com.github.wenslo.springbootdemo.permissions;

import com.github.wenslo.fluent.security.annotation.Permission;

/**
 * 用户权限
 */
public enum UserPermission implements Permission {
    ADD("add", "正常"),
    UPDATE("update", "修改"),
    DELETE("delete", "删除"),
    PASSWORD_RESET("password_reset", "密码重置");
    private String action;
    private String describe;

    UserPermission(String action, String describe) {
        this.action = action;
        this.describe = describe;
    }


    @Override
    public String getAction() {
        return action;
    }

    @Override
    public String getDescribe() {
        return describe;
    }

    @Override
    public String getGroup() {
        return "user";
    }

    @Override
    public String getGroupDescribe() {
        return "用户权限";
    }
}
