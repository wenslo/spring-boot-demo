package com.github.wenslo.springbootdemo.service.system;

import com.github.wenslo.springbootdemo.condition.system.RoleCondition;
import com.github.wenslo.springbootdemo.dto.system.role.RolePageDTO;
import com.github.wenslo.springbootdemo.model.system.Role;
import com.github.wenslo.springbootdemo.service.base.LongIdService;

import java.util.List;
import java.util.Map;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月12日 上午10:29
 * @description
 */
public interface RoleService extends LongIdService<Role, RoleCondition> {
    /**
     * 填充列表删除标志
     * @param roles         角色信息
     * @param resultContent 列表数据
     */
    public void populateRoleDeleteFlag(List<Role> roles, List<RolePageDTO> resultContent);

    /**
     * 根据角色和用户数目分组
     * @param roles 角色
     */
    public Map<Long, Long> groupingUserCountByRoles(List<Role> roles);

    public void saveRole(Role role);

    /**
     * 刷新所有角色数据权限
     */
    public void flushRoleDataPermission();
}
