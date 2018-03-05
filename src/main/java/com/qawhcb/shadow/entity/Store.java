package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * 店铺
 * Created by kane on 18-3-2
 */
@Entity
@Table(name = "t_store")
public class Store {

    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private Integer id;             //id
    @Column(name = "password", length = 16)
    private String password;        //密码
    @Column(name = "true_name", length = 8)
    private String trueName;       //真实姓名
    @Column(name = "id_card", length = 18)
    private String idCard;         //身份证号码
    @Column(name = "hand_id_card", length = 256)
    private String handIdCard;    //手持身份证照片
    @Column(name = "phone", length = 11)
    private String phone;           //手机号码
    @Column(name = "address", length = 128)
    private String address;         //技师长地址
    @Column(name = "store_name", length = 16)
    private String storeName;      //店铺名字
    @Column(name = "default_img", length = 256)
    private String defaultImg;     //店铺图片链接
    @Column(name = "rank", length = 4)
    private String rank;            //等级
    @Column(name = "describe", length = 256)
    private String describe;        //店铺描述
    @Column(name = "store_address", length = 128)
    private String storeAddress;   //店铺地址
    @Column(name = "remark", length = 128)
    private String remark;          //备注
    @Column(name = "employee_id", length = 11)
    private Integer employeeId;      //工作人员id
    @Column(name = "audit_time", length = 32)
    private String auditTime;      //审核时间
    @Column(name = "status", length = 8)
    private String status;          //状态
    @Column(name = "if_del", columnDefinition = "varchar(8) default 'false'")
    private String ifDel;           //是否删除
    @Column(name = "label1", length = 32)
    private String label1;          //标签１
    @Column(name = "label2", length = 32)
    private String label2;
    @Column(name = "label3", length = 32)
    private String label3;

    public Store() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getHandIdCard() {
        return handIdCard;
    }

    public void setHandIdCard(String handIdCard) {
        this.handIdCard = handIdCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(String defaultImg) {
        this.defaultImg = defaultImg;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIfDel() {
        return ifDel;
    }

    public void setIfDel(String ifDel) {
        this.ifDel = ifDel;
    }

    public String getLabel1() {
        return label1;
    }

    public void setLabel1(String label1) {
        this.label1 = label1;
    }

    public String getLabel2() {
        return label2;
    }

    public void setLabel2(String label2) {
        this.label2 = label2;
    }

    public String getLabel3() {
        return label3;
    }

    public void setLabel3(String label3) {
        this.label3 = label3;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", trueName='" + trueName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", handIdCard='" + handIdCard + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", storeName='" + storeName + '\'' +
                ", defaultImg='" + defaultImg + '\'' +
                ", rank='" + rank + '\'' +
                ", describe='" + describe + '\'' +
                ", storeAddress='" + storeAddress + '\'' +
                ", remark='" + remark + '\'' +
                ", employeeId=" + employeeId +
                ", auditTime='" + auditTime + '\'' +
                ", status='" + status + '\'' +
                ", ifDel='" + ifDel + '\'' +
                ", label1='" + label1 + '\'' +
                ", label2='" + label2 + '\'' +
                ", label3='" + label3 + '\'' +
                '}';
    }
}
