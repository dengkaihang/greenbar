package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * 帖子
 * Created by kane on 18-3-4
 */
@Entity
@Table(name = "t_post")
public class Post {
    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private Integer id;              //id
    @Column(name = "user_id", length = 11)
    private Integer userId;          //用户id
    @Column(name = "img", length = 256)
    private String img;             //图片
    @Column(name = "text", length = 512)
    private String text;            //文字
    @Column(name = "laud_time", length = 32)
    private String laudTime;        //点赞次数
    @Column(name = "follow_num", length = 8)
    private String followNum;       //关注人数
    @Column(name = "publish_time", length = 32)
    private String publishTime;     //发布时间
    @Column(name = "publish_address", length = 16)
    private String publishAddress;  //发布位置
    @Column(name = "if_del", columnDefinition = "varchar(8) default 'false'")
    private String ifDel = "false";           //是否删除
    @Column(name = "lable1", length = 32)
    private String lable1;          //标签1
    @Column(name = "lable2", length = 32)
    private String lable2;
    @Column(name = "lable3", length = 32)
    private String lable3;

    public Post() {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLaudTime() {
        return laudTime;
    }

    public void setLaudTime(String laudTime) {
        this.laudTime = laudTime;
    }

    public String getFollowNum() {
        return followNum;
    }

    public void setFollowNum(String followNum) {
        this.followNum = followNum;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishAddress() {
        return publishAddress;
    }

    public void setPublishAddress(String publishAddress) {
        this.publishAddress = publishAddress;
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
        return "Post{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", img='" + img + '\'' +
                ", text='" + text + '\'' +
                ", laudTime='" + laudTime + '\'' +
                ", followNum='" + followNum + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", publishAddress='" + publishAddress + '\'' +
                ", ifDel='" + ifDel + '\'' +
                ", lable1='" + lable1 + '\'' +
                ", lable2='" + lable2 + '\'' +
                ", lable3='" + lable3 + '\'' +
                '}';
    }
}
