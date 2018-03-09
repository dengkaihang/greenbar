package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Store;

/**
 * 店铺service
 * Created by kane on 18-3-6
 */
public interface IStoreService {
    /**
     * 店铺注册
     * @param store
     * @return
     */
    public Store regist(Store store);

    /**
     * 店铺登录
     * @param phone 手机号码
     * @param password  密码
     * @return
     */
    public Store login(String phone, String password);

    /**
     * 通过电话号码查询店铺
     * @param phone
     * @return
     */
    public Store selectByPhone(String phone);

    /**
     * 修改店铺信息
     * @param store
     * @return
     */
    public Store modify(Store store);

    /**
     * 通过id查找店铺
     * @param storeId
     * @return
     */
    public Store selectById(Integer storeId);

    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);
}
