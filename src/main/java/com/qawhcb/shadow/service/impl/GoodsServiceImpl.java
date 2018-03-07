package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IGoodsDao;
import com.qawhcb.shadow.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iGoodsDao.delete(id);
    }

    @Autowired
    private IGoodsDao iGoodsDao;
}
