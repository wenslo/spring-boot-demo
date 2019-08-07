package com.github.wenslo.springbootdemo.dto.system.user;

import com.github.wenslo.springbootdemo.dto.LongIdDTO;
import com.github.wenslo.springbootdemo.model.system.Role;

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
    protected Role role;
}
