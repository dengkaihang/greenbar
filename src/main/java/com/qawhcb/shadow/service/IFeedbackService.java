package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Feedback; /**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IFeedbackService {


    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 用户提交反馈
     * @param feedback 反馈内容
     * @return 提交的内容
     */
    public Feedback save(Feedback feedback);
}
