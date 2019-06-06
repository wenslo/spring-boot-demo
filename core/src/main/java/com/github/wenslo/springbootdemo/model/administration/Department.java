package com.github.wenslo.springbootdemo.model.administration;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.github.wenslo.springbootdemo.model.base.OrganizationBasicEntity;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018-12-31 23:45
 * @description 部门
 */
@Entity
public class Department extends OrganizationBasicEntity {
    /** 部门名称 **/
    private String name;
    /** 父部门 **/
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Department parentDepartment;
    /** 排序码 **/
    private Long sorted;
    /** 说明 **/
    private String comment;

    public Department() {
    }

    public Department(String name, Department parentDepartment, Long sorted, String comment) {
        this.name = name;
        this.parentDepartment = parentDepartment;
        this.sorted = sorted;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getParentDepartment() {
        return parentDepartment;
    }

    public void setParentDepartment(Department parentDepartment) {
        this.parentDepartment = parentDepartment;
    }

    public Long getSorted() {
        return sorted;
    }

    public void setSorted(Long sorted) {
        this.sorted = sorted;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override public String toString() {
        return "Department{" + "name='" + name + '\'' + ", parentDepartment=" + parentDepartment + ", sorted=" + sorted
            + ", comment='" + comment + '\'' + '}';
    }
}
