package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IPostCommentDao;
import com.qawhcb.shadow.dao.IPostDao;
import com.qawhcb.shadow.service.IPostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private IPostCommentDao iPostCommentDao;
}
