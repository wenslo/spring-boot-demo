package com.github.wenslo.springbootdemo.condition.system;


import com.github.wenslo.fluent.core.condition.LongIdCondition;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-01 20:07
 * @description
 */
public class DistrictCondition extends LongIdCondition {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DistrictCondition{" + "name='" + name + '\'' + '}';
    }
}
