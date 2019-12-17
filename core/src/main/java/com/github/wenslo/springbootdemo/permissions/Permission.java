package com.github.wenslo.springbootdemo.permissions;

/**
 * 定义权限枚举
 */
public interface Permission {

    String getDescribe();

    String getGroup();

    String getGroupDescribe();
}
