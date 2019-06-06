package com.github.wenslo.springbootdemo.model.base;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.github.wenslo.springbootdemo.model.system.Organization;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-01 00:30
 * @description 机构实体基类
 */
// @Inheritance
@MappedSuperclass
public abstract class OrganizationBasicEntity extends LongIdEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
