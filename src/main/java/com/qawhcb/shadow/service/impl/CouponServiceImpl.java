package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.ICouponDao;
import com.qawhcb.shadow.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class CouponServiceImpl implements ICouponService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iCouponDao.delete(id);
    }

    @Autowired
    private ICouponDao iCouponDao;
}

