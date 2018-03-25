package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Great;

/**
 * @author Taoism <br/>
 * Created on 2018/3/23 <br/>
 */
public interface IGreatService {

    /**
     * 用户点击点赞按钮
     * @param userId 用户id
     * @param postId 文章id
     * @return 点赞结果
     */
    public Great click(Integer userId, Integer postId);
}
