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
    /** 责任人 **/
    protected String responsiblePerson;
    /** 手机号 **/
    protected String phone;
    /** 备注 **/
    protected String note;
    /** 角色信息 **/
    protected List<SimpleRole> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
                ", responsiblePerson='" + responsiblePerson + '\'' +
                ", phone='" + phone + '\'' +
                ", note='" + note + '\'' +
                ", roles=" + roles +
                '}';
    }
}
