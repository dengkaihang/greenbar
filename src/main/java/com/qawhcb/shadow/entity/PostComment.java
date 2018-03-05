package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * 帖子评论
 * Created by kane on 18-3-4
 */
@Entity
@Table(name = "t_post_comment")
public class PostComment {

    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private String id;              //id
    @Column(name = "post_id", length = 11)
    private String postId;          //帖子id
    @Column(name = "user_id", length = 11)
    private String userId;          //用户id
    @Column(name = "text", length = 256)
    private String text;            //内容
    @Column(name = "time", length = 32)
    private String time;            //评论时间
    @Column(name = "if_del", columnDefinition = "varchar(8) default 'false'")
    private String ifDel;           //是否删除
    @Column(name = "lable1", length = 32)
    private String lable1;          //标签
    @Column(name = "lable2", length = 32)
    private String lable2;
    @Column(name = "lable3", length = 32)
    private String lable3;

    public PostComment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
        return "PostComment{" +
                "id='" + id + '\'' +
                ", postId='" + postId + '\'' +
                ", userId='" + userId + '\'' +
                ", text='" + text + '\'' +
                ", time='" + time + '\'' +
                ", ifDel='" + ifDel + '\'' +
                ", lable1='" + lable1 + '\'' +
                ", lable2='" + lable2 + '\'' +
                ", lable3='" + lable3 + '\'' +
                '}';
    }
}