package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IComplainDao;
import com.qawhcb.shadow.service.IComplainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class ComplainServiceImpl implements IComplainService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iComplainDao.delete(id);
    }

    @Autowired
    private IComplainDao iComplainDao;
}
