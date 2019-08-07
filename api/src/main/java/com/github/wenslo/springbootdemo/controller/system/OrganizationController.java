package com.github.wenslo.springbootdemo.controller.system;

import com.github.wenslo.springbootdemo.condition.system.OrganizationCondition;
import com.github.wenslo.springbootdemo.controller.BaseController;
import com.github.wenslo.springbootdemo.domain.Response;
import com.github.wenslo.springbootdemo.model.system.Organization;
import com.github.wenslo.springbootdemo.service.system.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenhailin
 * @createTime 2019年08月07日 下午2:54
 * @description
 */
@RestController
@RequestMapping("organization")
public class OrganizationController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);
    @Autowired
    private OrganizationService organizationService;

    @RequestMapping("/queryByPage")
    public Page<Organization> findByPage(@RequestBody OrganizationCondition condition) {
        return organizationService.getByCondition(condition, condition.getPageable());
    }

    @RequestMapping("/detail/{id}")
    public Response detail(@PathVariable Long id) {
        return Response.success(organizationService.get(id));
    }

    @RequestMapping("/save")
    public Response save(@RequestBody Organization organization) {
        return Response.success(organizationService.save(organization));
    }
}
