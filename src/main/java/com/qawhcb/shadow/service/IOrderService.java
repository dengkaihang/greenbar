package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Order;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IOrderService {

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 通过商品查询所有订单
     *
     * @param goodsId 商品id
     * @return 所有相关订单
     */
    public List<Order> getAll(Integer goodsId);

    /**
     * 新建订单
     *
     * @param order 订单对象
     * @return 添加成功的订单
     */
    public Order save(Order order);

    /**
     * 查询订单
     *
     * @param userId 当前用户id
     * @return 所有订单
     */
    public List<Order> findAll(Integer userId);

    /**
     * 订单文件上传
     *
     * @param orderId 文件上传到的订单
     * @param files   要上传的文件
     * @return 合成后的订单
     */
    public Order uploadFile(Integer orderId, MultipartFile[] files);

    /**
     * 查询店铺所有订单
     *
     * @param storeId 当前用户id
     * @return 所有订单
     */
    public List<Order> findAllByStore(Integer storeId);
}
