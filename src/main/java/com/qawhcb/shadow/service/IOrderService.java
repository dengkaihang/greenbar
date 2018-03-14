package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Order;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IOrderService {

    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 通过商品查询所有订单
     * @param goodsId 商品id
     * @return 所有相关订单
     */
    public List<Order> getAll(Integer goodsId);

    /**
     * 新建订单
     * @param order 订单对象
     * @return 添加成功的订单
     */
    public Order save(Order order);
}
