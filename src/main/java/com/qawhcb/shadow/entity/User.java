package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * 用户
 * Created by kane on 18-3-2
 */
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private Integer id;          //id
    @Column(name = "nick_name", length = 12)
    private String nickName;    //昵称
    @Column(name = "password", length = 32)
    private String password;    //密码
    @Column(name = "phone", length = 11, unique = true)
    private String phone;       //电话
    @Column(name = "qq", length = 13)
    private String qq;          //ＱＱ
    @Column(name = "wechat", length = 32)
    private String wechat;      //微信
    @Column(name = "registration_time", length = 32)
    private String registrationTime;      //注册时间
    @Column(name = "autograph", length = 64)
    private String autograph;   //签名
    @Column(name = "rank", length = 4)
    private String rank;        //等级
    @Column(name = "sex", length = 2)
    private String sex;         //性别
    @Column(name = "portrait", length = 128)
    private String portrait;    //头像
    @Column(name = "qr_code", length = 256)
    private String qrCode;      //二维码
    @Column(name = "token", length = 64)
    private String token;       //ｔｏｋｅｎ验证
    @Column(name = "if_del", columnDefinition = "varchar(8) default 'false'")
    private String ifDel = "false";       //是否删除
    @Column(name = "label1", length = 32)
    private String label1;      //标签
    @Column(name = "label2", length = 32)
    private String label2;      //
    @Column(name = "label3", length = 32)
    private String label3;      //

    public User() {
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
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
        return "User{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", wechat='" + wechat + '\'' +
                ", qq='" + qq + '\'' +
                ", autograph='" + autograph + '\'' +
                ", rank='" + rank + '\'' +
                ", sex='" + sex + '\'' +
                ", portrait='" + portrait + '\'' +
                ", qrCode='" + qrCode + '\'' +
                ", ifDel='" + ifDel + '\'' +
                ", label1='" + label1 + '\'' +
                ", label2='" + label2 + '\'' +
                ", label3='" + label3 + '\'' +
                '}';
    }
}
