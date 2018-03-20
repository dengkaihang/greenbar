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

    private String depictNum;
    private String speedNum;
    private String serviceNum;
    private String  totalPointsNum;

    public String getDepictNum() {
        return depictNum;
    }

    public void setDepictNum(String depictNum) {
        this.depictNum = depictNum;
    }

    public String getSpeedNum() {
        return speedNum;
    }

    public void setSpeedNum(String speedNum) {
        this.speedNum = speedNum;
    }

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum;
    }

    public String getTotalPointsNum() {
        return totalPointsNum;
    }

    public void setTotalPointsNum(String totalPointsNum) {
        this.totalPointsNum = totalPointsNum;
    }

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
