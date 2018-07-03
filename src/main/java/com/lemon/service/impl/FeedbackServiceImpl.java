package com.lemon.service.impl;

import com.lemon.dao.IFeedbackDao;
import com.lemon.entity.Feedback;
import com.lemon.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kane on 18-7-3
 */
@Service
public class FeedbackServiceImpl implements IFeedbackService {

    @Autowired
    private IFeedbackDao iFeedbackDao;

    @Override
    public Feedback save(Feedback feedback) {
        return iFeedbackDao.save(feedback);
    }
}
