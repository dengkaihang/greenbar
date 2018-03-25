package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.PostComment;
import com.qawhcb.shadow.entity.dataModel.PostCommentVo;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IPostCommentService {

    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 保存帖子评论
     * @param postComment 评论对象
     * @return 保存结果
     */
    PostComment save(PostComment postComment);

    /**
     * 通过id 查询所有评论
     * @param postId 帖子id
     * @return 评论列表
     */
    List<PostCommentVo> findByPostId(Integer postId);
}
