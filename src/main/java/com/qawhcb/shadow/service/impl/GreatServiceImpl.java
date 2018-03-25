package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IGreatDao;
import com.qawhcb.shadow.dao.IPostDao;
import com.qawhcb.shadow.entity.Great;
import com.qawhcb.shadow.entity.Post;
import com.qawhcb.shadow.service.IGreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/23 <br/>
 */
@Service
public class GreatServiceImpl implements IGreatService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Great click(Integer userId, Integer postId) {
        Great ispraise = iGreatDao.ispraise(userId, postId);

        Great save = null;

        if (ispraise == null) {
            Great great = new Great();

            great.setUserId(userId);
            great.setPostId(postId);
            great.setIfPraise("true");

            save =iGreatDao.save(great);

            if (save != null) {
                setPostLaudCount(postId, 1);
            }

        } else {
            if (ispraise.getIfPraise().equals("true")) {

                ispraise.setIfPraise("false");

                save = iGreatDao.save(ispraise);

                if (save != null) {
                    setPostLaudCount(postId, -1);
                }

            } else{
                ispraise.setIfPraise("true");

                save = iGreatDao.save(ispraise);

                if (save != null) {
                    setPostLaudCount(postId, 1);
                }
            }
        }

        return save;
    }

    /**
     * 设置文章点赞数
     *
     * @param postId      文章id
     * @param greatStatus 点赞状态（1 点赞， -1 取消点赞）
     */
    private void setPostLaudCount(Integer postId, int greatStatus) {
        Post post = iPostDao.findOne(postId);

        int i = post.getLaudCount();

        if (greatStatus == 1) {
            post.setLaudCount(i + 1);
        } else {
            if (i > 0) {
                post.setLaudCount(i - 1);
            } else {
                post.setLaudCount(0);
            }
        }
        iPostDao.save(post);
    }

    @Autowired
    private IGreatDao iGreatDao;

    @Autowired
    private IPostDao iPostDao;
}
