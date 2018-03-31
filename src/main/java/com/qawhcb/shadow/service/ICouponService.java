package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Coupon;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface ICouponService {

    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 优惠券创建
     * @param coupon 优惠券
     * @return 创建结果
     */
    Coupon publish(Coupon coupon);

    /**
     * 领取优惠券
     * @param userId 领取的用户
     * @param couponId 代金券的id
     * @return 结果
     */
    Coupon receive(Integer userId, Integer couponId);

    /**
     * 查询用户持有优惠券
     * @param userId 用户id
     * @return 所有领取但为用的优惠券
     */
    List<Coupon> find(Integer userId);

    /**
     * 查找所有没有被删除的优惠券
     * @return 优惠券列表
     */
    List<Coupon> findNotDel();
}
