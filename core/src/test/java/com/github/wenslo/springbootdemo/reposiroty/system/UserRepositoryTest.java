package com.github.wenslo.springbootdemo.reposiroty.system;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.wenslo.springbootdemo.DBTestCase;
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
public class UserRepositoryTest extends DBTestCase {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void testFindAll() {
        List<User> list = userRepository.findAll();
        logger.debug("list size is {}", list.size());
        logger.debug("list data is {}", list);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    public void testFindByUsername() {
        String username = "user1";
        User user = userRepository.findByUsername(username);
        Assert.assertNotNull(user);
        logger.debug("username is {} , findByUsername result is {},organizations is {}", user.getUsername(), user, user.getOrganizations());
    }

    @Test
    public void testQuerydsl() {
        String username = "user1";
        QUser user = QUser.user;
        BooleanExpression booleanExpression = user.username.startsWith(username);
        Optional one = userRepository.findOne(booleanExpression);
        Assert.assertTrue(one.isPresent());
        logger.debug("one is {}", one.get());
    }

    @Test
    public void testLastModifyTime() {
        Long id = -1L;
        User user = userRepository.findById(id).orElse(null);
        Assert.assertNotNull(user);
        logger.debug("The before data last modify time  is {}", user.getUpdatedAt());
        user.setUsername("Warren Wen");
        User changedUser = userRepository.save(user);
        logger.debug("The after data last modify time  is {}", changedUser.getUpdatedAt());
    }

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("Warren wen");
        user.setPassword("1234");
        User result = userRepository.save(user);
        logger.debug("test save parameter is {} , result is {}", user, result);
    }
}