package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IUseCouponDao;
import com.qawhcb.shadow.service.IUseCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class UseCouponServiceImpl implements IUseCouponService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iUseCouponDao.delete(id);
    }

    @Autowired
    private IUseCouponDao iUseCouponDao;
}
