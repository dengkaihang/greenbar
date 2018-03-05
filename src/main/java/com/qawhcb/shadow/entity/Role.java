package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * 角色类
 * Created by kane on 18-3-5
 */
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private Integer id;             //id
    @Column(name = "name", length = 8)
    private String name;            //角色名称
    @Column(name = "jurisdiction", length = 8)
    private String jurisdiction;    //权限
    @Column(name = "operation_data", length = 8)
    private String operationData;   //操作数据库
    @Column(name = "if_del", columnDefinition = "varchar(8) default 'false'")
    private String ifDel;           //是否删除
    @Column(name = "lable1", length = 32)
    private String lable1;          //标签
    @Column(name = "lable2", length = 32)
    private String lable2;
    @Column(name = "lable3", length = 32)
    private String lable3;

    public Role() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public String getOperationData() {
        return operationData;
    }

    public void setOperationData(String operationData) {
        this.operationData = operationData;
    }

    public String getIfDel() {
        return ifDel;
    }

    public void setIfDel(String ifDel) {
        this.ifDel = ifDel;
    }

    public String getLable1() {
        return lable1;
    }

    public void setLable1(String lable1) {
        this.lable1 = lable1;
    }

    public String getLable2() {
        return lable2;
    }

    public void setLable2(String lable2) {
        this.lable2 = lable2;
    }

    public String getLable3() {
        return lable3;
    }

    public void setLable3(String lable3) {
        this.lable3 = lable3;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jurisdiction='" + jurisdiction + '\'' +
                ", operationData='" + operationData + '\'' +
                ", ifDel='" + ifDel + '\'' +
                ", lable1='" + lable1 + '\'' +
                ", lable2='" + lable2 + '\'' +
                ", lable3='" + lable3 + '\'' +
                '}';
    }
}
