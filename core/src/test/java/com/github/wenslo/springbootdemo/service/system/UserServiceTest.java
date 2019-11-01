package com.github.wenslo.springbootdemo.service.system;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.wenslo.springbootdemo.DBTestCase;
import com.github.wenslo.springbootdemo.condition.system.UserCondition;
import com.github.wenslo.springbootdemo.model.system.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.util.Objects;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月03日 下午8:30
 * @description
 */
@DatabaseSetup(value = "/dataset.xml")
public class UserServiceTest extends DBTestCase {

    @Autowired
    private UserService userService;

    @Test
    public void testFindAll() {
        UserCondition condition = new UserCondition();
        condition.setUsername("user2");
        Pageable pageable = new Pageable();
        Page<User> page = userService.getByCondition(condition, pageable);
        logger.debug("page data is {}", page);
        Assert.assertFalse(page.isEmpty());
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("Warren Wen");
        user.setPassword("111111");
        user.setNickname("小黄");
        logger.debug("parameter is {}", user);
        userService.save(user);
        Assert.assertTrue(Objects.nonNull(user.getId()));
    }

}
