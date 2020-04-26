package com.github.wenslo.springbootdemo.condition.system;

import com.github.wenslo.fluent.core.condition.PageCondition;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月12日 上午10:32
 * @description
 */
public class RoleCondition extends PageCondition {
    private String name;
    private Boolean enabled;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("enabled", enabled)
                .toString();
    }
}
