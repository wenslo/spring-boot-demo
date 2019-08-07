package com.github.wenslo.springbootdemo.dto.system.role;

import com.github.wenslo.springbootdemo.dto.LongIdDTO;

import java.util.List;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-03-28 15:17
 * @description
 */
public class RoleDetailDTO extends LongIdDTO {
    /** 角色名称 **/
    private String name;
    /** 角色描述 **/
    private String description;
    /** 角色是否启用 **/
    private Boolean enabled;
    /** 角色权限 **/
    private List<String> permission;
}
