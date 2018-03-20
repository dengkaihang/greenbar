package com.qawhcb.shadow.entity.dataModel;

import com.qawhcb.shadow.entity.Post;

/**
 * @author Taoism <br/>
 * Created on 2018/3/20 <br/>
 */
public class PostVo {

    private Post post;
    private String userName;
    private String userImg;
    private String commentNum;

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }
}
