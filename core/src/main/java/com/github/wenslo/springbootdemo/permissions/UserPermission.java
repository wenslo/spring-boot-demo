package com.github.wenslo.springbootdemo.permissions;

/**
 * 用户权限
 */
public enum UserPermission implements Permission {
    ADD("正常"), UPDATE("修改"), DELETE("删除"), PASSWORD_RESET("密码重置");
    private String describe;

    UserPermission(String describe) {
        this.describe = describe;
    }


    public String getDescribe() {
        return describe;
    }


    public void setDescribe(String describe) {
        this.describe = describe;
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
