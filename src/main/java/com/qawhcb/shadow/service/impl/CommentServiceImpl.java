package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.ICommentDao;
import com.qawhcb.shadow.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class CommentServiceImpl implements ICommentService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iCommentDao.delete(id);
    }

    @Autowired
    private ICommentDao iCommentDao;
}
