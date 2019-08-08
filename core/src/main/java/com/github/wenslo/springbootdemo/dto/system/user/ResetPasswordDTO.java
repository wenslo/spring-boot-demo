package com.github.wenslo.springbootdemo.dto.system.user;


import java.io.Serializable;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-03-19 14:45
 * @description
 */
public class ResetPasswordDTO implements Serializable {
    private Long id;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ResetPasswordDTO{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
