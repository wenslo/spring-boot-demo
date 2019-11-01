package com.github.wenslo.springbootdemo.condition.system;

import java.io.Serializable;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-02 10:53
 * @description 机构查询条件
 */
public class OrganizationCondition extends PageCondition implements Serializable {
    private String name;
    private String districtCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    @Override
    public String toString() {
        return "OrganizationCondition{" + "name='" + name + '\'' + ", districtCode='" + districtCode + '\'' + '}';
    }
}
