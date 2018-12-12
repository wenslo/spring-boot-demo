package com.github.wenslo.springbootdemo.service.system.impl;

import com.github.wenslo.springbootdemo.condition.system.RoleCondition;
import com.github.wenslo.springbootdemo.model.system.QRole;
import com.github.wenslo.springbootdemo.model.system.Role;
import com.github.wenslo.springbootdemo.service.BaseServiceImpl;
import com.github.wenslo.springbootdemo.service.system.RoleService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月12日 上午10:30
 * @description
 */
@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleCondition> implements RoleService {
    @Override
    protected Predicate toPredicate(RoleCondition condition) {
        BooleanBuilder builder = new BooleanBuilder();
        QRole role = QRole.role;
        String roleName = condition.getRoleName();
        if (StringUtils.isNotBlank(roleName)) {
            builder.and(role.roleName.startsWith(roleName));
        }
        return builder;
    }
}