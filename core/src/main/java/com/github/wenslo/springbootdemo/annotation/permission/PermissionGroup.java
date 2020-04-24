package com.github.wenslo.springbootdemo.annotation.permission;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月10日 上午10:14
 * @description
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Component
public @interface PermissionGroup {
}
