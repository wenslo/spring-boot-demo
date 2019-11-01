package com.github.wenslo.springbootdemo.controller.system;

import com.github.wenslo.springbootdemo.cache.PermissionCollector;
import com.github.wenslo.springbootdemo.condition.system.RoleCondition;
import com.github.wenslo.springbootdemo.controller.BaseController;
import com.github.wenslo.springbootdemo.dto.system.StatusDTO;
import com.github.wenslo.springbootdemo.model.system.Role;
import com.github.wenslo.springbootdemo.service.system.RoleService;
import com.github.wenslo.springbootdemo.util.BeanUtil;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月12日 上午10:06
 * @description 角色
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private RoleService roleService;

    @RequestMapping("/save")
    public Response save(@RequestBody Role role) {
        logger.debug("That be saved role is {}", gson.toJson(role));
        if (Objects.nonNull(role.getId())) {
            Role oldRole = roleService.get(role.getId());
            BeanUtil.copyProperties(role, oldRole);
            roleService.save(oldRole);
        } else {
            roleService.save(role);
        }
        return Response.SUCCESS;
    }

    @RequestMapping("/queryByPage")
    public Page<Role> queryByPage(@RequestBody RoleCondition condition) {
        logger.debug("The currently operator is {}, condition is {}", getLoginUsername(), gson.toJson(condition));
        return roleService.getByCondition(condition, condition.getPageable());
    }

    @RequestMapping("/detail/{id}")
    public Response detail(@PathVariable Long id) {
        return Response.success(roleService.get(id));
    }

    @RequestMapping("/remove/{id}")
    public Response remove(@PathVariable Long id) {
        Map<Long, Long> map = roleService.groupingUserCountByRoles(Lists.newArrayList(new Role(id)));
        logger.debug("grouping user map is {} ", gson.toJson(map));
        Long count = map.get(id);
        if (Objects.nonNull(count) && count > 0) {
            return Response.error("该角色被占用，无法删除！");
        }
        roleService.remove(id);
        return Response.SUCCESS;
    }

    @RequestMapping("/status")
    public Response changeEnabledStatus(@RequestBody StatusDTO dto) {
        Role role = roleService.get(dto.getId());
        role.setEnabled(dto.isEnabled());
        roleService.save(role);
        return Response.SUCCESS;
    }

    @RequestMapping("/allPermission")
    public Response allPermission() {
        return Response.success(PermissionCollector.permissionMap);
    }
}
