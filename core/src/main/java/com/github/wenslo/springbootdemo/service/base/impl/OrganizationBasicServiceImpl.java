package com.github.wenslo.springbootdemo.service.base.impl;

import com.github.wenslo.fluent.data.querydsl.CustomEntityPathBase;
import com.github.wenslo.fluent.data.service.impl.LongIdServiceImpl;
import com.github.wenslo.springbootdemo.condition.base.OrganizationBasicCondition;
import com.github.wenslo.springbootdemo.model.base.OrganizationBasicEntity;
import com.github.wenslo.springbootdemo.reposiroty.base.OrganizationBasicRepository;
import com.github.wenslo.springbootdemo.service.base.OrganizationBasicService;
import com.querydsl.core.types.Predicate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-01 19:53
 * @description
 */
public abstract class OrganizationBasicServiceImpl<T extends OrganizationBasicEntity, C extends OrganizationBasicCondition>
        extends LongIdServiceImpl<T, C> implements OrganizationBasicService<T, C> {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationBasicServiceImpl.class);
    @Autowired
    protected OrganizationBasicRepository<T, Long> repository;

    @Override
    protected List<Predicate> conditionBuild(C condition) {
        List<Predicate> conditionBuilder = super.conditionBuild(condition);
        CustomEntityPathBase<T> pathBase = super.getEntityPath();
        Long organizationId = condition.getOrganizationId();
//        if (Objects.nonNull(organizationId)) {
//            conditionBuilder.add(pathBase.organization.id.eq(organizationId));
//        }
        return conditionBuilder;
    }

    @Override
    public Optional<List<T>> findByOrganizationId(Long organizationId) {
        return repository.findByOrganizationIdEquals(organizationId);
    }

}
