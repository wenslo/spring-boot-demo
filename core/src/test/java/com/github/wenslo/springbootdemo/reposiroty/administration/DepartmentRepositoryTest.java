package com.github.wenslo.springbootdemo.reposiroty.administration;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.wenslo.springbootdemo.DBTestCase;
import com.github.wenslo.springbootdemo.model.administration.Department;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-01 14:05
 * @description 部门
 */
@DatabaseSetup(value = "/dataset.xml")
public class DepartmentRepositoryTest extends DBTestCase {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testFindByOrganizationIdEquals() {
        Long organizationId = -1L;
        List<Department> data = departmentRepository.findByOrganizationIdEquals(organizationId).get();
        Assert.assertFalse(data.isEmpty());
        logger.debug("findByOrganizationIdEquals parameter is {},result is {}", organizationId, data);
    }


    @Test
    public void testFindByParentDepartmentIdIn() {
        List<Long> parameter = Lists.newArrayList(-222L, -333L);
        List<Department> list = departmentRepository.findByParentDepartmentIdIn(parameter);
        Assert.assertTrue(list.isEmpty());
        logger.debug("testFindByParentDepartmentIdIn , parameter is {}, result is {}", parameter, list);
    }

}