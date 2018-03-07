package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IActivityDao;
import com.qawhcb.shadow.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class ActivityServiceImpl implements IActivityService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iActivityDao.delete(id);
    }

    @Autowired
    private IActivityDao iActivityDao;
}
