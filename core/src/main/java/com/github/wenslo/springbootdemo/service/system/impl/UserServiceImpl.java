package com.github.wenslo.springbootdemo.service.system.impl;

import com.github.wenslo.fluent.data.service.impl.LongIdServiceImpl;
import com.github.wenslo.springbootdemo.condition.system.UserCondition;
import com.github.wenslo.springbootdemo.model.system.QUser;
import com.github.wenslo.springbootdemo.model.system.User;
import com.github.wenslo.springbootdemo.service.system.UserService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月04日 上午11:06
 * @description
 */
@Service
@Transactional
public class UserServiceImpl extends LongIdServiceImpl<User, UserCondition> implements UserService {

    @Override
    protected List<Predicate> conditionBuild(UserCondition condition) {
        List<Predicate> conditionBuilder = super.conditionBuild(condition);
        BooleanBuilder builder = new BooleanBuilder();
        QUser user = QUser.user;
        String username = condition.getUsername();
        if (StringUtils.isNoneBlank(username)) {
            builder.and(user.username.startsWith(username));
        }
        Boolean enabled = condition.getEnabled();
        if (Objects.nonNull(enabled)) {
            builder.and(user.enabled.eq(enabled));
        }
        conditionBuilder.add(builder);
        return conditionBuilder;
    }
}
