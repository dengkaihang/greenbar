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
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);
}
