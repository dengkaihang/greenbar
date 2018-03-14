package com.qawhcb.shadow.entity.dataModel;


import com.qawhcb.shadow.entity.Comment;
import com.qawhcb.shadow.entity.User;

/**
 * @author Taoism <br/>
 * Created on 2018/3/14 <br/>
 * 评论数据封装
 */
public class CommentVo {

    private Comment comment;
    private User user;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
