package com.github.wenslo.springbootdemo.model.system;

import com.github.wenslo.fluent.data.model.LongIdEntity;
import com.github.wenslo.springbootdemo.cache.PermissionCollector;
import com.github.wenslo.springbootdemo.convert.StringListConverter;
import com.github.wenslo.springbootdemo.permissions.AdminPermission;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年11月24日 下午3:59
 * @description
 */
@Entity
@Table(name = "user", indexes = {@Index(name = "username_index", columnList = "username")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@NamedEntityGraph(name = "user.organizations", attributeNodes = @NamedAttributeNode(value = "organizations"))
public class User extends LongIdEntity implements UserDetails {

    /** 用户名 **/
    @NotBlank(message = "用户名不能为空")
    @Column(name = "username")
    private String username;
    /** 密码 **/
    @NotBlank(message = "密码不能为空")
    @Column(name = "password")
    private String password;
    @NotBlank(message = "昵称不能为空")
    @Column(name = "nickname")
    private String nickname;
    /** 权限 **/
    @Column(name = "permission", length = 1024)
    @Convert(converter = StringListConverter.class)
    private List<String> permission;
    /** 角色 **/
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id"),},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;
    /** 账户是否过期 **/
    @Column(name = "account_non_expired")
    private boolean accountNonExpired;
    /** 账户是否锁定 **/
    @Column(name = "account_non_locked")
    private boolean accountNonLocked;
    /** 凭证是否未过期 **/
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired;
    /** 账户是否启用 **/
    @Column(name = "enabled")
    private boolean enabled;
    /** 所绑定机构信息 **/
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_organization", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "organization_id")})
    private List<Organization> organizations;


    /** 头像 **/
    private String avatar;

    //    /** 手机 **/
//    private String phone;
//    /** 最后登录IP **/
//    @Column(name = "last_login_ip")
//    private String lastLoginIp;
//    /** 最后登录时间 **/
//    @Column(name = "last_login_time")
//    private LocalDateTime lastLoginTime;
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<String> authorities = Sets.newHashSet();
        if (Objects.isNull(permission)) {
            return Lists.newArrayList();
        }
        if (!permission.isEmpty()) {
            authorities.addAll(this.permission);
        }
        if (!roles.isEmpty()) {
            roles.forEach(it -> {
                List<String> rolePermissions = it.getPermission();
                if (!rolePermissions.isEmpty()) {
                    boolean match = rolePermissions.stream().anyMatch(
                            authority -> StringUtils.contains(authority, AdminPermission.ADMIN.getAction()));
                    if (match) {
                        authorities.addAll(PermissionCollector.permissionSet);
                    }
                    authorities.addAll(rolePermissions);
                }
            });
        }
        return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", permission=" + permission +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                '}';
    }
}
