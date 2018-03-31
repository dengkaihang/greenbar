package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.GoodsImg;
import com.qawhcb.shadow.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.security.KeyStore;
import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/6 <br/>
 */
public interface IOrderDao extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {

    /**
     * 按指定id逻辑删除数据
     *
     * @param id 删除数据id
     */
    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update Order as a set a.ifDel = 'true' where a.id = ?1")
    void delete(String id);

    /**
     * 通过店铺id查询所有订单
     *
     * @param storeId 店铺id
     * @return 店铺下所有订单
     */
    @Query(value = "select o from Order as o where o.storeId = ?1 and o.ifDel = 'false'")
    List<Order> findByStore(Integer storeId);

    /**
     * 通过店铺id查询所有订单
     *
     * @param goodsId 商品Id
     * @return 店铺下所有订单
     */
    @Query(value = "select o from Order as o where o.goodsId = ?1 and o.ifDel = 'false'")
    List<Order> findByGoods(Integer goodsId);


    /**
     * 查询交易成功的订单
     *
     * @return 结果
     */
    @Query(value = "select o from Order as o where o.status = 'dpj' or o.status = 'ywc'")
    List<Order> findBySuccess();

    /**
     * 查询退款成功的订单
     *
     * @return 结果
     */
    @Query(value = "select o from Order as o where o.status = 'tkwc'")
    List<Order> findByRefund();

    /**
     * 统计某天订单成交量
     *
     * @param date    时间
     * @param endDate 结束时间
     * @return 结果
     */
    @Query(value = "select o from Order as o where o.fulfilTime >= ?1 and o.fulfilTime < ?2 and o.status = 'ywc'")
    List<Order> findByWeekSuccess(String date, String endDate);

    /**
     * 统计某天订单退款量
     *
     * @param date    时间
     * @param endDate 结束时间
     * @return 查询结果
     */
    @Query(value = "select o from Order as o where o.refundTime >= ?1 and o.refundTime < ?2 and o.status = 'tkwc'")
    List<Order> findByWeekRefund(String date, String endDate);

}
