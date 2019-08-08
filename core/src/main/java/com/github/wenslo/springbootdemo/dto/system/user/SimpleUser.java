package com.github.wenslo.springbootdemo.dto.system.user;

import com.github.wenslo.springbootdemo.dto.LongIdDTO;
import com.github.wenslo.springbootdemo.dto.system.role.SimpleRole;

import java.util.List;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-03-19 13:04
 * @description
 */
public class SimpleUser extends LongIdDTO {
    /** 用户名 **/
    protected String username;
    /** 昵称 **/
    protected String nickname;
    /** 角色信息 **/
    protected List<SimpleRole> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public List<SimpleRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SimpleRole> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "SimpleUser{" +
                "username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", roles=" + roles +
                '}';
    }
}
