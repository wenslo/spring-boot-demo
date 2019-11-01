package com.github.wenslo.springbootdemo.service.system.impl;

import com.github.wenslo.fluent.data.service.impl.LongIdServiceImpl;
import com.github.wenslo.springbootdemo.condition.system.OrganizationCondition;
import com.github.wenslo.springbootdemo.model.system.Organization;
import com.github.wenslo.springbootdemo.model.system.QOrganization;
import com.github.wenslo.springbootdemo.service.system.OrganizationService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-02 10:56
 * @description 机构service
 */
@Service
@Transactional(readOnly = true)
public class OrganizationServiceImpl extends LongIdServiceImpl<Organization, OrganizationCondition> implements OrganizationService {
    @Override
    protected List<Predicate> conditionBuild(OrganizationCondition condition) {
        List<Predicate> conditionList = super.conditionBuild(condition);
        BooleanBuilder builder = new BooleanBuilder();
        QOrganization organization = QOrganization.organization;
        String name = condition.getName();
        if (StringUtils.isNotBlank(name)) {
            organization.name.startsWith(name);
        }

        String districtCode = condition.getDistrictCode();
        if (StringUtils.isNotBlank(districtCode)) {
            organization.districtCode.startsWith(districtCode);
        }
        conditionList.add(builder);
        return conditionList;
    }
}
