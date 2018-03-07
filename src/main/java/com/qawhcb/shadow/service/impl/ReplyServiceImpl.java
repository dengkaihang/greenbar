package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IReplyDao;
import com.qawhcb.shadow.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class ReplyServiceImpl implements IReplyService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iReplyDao.delete(id);
    }

    @Autowired
    private IReplyDao iReplyDao;
}
