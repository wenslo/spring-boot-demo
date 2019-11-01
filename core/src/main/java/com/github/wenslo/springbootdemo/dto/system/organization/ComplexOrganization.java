package com.github.wenslo.springbootdemo.dto.system.organization;

import com.github.wenslo.fluent.core.dto.LongIdDTO;
import com.github.wenslo.springbootdemo.enums.common.EnableFlag;

import java.time.LocalDate;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-03-28 10:06
 * @description
 */
public class ComplexOrganization extends LongIdDTO {
    /** 驾校简称 **/
    private String name;
    /** 驾校全称 **/
    private String fullName;
    /** 省 **/
    private Integer provinceCode;
    /** 市 **/
    private Integer cityCode;
    /** 区 **/
    private Integer areaCode;
    /** 详细地址 **/
    private String address;
    /** 主要联系人姓名 **/
    private String majorContactName;
    /** 主要联系人手机 **/
    private String majorContactPhone;
    /** 次要联系人姓名 **/
    private String minorContactName;
    /** 次要联系人手机 **/
    private String minorContactPhone;
    /** 统一社会信用代码 **/
    private String identification;
    /** 法定代表人 **/
    private String legalRepresentative;
    /** 注册地址 **/
    private String registeredAddress;
    /** 注册资本 **/
    private String registeredCapital;
    /** 成立日期 **/
    private String registeredDate;
    /** 营业期限 开始日期 **/
    private LocalDate businessStartDate;
    /** 营业期限 结束日期 **/
    private LocalDate businessEndDate;
    /** 管理员账号 **/
    private String administratorAccount;
    /** 启用状态 **/
    private EnableFlag enableFlag;
}
