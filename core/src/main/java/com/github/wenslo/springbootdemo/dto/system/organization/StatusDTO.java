package com.github.wenslo.springbootdemo.dto.system.organization;


import com.github.wenslo.springbootdemo.enums.common.EnableFlag;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-03-20 11:09
 * @description
 */
public class StatusDTO implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private EnableFlag enabled;
}
