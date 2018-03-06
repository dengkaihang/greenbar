package com.qawhcb.shadow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/5 <br/>
 */
@Service
public class TestServer {



    @Transactional(rollbackFor = Exception.class)
    public int test(String name, int id){

        orderDao.er("exception", id);

//        int i = 1/0;

        return orderDao.updateNameById(name, id);
    }

    @Autowired
    private OrderDao orderDao;

}
