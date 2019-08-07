package com.github.wenslo.springbootdemo.dto.system.organization;

import com.github.wenslo.springbootdemo.dto.LongIdDTO;
import com.github.wenslo.springbootdemo.enums.common.EnableFlag;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-03-20 16:55
 * @description
 */
public class OrganizationPageDTO extends LongIdDTO {
    /** 驾校简称 **/
    private String name;
    /** 驾校全称 **/
    private String fullName;
    /** 管理员账号 **/
    private String administratorAccount;
    /** 启用状态 **/
    private EnableFlag enableFlag;
}
