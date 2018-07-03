package com.lemon.service;

import com.lemon.entity.User;

/**
 * Created by kane on 18-7-2
 */
public interface IUserService {

    /**
     * 添加用户
     * @param portrait 头像
     * @param nickname 昵称
     * @param phone 手机号码
     * @return
     */
    public User add(String portrait, String nickname, String phone);

}
