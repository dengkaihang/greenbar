package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.User;

/**
 * 用户ｓｅｒｖｉｃｅ
 * Created by kane on 18-3-5
 */
public interface IUserService {
    /**
     * 用户验证码登录
     * @param phone　手机号码
     * @return
     * @throws Exception
     */
    public User codeLogin(String phone)throws Exception;

    /**
     * 用户注册（首次登录）
     * @param phone　手机号码
     * @return
     * @throws Exception
     */
    public User regist(String phone)throws Exception;

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    public User modify(User user);

    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);
}
