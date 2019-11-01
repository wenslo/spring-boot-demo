package com.github.wenslo.springbootdemo.condition.system;

import com.github.wenslo.fluent.core.condition.PageCondition;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月12日 上午10:32
 * @description
 */
public class RoleCondition extends PageCondition {
    private String name;
    private Boolean eanbled;

    public Boolean getEanbled() {
        return eanbled;
    }

    public void setEanbled(Boolean eanbled) {
        this.eanbled = eanbled;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleCondition{" +
                "name='" + name + '\'' +
                ", eanbled=" + eanbled +
                '}';
    }
}
