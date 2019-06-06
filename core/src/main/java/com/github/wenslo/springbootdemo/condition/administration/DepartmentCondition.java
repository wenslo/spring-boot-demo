package com.github.wenslo.springbootdemo.condition.administration;

import com.github.wenslo.springbootdemo.condition.base.OrganizationBasicCondition;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-01-06 14:05
 * @description 部门查询条件
 */
public class DepartmentCondition extends OrganizationBasicCondition {
    /** 部门名称 **/
    private String name;
    /** 父部门ID **/
    private Long parentId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "DepartmentCondition{" + "name='" + name + '\'' + ", parentId=" + parentId + ", pageable=" + pageable
            + ", id=" + id + ", ids=" + ids + ", createdAtStart=" + createdAtStart + ", createdAtEnd=" + createdAtEnd
            + ", updatedAtStart=" + updatedAtStart + ", updatedAtEnd=" + updatedAtEnd + '}';
    }
}
