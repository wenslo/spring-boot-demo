package com.github.wenslo.springbootdemo.reposiroty.system;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.wenslo.springbootdemo.BaseTestCase;
import com.github.wenslo.springbootdemo.model.system.QUser;
import com.github.wenslo.springbootdemo.model.system.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年11月24日 下午6:11
 * @description
 */
@DatabaseSetup(value = "/dataset.xml")
public class UserRepositoryTest extends BaseTestCase {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testFindAll() {
        List<User> list = userRepository.findAll();
        logger.debug("list size is {}", list.size());
        logger.debug("list data is {}", list);
        Assert.assertTrue(!list.isEmpty());
    }

    @Test
    public void testFindByUsername() {
        String username = "user1";
        User user = userRepository.findByUsername(username);
        Assert.assertNotNull(user);
        logger.info("username is {} , findByUsername result is {},organizations is {}", user.getUsername(), user, user.getOrganizations());
    }

    @Test
    public void testQuerydsl() {
        String username = "user1";
        QUser user = QUser.user;
        BooleanExpression booleanExpression = user.username.startsWith(username);
        Optional one = userRepository.findOne(booleanExpression);
        Assert.assertTrue(one.isPresent());
        logger.info("one is {}", one.get());
    }

}