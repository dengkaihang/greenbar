package com.lemon.service;

import com.lemon.entity.Feedback;

/**
 * Created by kane on 18-7-3
 */
public interface IFeedbackService {

    /**
     * 添加反馈
     * @param feedback
     * @return
     */
    public Feedback save(Feedback feedback);
}
