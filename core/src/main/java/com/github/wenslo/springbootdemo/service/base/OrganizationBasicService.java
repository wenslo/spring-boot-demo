package com.github.wenslo.springbootdemo.service.base;

import com.github.wenslo.springbootdemo.condition.base.OrganizationBasicCondition;
import com.github.wenslo.springbootdemo.model.base.OrganizationBasicEntity;

import java.util.List;
import java.util.Optional;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-01 19:48
 * @description
 */
public interface OrganizationBasicService<T extends OrganizationBasicEntity, C extends OrganizationBasicCondition>
        extends LongIdService<T, C> {
    /**
     * 根据机构ID查询
     *
     * @param organizationId 机构ID
     */
    public Optional<List<T>> findByOrganizationId(Long organizationId);
}
