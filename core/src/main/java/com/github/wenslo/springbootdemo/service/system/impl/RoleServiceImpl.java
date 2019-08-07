package com.github.wenslo.springbootdemo.service.system.impl;

import com.github.wenslo.springbootdemo.condition.system.RoleCondition;
import com.github.wenslo.springbootdemo.dto.system.role.RolePageDTO;
import com.github.wenslo.springbootdemo.model.system.QRole;
import com.github.wenslo.springbootdemo.model.system.Role;
import com.github.wenslo.springbootdemo.service.base.impl.LongIdServiceImpl;
import com.github.wenslo.springbootdemo.service.system.RoleService;
import com.google.common.collect.Maps;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月12日 上午10:30
 * @description
 */
@Service
@Transactional
public class RoleServiceImpl extends LongIdServiceImpl<Role, RoleCondition> implements RoleService {

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
    public void populateRoleDeleteFlag(List<Role> roles, List<RolePageDTO> resultContent) {

        Map<Long, Long> map = this.groupingUserCountByRoles(roles);
        resultContent.forEach(it -> {
            Long count = map.get(it.getId());
            if (Objects.nonNull(count) && count > 0) {
                it.setDeletable(false);
            }
        });
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

    @Override
    public void saveRole(Role role) {
        populateSchoolIdsByPermissionType(role);
        this.save(role);
    }

    private void populateSchoolIdsByPermissionType(Role role) {
        switch (role.getPermissionType()) {
            case ALL: {
                role.setSchoolIds(schoolRepository.findAll().stream().map(School::getId).map(String::valueOf).collect(Collectors.toList()));
                break;
            }
            case AREA: {
                List<String> schoolIds = schoolRepository.findByAreaId(role.getAreaId()).stream().map(School::getId).map(String::valueOf).collect(Collectors.toList());
                role.setSchoolIds(schoolIds);
                break;
            }
            case SCHOOL:
                break;
        }
    }

    @Override
    public void flushRoleDataPermission() {
        List<Role> roles = roleRepository.findAll();
        logger.debug("flushRoleDataPermission, roles size is {}", roles.size());
        roles.forEach(this::populateSchoolIdsByPermissionType);
        repository.saveAll(roles);
    }
}
