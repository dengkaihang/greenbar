package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IPackDao;
import com.qawhcb.shadow.service.IPackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class PackServiceImpl implements IPackService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iPackDao.delete(id);
    }

    @Autowired
    private IPackDao iPackDao;
}
