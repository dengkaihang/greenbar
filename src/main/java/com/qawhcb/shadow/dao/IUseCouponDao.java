package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Coupon;
import com.qawhcb.shadow.entity.Role;
import com.qawhcb.shadow.entity.UseCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/6 <br/>
 */
public interface IUseCouponDao extends JpaRepository<UseCoupon, Integer>, JpaSpecificationExecutor<UseCoupon> {

    /**
     * 按指定id逻辑删除数据
     *
     * @param id 删除数据id
     */
    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update UseCoupon as a set a.ifDel = 'true' where a.id = ?1")
    void delete(Integer id);

    /**
     * 通过用户id, 查询其所有未过期的优惠券
     *
     * @param userId    用户id
     * @param localTime 本地时间
     * @return 优惠券列表
     */
    @Query(value = "select u from UseCoupon as u where u.userId = ?1 and u.ifUse = 'false' and u.periodOfValidity >= ?2")
    List<UseCoupon> findByUser(Integer userId, String localTime);
}
