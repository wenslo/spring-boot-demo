package com.github.wenslo.springbootdemo.model.system;

import javax.persistence.Entity;

import com.github.wenslo.springbootdemo.model.base.LongIdEntity;

import jdk.nashorn.internal.ir.annotations.Immutable;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018-12-31 17:02
 * @description 地区，采用国家统一编码
 */
@Entity
@Immutable
public class District extends LongIdEntity {
    /** 编码 **/
    private String code;
    /** 名称 **/
    private String name;
    /** 父地区编码 **/
    private String parentCode;
    /** 邮政编码 **/
    private String zipCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "District{" + "code='" + code + '\'' + ", name='" + name + '\'' + ", parentCode='" + parentCode + '\''
            + ", zipCode='" + zipCode + '\'' + '}';
    }
}
