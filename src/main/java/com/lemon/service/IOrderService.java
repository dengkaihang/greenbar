package com.lemon.service;

import com.lemon.entity.Order;

import java.util.List;

/**
 * Created by kane on 18-7-2
 */
public interface IOrderService {
    /**
     * 添加订单
     * @param order
     * @return
     */
    public Order save(Order order);

    /**
     * 查询用户所有订单
     * @param id 用户id
     * @return
     */
    public List<Order> findAll(Integer id);

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    public Order findOne(Integer id);

    /**
     * 查询房源订单
     * @param houseId 房源id
     * @param liveTime 入住时间
     * @param leaveTime 离开时间
     * @return
     */
    public List<Order> findByTime(Integer houseId, String liveTime, String leaveTime);
}
