package com.github.wenslo.springbootdemo.dto.system;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2019-08-07 23:09
 * @description
 */
public class StatusDTO implements Serializable {
    @NotNull
    private Long id;
    @NotNull
    private boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "StatusDTO{" +
                "id=" + id +
                ", enabled=" + enabled +
                '}';
    }
}
