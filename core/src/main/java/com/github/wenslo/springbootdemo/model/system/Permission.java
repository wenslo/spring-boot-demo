package com.github.wenslo.springbootdemo.model.system;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018年12月10日 上午10:36
 * @description 权限DTO
 */
public class Permission implements Serializable {

    /** 权限实际值 **/
    private String action;
    /** 权限描述 **/
    private String describe;
    private List<Permission> actions;

    public Permission() {
    }

    public Permission(String action, String describe) {
        this.action = action;
        this.describe = describe;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<Permission> getActions() {
        return actions;
    }

    public void setActions(List<Permission> actions) {
        this.actions = actions;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("action", action)
            .append("describe", describe)
            .append("actions", actions)
            .toString();
    }
}
