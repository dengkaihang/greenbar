package com.qawhcb.shadow.entity;

import javax.persistence.*;

/**
 * @author Taoism <br/>
 * Created on 2018/3/23 <br/>
 * 用户文章点赞表
 */
@Entity
@Table(name = "t_great")
public class Great {

    // 点赞表id
    @Id
    @GeneratedValue()
    @Column(name = "id", columnDefinition = "int(11) UNSIGNED ZEROFILL AUTO_INCREMENT")
    private Integer id;

    // 帖子Id
    @Column(name = "post_id", length = 5)
    private Integer postId;

    // 用户Id
    @Column(name = "user_id", length = 5)
    private Integer userId;

    // 是否点赞
    @Column(name = "if_praise", length = 5)
    private String ifPraise;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIfPraise() {
        return ifPraise;
    }

    public void setIfPraise(String ifPraise) {
        this.ifPraise = ifPraise;
    }
}
