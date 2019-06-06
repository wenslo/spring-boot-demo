package com.github.wenslo.springbootdemo.condition.base;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-01 00:48
 * @description 机构查询条件
 */
public class OrganizationBasicCondition extends PageCondition {
    private Long organizationId;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
