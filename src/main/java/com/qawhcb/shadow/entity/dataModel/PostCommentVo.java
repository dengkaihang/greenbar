package com.qawhcb.shadow.entity.dataModel;

import com.qawhcb.shadow.entity.PostComment;

/**
 * @author Taoism <br/>
 * Created on 2018/3/24 <br/>
 */
public class PostCommentVo {

    private PostComment postComment;

    private String nickName;

    private String portrait;

    public PostComment getPostComment() {
        return postComment;
    }

    public void setPostComment(PostComment postComment) {
        this.postComment = postComment;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }
}
