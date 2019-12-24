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
public class Permission implements Serializable, Cloneable {

    /** 权限实际值 **/
    private String action;
    /** 权限描述 **/
    private String describe;
    private List<Permission> actions;

    private String parentAction;
    private String parentDescribe;

    public Permission() {
    }

    public Permission(String action, String describe) {
        this.action = action;
        this.describe = describe;
    }

    public Permission builderParent(String parentAction, String parentDescribe) {
        this.parentAction = parentAction;
        this.parentDescribe = parentDescribe;
        return this;
    }

    public Permission buildActions(List<Permission> actions) {
        this.actions = actions;
        return this;
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

    public String getParentAction() {
        return parentAction;
    }

    public void setParentAction(String parentAction) {
        this.parentAction = parentAction;
    }

    public String getParentDescribe() {
        return parentDescribe;
    }

    public void setParentDescribe(String parentDescribe) {
        this.parentDescribe = parentDescribe;
    }

    @Override
    public Permission clone() {
        Permission permission = null;
        try {
            permission = (Permission) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return permission;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("action", action)
            .append("describe", describe)
            .append("actions", actions)
            .append("parentAction", parentAction)
            .append("parentDescribe", parentDescribe)
            .toString();
    }
}
