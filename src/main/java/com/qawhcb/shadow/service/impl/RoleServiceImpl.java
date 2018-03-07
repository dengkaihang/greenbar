package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IRoleDao;
import com.qawhcb.shadow.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iRoleDao.delete(id);
    }

    @Autowired
    private IRoleDao iRoleDao;
}
