package com.github.wenslo.springbootdemo.model.system;

import com.github.wenslo.fluent.data.model.LongIdEntity;
import com.github.wenslo.springbootdemo.enums.common.DeleteFlag;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * @author wenhailin
 * @version 0.0.1
 * @createTime 2018-12-31 16:13
 * @description 机构（学校，组织）
 */
@Entity
@SQLDelete(sql = "update organization set delete_flag = 'DELETED' where id = ?", check = ResultCheckStyle.COUNT)
@Where(clause = "delete_flag <> 'DELETED'")
public class Organization extends LongIdEntity {
    /**
     * 全称
     **/
    private String fullName;
    /**
     * 简称
     **/
    private String name;
    /**
     * 地区
     **/
    private String districtCode;
    /**
     * 联系地址
     **/
    private String address;
    /**
     * 联系人名称
     **/
    private String contractName;
    /**
     * 联系人电话
     **/
    private String contractPhone;
    @Enumerated(EnumType.STRING)
    private DeleteFlag deleteFlag;
    @Transient
    private List<User> users;

    @PreRemove
    public void preRemove() {
        this.deleteFlag = DeleteFlag.DELETED;
    }

    public Organization() {
    }

    public Organization(String fullName, String name, String districtCode, String address, String contractName,
                        String contractPhone, DeleteFlag deleteFlag) {
        this.fullName = fullName;
        this.name = name;
        this.districtCode = districtCode;
        this.address = address;
        this.contractName = contractName;
        this.contractPhone = contractPhone;
        this.deleteFlag = deleteFlag;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractPhone() {
        return contractPhone;
    }

    public void setContractPhone(String contractPhone) {
        this.contractPhone = contractPhone;
    }


    public DeleteFlag getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(DeleteFlag deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Organization{" + "fullName='" + fullName + '\'' + ", name='" + name + '\'' + ", districtCode='"
                + districtCode + '\'' + ", address='" + address + '\'' + ", contractName='" + contractName + '\''
                + ", contractPhone='" + contractPhone + '\''
                + ", deleteFlag=" + deleteFlag + '}';
    }
}
