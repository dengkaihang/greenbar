package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Store;
import com.qawhcb.shadow.entity.dataModel.StatisticsOrderVo;
import com.qawhcb.shadow.entity.dataModel.StoreVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 按照id查询指定收藏店铺
     * @param userId 用户id（没有设置为null）
     * @param storeId 店铺id
     * @return 查询结果
     */
    public StoreVo findOne(Integer userId, Integer storeId);

    /**
     * 查询店铺
     * @param storeId 店铺id
     * @return 查询的信息
     */
    public Store find(Integer storeId);

    /**
     * 更新店铺对象
     * @param store 更新对象
     * @return 更新后的
     */
    public Store update(Store store);

    /**
     * 店铺用户信息图片上传
     * @param storeId 店铺
     * @param type 上传图片所属种类
     * @param files 文件
     * @return 修改的店铺信息
     */
    Store updateImg(Integer storeId, String type, MultipartFile[] files);

    /**
     * 统计当前店铺订单信息
     * @param storeId 店铺id
     * @return 统计结果
     */
    StatisticsOrderVo statisticsOrder(Integer storeId);

    /**
     * 店铺首页信息展示
     * @param storeId 商品id
     * @return 展示的信息
     */
    Store showInHome(Integer storeId);

    /**
     * 店铺评分查询
     * @param storeId 店铺id
     * @return 查询的评分, json 格式
     */
    StoreVo grade(Integer storeId);

    /**
     * 退出登录
     * @param storeId 退出的帐号
     */
    void logout(Integer storeId);

    /**
     * 查询店铺排名
     * @return 所有店铺排名
     */
    List<Store> ranking();

    /**
     * 查询没有被删除的店铺
     * @return 店铺集合
     */
    List<Store> findNotDel();

    /**
     * 设置精选店铺
     * @param ids 店铺id列表
     * @return 修改后的店铺
     */
    List<Store> nominate(String[] ids);
}
