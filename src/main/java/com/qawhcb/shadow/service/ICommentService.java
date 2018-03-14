package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Comment;
import com.qawhcb.shadow.entity.dataModel.CommentVo;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface ICommentService {

    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 查询商品所有评论
     * @param goodsId
     */
    public List<CommentVo> findAll(int goodsId);
}
