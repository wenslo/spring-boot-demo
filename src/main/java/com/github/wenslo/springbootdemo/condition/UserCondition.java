package com.github.wenslo.springbootdemo.condition;

import com.github.wenslo.springbootdemo.base.condition.BaseCondition;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月02日 下午2:50
 * @description
 */
public class UserCondition extends BaseCondition {
    private String username;
    private Boolean enabled;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
