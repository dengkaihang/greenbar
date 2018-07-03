package com.lemon.service.impl;

import com.lemon.dao.IUserDao;
import com.lemon.entity.User;
import com.lemon.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kane on 18-7-2
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;

    @Override
    public User add(String portrait, String nickname, String phone) {
        User user = new User();
        user.setPortrait(portrait);
        user.setNickname(nickname);
        user.setPhone(phone);
        user = iUserDao.save(user);
        return user;
    }

}
