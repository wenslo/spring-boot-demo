package com.github.wenslo.springbootdemo.dto.system.user;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-03-21 17:31
 * @description
 */
public class UserSaveDTO extends SimpleUser {
    private String password;
    private boolean enabled;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
