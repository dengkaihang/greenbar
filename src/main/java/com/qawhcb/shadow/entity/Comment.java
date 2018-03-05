package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * 评价类
 * Created by kane on 18-3-4
 */
@Entity
@Table(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private String id;              //id
    @Column(name = "order_id", length = 11)
    private String orderId;         //订单id
    @Column(name = "text", length = 256)
    private String text;            //评价内容
    @Column(name = "comment_time", length = 32)
    private String commentTime;     //评价时间
    @Column(name = "describe", length = 128)
    private String describe;        //描述
    @Column(name = "img", length = 256)
    private String img;             //图片
    @Column(name = "service", length = 2)
    private String service;         //服务
    @Column(name = "speed", length = 2)
    private String speed;           //速度
    @Column(name = "total_points", length = 2)
    private String totalPoints;     //总分
    @Column(name = "if_del", columnDefinition = "varchar(8) default 'false'")
    private String ifDel;           //是否删除
    @Column(name = "lable1", length = 32)
    private String lable1;          //标签
    @Column(name = "lable2", length = 32)
    private String lable2;
    @Column(name = "lable3", length = 32)
    private String lable3;

    public Comment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
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
        return "Comment{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", text='" + text + '\'' +
                ", commentTime='" + commentTime + '\'' +
                ", describe='" + describe + '\'' +
                ", img='" + img + '\'' +
                ", service='" + service + '\'' +
                ", speed='" + speed + '\'' +
                ", totalPoints='" + totalPoints + '\'' +
                ", ifDel='" + ifDel + '\'' +
                ", lable1='" + lable1 + '\'' +
                ", lable2='" + lable2 + '\'' +
                ", lable3='" + lable3 + '\'' +
                '}';
    }
}