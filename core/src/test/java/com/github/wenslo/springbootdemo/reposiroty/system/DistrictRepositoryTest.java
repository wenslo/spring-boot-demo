package com.github.wenslo.springbootdemo.reposiroty.system;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.wenslo.springbootdemo.DBTestCase;
import com.github.wenslo.springbootdemo.model.system.District;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@DatabaseSetup(value = "/dataset.xml")
public class DistrictRepositoryTest extends DBTestCase {

    @Autowired
    private DistrictRepository districtRepository;

    @Test
    public void testFindByCodeLength() {
        Integer length = 2;
        logger.debug("testFindByCodeLength , length is {}", length);
        Optional<List<District>> optional = districtRepository.findByCodeLength(length);
        List<District> districts = optional.orElse(null);
        Assert.assertNotNull(districts);
        Assert.assertFalse(districts.isEmpty());
        logger.debug("testFindByCodeLength result is {} ", gson.toJson(districts));
    }

    @Test
    public void testFindByParentCodeIsNull() {
        List<District> districts = districtRepository.findByParentCodeIsNull().orElse(null);
        Assert.assertNotNull(districts);
        logger.debug("testFindByParentCodeIsNull result is {}", gson.toJson(districts));
    }


}