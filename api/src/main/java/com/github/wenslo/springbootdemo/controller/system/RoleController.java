package com.github.wenslo.springbootdemo.controller.system;

import com.github.wenslo.fluent.core.domain.Response;
import com.github.wenslo.springbootdemo.cache.PermissionCollector;
import com.github.wenslo.springbootdemo.condition.system.RoleCondition;
import com.github.wenslo.springbootdemo.controller.BaseController;
import com.github.wenslo.springbootdemo.dto.system.StatusDTO;
import com.github.wenslo.springbootdemo.model.system.Role;
import com.github.wenslo.springbootdemo.service.system.RoleService;
import com.github.wenslo.springbootdemo.util.BeanUtil;
import com.github.wenslo.springbootdemo.util.PermissionUtil;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

import static com.github.wenslo.springbootdemo.permissions.SystemPermissions.*;

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
    @PreAuthorize("hasAuthority('" + ROLE_ADD + "')")
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
    @PreAuthorize("hasAuthority('" + ROLE_VIEW + "')")
    public Page<Role> queryByPage(@RequestBody RoleCondition condition) {
        logger.debug("The currently operator is {}, condition is {}", getLoginUsername(), gson.toJson(condition));
        return roleService.getByCondition(condition, condition.getPageable());
    }

    @RequestMapping("/detail/{id}")
    @PreAuthorize("hasAuthority('" + ROLE_VIEW + "')")
    public Response detail(@PathVariable Long id) {
        Role role = roleService.get(id);
        role.setConvertedPermission(PermissionUtil.convertPermissionGroup(role.getPermission()));
        logger.info("converted permission is {}", role.getConvertedPermission());
        return Response.success(role);
    }

    @RequestMapping("/remove/{id}")
    @PreAuthorize("hasAuthority('" + ROLE_DELETE + "')")
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
    @PreAuthorize("hasAuthority('" + ROLE_UPDATE + "')")
    public Response changeEnabledStatus(@RequestBody StatusDTO dto) {
        Role role = roleService.get(dto.getId());
        role.setEnabled(dto.isEnabled());
        roleService.save(role);
        return Response.SUCCESS;
    }

    @RequestMapping("/allPermission")
    @PreAuthorize("hasAuthority('" + ROLE_VIEW + "')")
    public Response allPermission() {
        return Response.success(PermissionCollector.permissionMap);
    }
}
