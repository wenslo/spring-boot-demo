package com.github.wenslo.springbootdemo.model.system;

import com.github.wenslo.springbootdemo.convert.PermissionConverter;
import com.github.wenslo.springbootdemo.model.base.LongIdEntity;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年11月24日 下午3:59
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(exclude = "organizations")
@Entity
@Table(name = "user", indexes = {@Index(name = "username_index", columnList = "username")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@NamedEntityGraph(name = "user.organizations",
        attributeNodes = @NamedAttributeNode(value = "organizations"))
public class User extends LongIdEntity implements UserDetails {

    /** 用户名 **/
    @NotBlank(message = "用户名不能为空")
    @Column(name = "username")
    private String username;
    /** 密码 **/
    @NotBlank(message = "密码不能为空")
    @Column(name = "password")
    private String password;
    /** 权限 **/
    @Column(name = "permission", length = 1024)
    @Convert(converter = PermissionConverter.class)
    private List<Permission> permission;
    /** 角色 **/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id"),}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;
    /** 账户是否过期 **/
    @Column(name = "account_non_expired")
    private boolean accountNonExpired;
    /** 账户是否锁定 **/
    @Column(name = "account_non_locked")
    private boolean accountNonLocked;
    /** 凭证未过期 **/
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;
    /** 账户是否启用 **/
    @Column(name = "enabled")
    private boolean enabled;
    /** 所绑定驾校信息 **/
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_organization", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "organization_id")})
    private List<Organization> organizations;

    @Transient
    private Map<String, List<Permission>> permissionMap;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> authorities = Sets.newHashSet();
        if (Objects.isNull(permission)) {
            return Lists.newArrayList();
        }
        if (!permission.isEmpty()) {
            authorities.addAll(getPermissionCollect(this.permission));
        }
        if (!roles.isEmpty()) {
            roles.forEach(it -> {
                List<Permission> rolePermissions = it.getPermission();
                if (!rolePermissions.isEmpty()) {
                    authorities.addAll(getPermissionCollect(rolePermissions));
                }
            });
        }
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    /**
     * collect permission
     * @param permissions permissions
     * @return permission str on set
     */
    private Set<String> getPermissionCollect(List<Permission> permissions) {
        return permissions.stream().filter(Permission::isEnabled).map(Permission::getValue).collect(Collectors.toSet());
    }

}
