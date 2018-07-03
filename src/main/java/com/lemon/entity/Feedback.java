package com.lemon.entity;

import javax.persistence.*;

/**
 * 反馈表
 * Created by kane on 18-6-29
 */
@Entity
@Table(name = "t_feedback")
public class Feedback {

    private Integer id;
    private Integer userId;  //用户id
    private String text;    //反馈内容
    private String time;    //反馈时间

    public Feedback() {
    }

    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    public Integer getId() {
        return id;
    }
    @Column(name = "user_id", length = 11)
    public Integer getUserId() {
        return userId;
    }
    @Column(name = "text", length = 256)
    public String getText() {
        return text;
    }
    @Column(name = "time", length = 32)
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
