package com.github.wenslo.springbootdemo.service.system;

import com.github.wenslo.fluent.data.service.LongIdService;
import com.github.wenslo.springbootdemo.condition.system.OrganizationCondition;
import com.github.wenslo.springbootdemo.model.system.Organization;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-02 10:52
 * @description 机构
 */
public interface OrganizationService extends LongIdService<Organization, OrganizationCondition> {
}
