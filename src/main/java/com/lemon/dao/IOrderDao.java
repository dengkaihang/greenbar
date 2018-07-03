package com.lemon.dao;

import com.lemon.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by kane on 18-7-2
 */
public interface IOrderDao extends JpaSpecificationExecutor<Order>,JpaRepository<Order,String> {

    /**
     * 根据时间查询房源预定情况
     * @param houseId
     * @param liveTime
     * @param leaveTime
     * @return
     */
    @Query(value = "select o from Order as o where o.houseId = ?1 and o.liveTime >= ?2 and o.leaveTime <= ?3 and o.status = '已付款'")
    List<Order> findByTime(Integer houseId, String liveTime, String leaveTime);
}
