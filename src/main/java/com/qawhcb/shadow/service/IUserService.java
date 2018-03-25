package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户ｓｅｒｖｉｃｅ
 * Created by kane on 18-3-5
 */
public interface IUserService {
    /**
     * 用户验证码登录
     *
     * @param phone 　手机号码
     * @return
     * @throws Exception
     */
    public User codeLogin(String phone) throws Exception;

    /**
     * 用户注册（首次登录）
     *
     * @param phone 　手机号码
     * @return
     * @throws Exception
     */
    public User regist(String phone) throws Exception;

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    public User modify(User user);

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 修改用户信息
     *
     * @param user 用户对象
     * @return 更新的对象
     */
    public User update(User user);

    /**
     * 修改用户头像
     *
     * @param userId 用户id
     * @param files 用户头像图片文件
     * @return 更新的对象
     */
    User updateVar(Integer userId, MultipartFile[] files);

    /**
     * 验证是否第三方绑定
     * @param token 第三方toke
     * @param mode 登录方式
     * @return 是否注册过
     */
    User isRegisterBySide(String token, String mode);

    /**
     * 第三方登录绑定
     * @param token 第三方登录token
     * @param mode 登录方式
     * @param phone 手机号
     * @return 绑定并登录结果
     */
    User loginBySide(String token, String mode, String phone);

    /**
     * 环信关注好友
     * @param userId 要添加好友的id
     * @param friend 被添加为好友的id
     */
    void follow(Integer userId, Integer friend);

    /**
     * 根据传入的手机号字符串，查询用户信息
     * @param phones 手机号字符串
     * @return 用户列表
     */
    List<User> findByPhones(String phones);
}
