package com.github.wenslo.springbootdemo.permissions;

public enum AdminPermission implements Permission {
    ADMIN("admin", "超级管理员");
    private String action;
    private String describe;

    AdminPermission(String action, String describe) {
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
        return "super";
    }

    @Override
    public String getGroupDescribe() {
        return "超级权限";
    }
}
