package com.github.wenslo.springbootdemo.service.system.impl;

import com.github.wenslo.fluent.data.model.LongIdEntity;
import com.github.wenslo.fluent.data.service.impl.LongIdServiceImpl;
import com.github.wenslo.springbootdemo.condition.system.RoleCondition;
import com.github.wenslo.springbootdemo.model.system.QRole;
import com.github.wenslo.springbootdemo.model.system.Role;
import com.github.wenslo.springbootdemo.model.system.User;
import com.github.wenslo.springbootdemo.reposiroty.system.UserRepository;
import com.github.wenslo.springbootdemo.service.system.RoleService;
import com.google.common.collect.Maps;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月12日 上午10:30
 * @description
 */
@Service
@Transactional
public class RoleServiceImpl extends LongIdServiceImpl<Role, RoleCondition> implements RoleService {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected List<Predicate> conditionBuild(RoleCondition condition) {
        List<Predicate> conditionBuilder = super.conditionBuild(condition);
        BooleanBuilder builder = new BooleanBuilder();
        QRole role = QRole.role;
        String roleName = condition.getName();
        if (StringUtils.isNotBlank(roleName)) {
            builder.and(role.name.startsWith(roleName));
        }
        conditionBuilder.add(builder);
        return conditionBuilder;
    }


    @Override
    public Map<Long, Long> groupingUserCountByRoles(List<Role> roles) {
        List<User> users = userRepository.findByRolesIn(roles);
        Map<Long, Long> map = Maps.newHashMap();
        users.forEach(user -> user.getRoles().stream().map(LongIdEntity::getId).forEach(roleId -> {
            if (Objects.isNull(map.get(roleId))) {
                map.put(roleId, 1L);
            } else {
                Long count = map.get(roleId);
                ++count;
                map.put(roleId, count);
            }
        }));
        return map;
    }

}
