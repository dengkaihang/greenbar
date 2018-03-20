package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.User;
import org.springframework.web.multipart.MultipartFile;

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
}
