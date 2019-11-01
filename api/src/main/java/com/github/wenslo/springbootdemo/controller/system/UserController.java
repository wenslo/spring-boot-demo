package com.github.wenslo.springbootdemo.controller.system;

import com.github.wenslo.springbootdemo.condition.system.RoleCondition;
import com.github.wenslo.springbootdemo.condition.system.UserCondition;
import com.github.wenslo.springbootdemo.controller.BaseController;
import com.github.wenslo.springbootdemo.dto.system.StatusDTO;
import com.github.wenslo.springbootdemo.dto.system.role.SimpleRole;
import com.github.wenslo.springbootdemo.dto.system.user.ResetPasswordDTO;
import com.github.wenslo.springbootdemo.dto.system.user.UserDetailDTO;
import com.github.wenslo.springbootdemo.dto.system.user.UserPageDTO;
import com.github.wenslo.springbootdemo.dto.system.user.UserSaveDTO;
import com.github.wenslo.springbootdemo.model.system.Role;
import com.github.wenslo.springbootdemo.model.system.User;
import com.github.wenslo.springbootdemo.service.system.RoleService;
import com.github.wenslo.springbootdemo.service.system.UserService;
import com.github.wenslo.springbootdemo.util.BeanUtil;
import com.github.wenslo.springbootdemo.util.ExcelUtil;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月02日 下午1:47
 * @description
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ExcelUtil excelUtil;

    @RequestMapping("/save")
    public Response save(@RequestBody UserSaveDTO dto) {
        User user = new User();
        if (Objects.nonNull(dto.getId())) {
            BeanUtil.copyProperties(dto, user, "password");
        }
        user.setRoles(dto.getRoles().stream().map(role -> modelMapper.map(role, Role.class)).collect(Collectors.toList()));
        userService.save(user);
        logger.debug("That be saved user is {}", gson.toJson(user));
        return Response.SUCCESS;
    }


    @RequestMapping("/queryByPage")
    public Page<UserPageDTO> queryByPage(@RequestBody UserCondition condition) {
        logger.debug("The currently operator is {}, condition is {}", getLoginUsername(), gson.toJson(condition));
        Page<User> page = userService.getByCondition(condition, condition.getPageable());
        return pageTranslate(page);
    }

    private Page<UserPageDTO> pageTranslate(Page<User> page) {
        List<UserPageDTO> resultContent = page.getContent().stream().map(it -> {
            UserPageDTO dto = modelMapper.map(it, UserPageDTO.class);
            dto.setRoles(it.getRoles().stream().map(role -> modelMapper.map(role, SimpleRole.class)).collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
        return new PageImpl<>(resultContent, page.getPageable(), page.getTotalElements());
    }

    @RequestMapping("/remove/{id}")
    public Response remove(@PathVariable Long id) {
        logger.debug("be remove user id is {}", id);
        userService.remove(id);
        return Response.SUCCESS;
    }

    @RequestMapping("/detail/{id}")
    public Response detail(@PathVariable Long id) {
        User user = userService.get(id);
        UserDetailDTO dto = modelMapper.map(user, UserDetailDTO.class);
        return Response.success(dto);
    }

    @RequestMapping("/reset")
    public Response reset(@RequestBody ResetPasswordDTO dto) {
        logger.debug("be reset password user id is  {}", dto.getId());
        User user = userService.get(dto.getId());
        user.setPassword(dto.getPassword());
        userService.save(user);
        return Response.SUCCESS;
    }

    @RequestMapping("/status")
    public Response changeStatus(@RequestBody StatusDTO dto) {
        User user = userService.get(dto.getId());
        user.setEnabled(dto.isEnabled());
        userService.save(user);
        return Response.SUCCESS;
    }

    @RequestMapping("/getAllRole")
    public Response queryAll() {
        RoleCondition roleCondition = new RoleCondition();
        roleCondition.setEanbled(true);
        return Response.success(roleService.getByCondition(roleCondition));
    }

    @RequestMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<User> users = userService.getAll();
        response.reset();
        Map<String, Object> map = Maps.newHashMap();
        map.put("users", users);
        String exportFile = excelUtil.export("/template/user.xls", map);
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application.octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String("用户数据".getBytes(), "ISO8859_1") + ".xls");
        OutputStream outputStream = response.getOutputStream();
        try (FileInputStream fileInputStream = new FileInputStream(exportFile)) {
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fileInputStream.read(bytes)) > 0) {
                outputStream.write(bytes, 0, length);
            }
            outputStream.flush();
            outputStream.close();
        }
    }
}
