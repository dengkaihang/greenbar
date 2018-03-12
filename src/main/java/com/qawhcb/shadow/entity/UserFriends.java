package com.qawhcb.shadow.entity;

import io.swagger.models.auth.In;

import javax.persistence.*;

/**
 * 用户好友
 * Created by kane on 18-3-5
 */
@Entity
@Table(name = "t_user_friends")
public class UserFriends {
    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private Integer id;              //id
    @Column(name = "friend_id", length = 11)
    private Integer frienId;
    @Column(name = "user_id", length = 11)
    private Integer userId;
    @Column(name = "status", length = 4)
    private String status;
    @Column(name = "if_del", columnDefinition = "varchar(8) default 'false'")
    private String ifDel = "false";           //是否删除
    @Column(name = "lable1", length = 32)
    private String lable1;          //标签
    @Column(name = "lable2", length = 32)
    private String lable2;
    @Column(name = "lable3", length = 32)
    private String lable3;

    public UserFriends() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrienId() {
        return frienId;
    }

    public void setFrienId(Integer frienId) {
        this.frienId = frienId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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
        return "UserFriends{" +
                "id=" + id +
                ", frienId=" + frienId +
                ", userId=" + userId +
                ", status='" + status + '\'' +
                ", ifDel='" + ifDel + '\'' +
                ", lable1='" + lable1 + '\'' +
                ", lable2='" + lable2 + '\'' +
                ", lable3='" + lable3 + '\'' +
                '}';
    }
}
