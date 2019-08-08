package com.github.wenslo.springbootdemo.dto.system.role;

import com.github.wenslo.springbootdemo.dto.LongIdDTO;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-03-19 22:58
 * @description
 */
public class RolePageDTO extends LongIdDTO {
    /** 角色名 **/
    private String name;
    /** 角色描述 **/
    private String description;
    /** 启用状态 **/
    private Boolean enabled;

    public RolePageDTO(Long id) {
        this.id = id;
    }

    public RolePageDTO(String name, String description, Boolean enabled) {
        this.name = name;
        this.description = description;
        this.enabled = enabled;
    }

    public RolePageDTO(String name) {
        this.name = name;
    }
}
