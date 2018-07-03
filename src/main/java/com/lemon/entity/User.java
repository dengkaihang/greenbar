package com.lemon.entity;

import javax.persistence.*;

/**
 * 用户表
 * Created by kane on 18-6-29
 */
@Entity
@Table(name = "t_user")
public class User {

    private Integer id;
    private String portrait;    //头像
    private String nickname;    //昵称
    private String phone;       //电话号码

    public User() {
    }

    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    public Integer getId() {
        return id;
    }
    @Column(name = "portrait", length = 64)
    public String getPortrait() {
        return portrait;
    }
    @Column(name = "nickname", length = 32)
    public String getNickname() {
        return nickname;
    }
    @Column(name = "phone", length = 11)
    public String getPhone() {
        return phone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", portrait='" + portrait + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
