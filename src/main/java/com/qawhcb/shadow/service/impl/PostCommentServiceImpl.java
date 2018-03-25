package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IPostCommentDao;
import com.qawhcb.shadow.dao.IPostDao;
import com.qawhcb.shadow.dao.IUserDao;
import com.qawhcb.shadow.entity.PostComment;
import com.qawhcb.shadow.entity.User;
import com.qawhcb.shadow.entity.dataModel.PostCommentVo;
import com.qawhcb.shadow.service.IPostCommentService;
import com.qawhcb.shadow.utils.GetLocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class PostCommentServiceImpl implements IPostCommentService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iPostCommentDao.delete(id);
    }

    @Override
    public PostComment save(PostComment postComment) {

        postComment.setTime(GetLocalDateTime.getLocalDataTime());

        return iPostCommentDao.save(postComment);
    }

    @Override
    public List<PostCommentVo> findByPostId(Integer postId) {

        List<PostComment> byPostId = iPostCommentDao.findByPostId(postId);

        List<PostCommentVo> postCommentVos = new ArrayList<>(16);


        PostCommentVo postCommentVo = null;
        // 封装评论用户
        for (PostComment p :
                byPostId) {

            postCommentVo = new PostCommentVo();

            postCommentVo.setPostComment(p);

            User user = iUserDao.findOne(p.getUserId());


            postCommentVo.setNickName(user.getNickName());
            postCommentVo.setPortrait(user.getPortrait());

            postCommentVos.add(postCommentVo);
        }

        return postCommentVos;
    }

    @Autowired
    private IPostCommentDao iPostCommentDao;

    @Autowired
    private IUserDao iUserDao;
}
