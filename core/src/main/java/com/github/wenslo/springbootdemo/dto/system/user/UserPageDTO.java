package com.github.wenslo.springbootdemo.dto.system.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-03-19 12:59
 * @description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserPageDTO extends SimpleUser {

    private Boolean enabled;

}
