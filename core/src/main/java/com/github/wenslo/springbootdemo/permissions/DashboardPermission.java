package com.github.wenslo.springbootdemo.permissions;

/**
 * 仪表盘全新啊
 */
public enum DashboardPermission implements Permission {
    ADD("add", "正常"),
    UPDATE("update", "修改"),
    DELETE("delete", "删除"),
    PASSWORD_RESET("password_reset", "密码重置");
    private String action;
    private String describe;

    DashboardPermission(String action, String describe) {
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
        return "dashboard";
    }

    @Override
    public String getGroupDescribe() {
        return "仪表盘权限";
    }

}
