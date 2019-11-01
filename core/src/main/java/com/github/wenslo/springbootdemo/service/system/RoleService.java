package com.github.wenslo.springbootdemo.service.system;

import com.github.wenslo.fluent.data.service.LongIdService;
import com.github.wenslo.springbootdemo.condition.system.RoleCondition;
import com.github.wenslo.springbootdemo.model.system.Role;

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
     * 根据角色和用户数目分组
     *
     * @param roles 角色
     */
    public Map<Long, Long> groupingUserCountByRoles(List<Role> roles);

}
