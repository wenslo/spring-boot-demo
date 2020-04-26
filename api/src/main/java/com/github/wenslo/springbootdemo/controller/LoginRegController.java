package com.github.wenslo.springbootdemo.controller;

import com.github.wenslo.fluent.core.domain.Response;
import com.github.wenslo.fluent.security.SecurityUtil;
import com.github.wenslo.springbootdemo.cache.EnumCollector;
import com.github.wenslo.springbootdemo.model.system.User;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年11月29日 下午5:52
 * @description
 */
@RestController
public class LoginRegController {

    private static final Logger logger = LoggerFactory.getLogger(LoginRegController.class);
    @Autowired
    private EnumCollector enumCollector;

    @RequestMapping("/login_page")
    public Response loginPage() {
        return Response.UNAUTHORIZED;
    }

    @RequestMapping("/me")
    public Response me() {
        Map<String, Object> map = Maps.newHashMap();
        User user = (User) SecurityUtil.getLoginUser();
        user.setPassword(null);
        map.put("user", user);
        map.put("enums", enumCollector.enums);
        return Response.success(map);
    }

}
