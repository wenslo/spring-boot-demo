package com.github.wenslo.springbootdemo.dto.system;

import javax.validation.constraints.NotNull;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-08-07 23:09
 * @description
 */
public class StatusDTO {
    @NotNull
    private Long id;
    @NotNull
    private Boolean enabled;
}
