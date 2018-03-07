package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IUserFriendsDao;
import com.qawhcb.shadow.service.IUserFriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class UserFriendsServiceImpl implements IUserFriendsService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iUserFriendsDao.delete(id);
    }

    @Autowired
    private IUserFriendsDao iUserFriendsDao;
}
