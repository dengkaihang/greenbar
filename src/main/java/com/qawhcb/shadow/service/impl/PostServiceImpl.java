package com.qawhcb.shadow.service.impl;

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
public class PostServiceImpl implements IPostCommentService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iPostDao.delete(id);
    }

    @Autowired
    private IPostDao iPostDao;
}
