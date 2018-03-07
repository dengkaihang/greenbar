package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IEmployeeDao;
import com.qawhcb.shadow.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iEmployeeDao.delete(id);
    }

    @Autowired
    private IEmployeeDao iEmployeeDao;
}
