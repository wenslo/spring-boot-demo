package com.github.wenslo.springbootdemo.model.system;

import com.github.wenslo.springbootdemo.convert.StringListConverter;
import com.github.wenslo.springbootdemo.model.base.OrganizationBasicEntity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年11月30日 下午3:01
 * @description
 */
@Entity
public class Role extends OrganizationBasicEntity {
    /** 角色名称 **/
    @NotEmpty
    @Column(name = "name", unique = true, length = 128)
    private String name;
    /** 角色描述 **/
    private String description;
    /** 角色是否启用 **/
    private boolean enabled;
    /** 角色权限 **/
    @Column(name = "permission", length = 1024)
    @Convert(converter = StringListConverter.class)
    private List<String> permission;

    public Role() {
    }

    public Role(@NotEmpty String name, String description, boolean enabled, List<String> permission) {
        this.name = name;
        this.description = description;
        this.enabled = enabled;
        this.permission = permission;
    }

    public Role(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Role{" + "name='" + name + '\'' + ", description='" + description + '\'' + ", enabled=" + enabled
                + ", permission=" + permission + '}';
    }
}
