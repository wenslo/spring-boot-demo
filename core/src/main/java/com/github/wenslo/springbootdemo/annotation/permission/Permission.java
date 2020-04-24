package com.github.wenslo.springbootdemo.annotation.permission;

import java.lang.annotation.*;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月10日 上午10:14
 * @description
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Permission {
    String value();

    String group();
}
