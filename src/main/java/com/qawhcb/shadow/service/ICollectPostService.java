package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.CollectPost;
import com.qawhcb.shadow.entity.dataModel.PostVo;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface ICollectPostService {

    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 收藏发帖
     * @param userId 用户id
     * @param postId 帖子id
     * @return 收藏保存结果
     */
    CollectPost save(Integer userId, Integer postId);

    /**
     * 查询用户收藏的所有帖子
     * @param userId 用户数据
     * @return 查询到的用户收藏的帖子
     */
    List<PostVo> find(Integer userId);

    /**
     * 取消收藏
     * @param userId 用户id
     * @param postId 帖子id
     */
    void deleteByUserIdAndPostId(Integer userId, Integer postId);
}
