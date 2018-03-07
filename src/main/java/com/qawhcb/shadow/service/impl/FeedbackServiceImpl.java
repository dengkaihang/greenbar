package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IFeedbackDao;
import com.qawhcb.shadow.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class FeedbackServiceImpl implements IFeedbackService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iFeedbackDao.delete(id);
    }

    @Autowired
    private IFeedbackDao iFeedbackDao;
}
