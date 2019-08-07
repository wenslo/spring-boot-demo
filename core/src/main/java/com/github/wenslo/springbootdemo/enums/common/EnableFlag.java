package com.github.wenslo.springbootdemo.enums.common;


import com.github.wenslo.springbootdemo.enums.BaseEnum;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-03-20 14:35
 * @description
 */
public enum EnableFlag implements BaseEnum {
    ENABLE("启用"), DISABLE("禁用");

    private String label;

    EnableFlag(String label) {
        this.label = label;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}

