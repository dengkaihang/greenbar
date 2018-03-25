package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.ICollectPostDao;
import com.qawhcb.shadow.dao.IPostDao;
import com.qawhcb.shadow.dao.IUserDao;
import com.qawhcb.shadow.entity.CollectPost;
import com.qawhcb.shadow.entity.Post;
import com.qawhcb.shadow.entity.User;
import com.qawhcb.shadow.entity.dataModel.PostVo;
import com.qawhcb.shadow.service.ICollectPostService;
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
public class CollectPostServiceImpl implements ICollectPostService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iCollectPostDao.delete(id);
    }

    @Override
    public CollectPost save(Integer userId, Integer postId) {

        // 查询用户之前是否收藏过此贴
        CollectPost fingByUserIdAndPostId = iCollectPostDao.fingByUserIdAndPostId(userId, postId);

        // 如果有记录，继续判断是否删除
        if (fingByUserIdAndPostId != null) {

            // 如果之前收藏，然后删除了。修改其删除状态
            if ("true".equals(fingByUserIdAndPostId.getIfDel())) {
                fingByUserIdAndPostId.setIfDel("false");

                return iCollectPostDao.save(fingByUserIdAndPostId);
            }else {
                // 如果之前收藏了，没有删除， 则不做任何处理
                return null;
            }
        }

        // 没有记录， 则新增收藏记录
        CollectPost collectPost = new CollectPost();

        collectPost.setUserId(userId);
        collectPost.setPostId(postId);

        return iCollectPostDao.save(collectPost);
    }

    @Override
    public List<PostVo> find(Integer userId) {
        List<CollectPost> byUserId = iCollectPostDao.findByUserId(userId);

        // 遍历收藏列表，查出所有帖子对象
        List<PostVo> postVos = new ArrayList<>(16);

        PostVo postVo = null;
        for (CollectPost c :
                byUserId) {

            postVo = new PostVo();

            Post post = iPostDao.findOne(c.getPostId());

            postVo.setPost(post);

            // 查询出发帖人
            User user = iUserDao.findOne(post.getUserId());

            postVo.setUserName(user.getNickName());
            postVo.setUserImg(user.getPortrait());

            postVos.add(postVo);
        }

        return postVos;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByUserIdAndPostId(Integer userId, Integer postId) {
        iCollectPostDao.deleteByUserIdAndPostId(userId, postId);
    }

    @Autowired
    private ICollectPostDao iCollectPostDao;

    @Autowired
    private IPostDao iPostDao;

    @Autowired
    private IUserDao iUserDao;
}
