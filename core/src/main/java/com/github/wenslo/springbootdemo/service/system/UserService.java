package com.github.wenslo.springbootdemo.service.system;

import com.github.wenslo.fluent.data.service.LongIdService;
import com.github.wenslo.springbootdemo.condition.system.UserCondition;
import com.github.wenslo.springbootdemo.model.system.User;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年11月24日 下午3:58
 * @description
 */
public interface UserService extends LongIdService<User, UserCondition> {
}
