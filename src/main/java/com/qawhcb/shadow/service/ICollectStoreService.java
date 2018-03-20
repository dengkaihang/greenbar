package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.CollectStore;
import com.qawhcb.shadow.entity.dataModel.CollectStoreVo;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface ICollectStoreService {


    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 添加店铺到收藏列表
     * @param userId 收藏用户
     * @param storeId 店铺id
     * @return 收藏结果
     */
    public CollectStore save(Integer userId, Integer storeId);

    /**
     * 查询用户收藏列表
     * @param userId 用户id
     * @return 查询结果
     */
    List<CollectStoreVo> findAll(Integer userId);
}
