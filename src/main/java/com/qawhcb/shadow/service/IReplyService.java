package com.qawhcb.shadow.service;

import com.qawhcb.shadow.dao.IReplyDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IReplyService {

    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);
}
