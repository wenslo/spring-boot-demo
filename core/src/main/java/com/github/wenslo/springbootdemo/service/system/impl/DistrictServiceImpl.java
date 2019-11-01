package com.github.wenslo.springbootdemo.service.system.impl;

import java.util.List;

import com.github.wenslo.fluent.data.service.impl.LongIdServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.wenslo.springbootdemo.condition.system.DistrictCondition;
import com.github.wenslo.springbootdemo.enums.common.DistrictType;
import com.github.wenslo.springbootdemo.model.system.District;
import com.github.wenslo.springbootdemo.reposiroty.system.DistrictRepository;
import com.github.wenslo.springbootdemo.service.system.DistrictService;
import com.google.common.collect.Lists;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-01 20:08
 * @description
 */
@Service
@Transactional(readOnly = true)
public class DistrictServiceImpl extends LongIdServiceImpl<District, DistrictCondition> implements DistrictService {
    private static final Integer CITY_CODE_LENGTH = 4;
    private static final Integer AREA_CODE_LENGTH = 6;
    @Autowired
    private DistrictRepository districtRepository;

    @Override
    public List<District> findByType(DistrictType type) {
        List<District> result = null;
        switch (type) {
            case PROVINCE:
                result = districtRepository.findByParentCodeIsNull().orElse(Lists.newArrayList());
                break;
            case CITY:
                result = districtRepository.findByCodeLength(CITY_CODE_LENGTH).orElse(Lists.newArrayList());
                break;
            case AREA:
                result = districtRepository.findByCodeLength(AREA_CODE_LENGTH).orElse(Lists.newArrayList());
                break;
        }
        return result;
    }
}
