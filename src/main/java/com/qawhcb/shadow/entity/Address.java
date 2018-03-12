package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * 常用地址
 * Created by kane on 18-3-4
 */
@Entity
@Table(name = "t_address")
public class Address {
    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private Integer id;              //id
    @Column(name = "user_id", length = 11)
    private Integer userId;          //用户id
    @Column(name = "region", length = 12)
    private String region;          //所在地区
    @Column(name = "detailed_address", length = 128)
    private String detailedAddress; //详细地址
    @Column(name = "mark", length = 32 )
    private String mark;            //标志
    @Column(name = "phone", length = 11)
    private String phone;           //电话
    @Column(name = "addressee", length = 6)
    private String addressee;       //收货人
    @Column(name = "if_del", columnDefinition = "varchar(8) default 'false'")
    private String ifDel = "false";       //是否删除
    @Column(name = "label1", length = 32)
    private String label1;          //标签
    @Column(name = "label2", length = 32)
    private String lable2;          //
    @Column(name = "label3", length = 32)
    private String label3;

    public String getIfDel() {
        return ifDel;
    }

    public void setIfDel(String ifDel) {
        this.ifDel = ifDel;
    }

    public Address() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLable2() {
        return lable2;
    }

    public void setLable2(String lable2) {
        this.lable2 = lable2;
    }

    public String getLabel3() {
        return label3;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", region='" + region + '\'' +
                ", detailedAddress='" + detailedAddress + '\'' +
                ", mark='" + mark + '\'' +
                ", phone='" + phone + '\'' +
                ", addressee='" + addressee + '\'' +
                ", label1='" + label1 + '\'' +
                ", lable2='" + lable2 + '\'' +
                ", label3='" + label3 + '\'' +
                '}';
    }
}
