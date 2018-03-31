package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Complain;
import com.qawhcb.shadow.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/6 <br/>
 */
public interface ICouponDao extends JpaRepository<Coupon, Integer>, JpaSpecificationExecutor<Coupon> {

    /**
     * 按指定id逻辑删除数据
     * @param id 删除数据id
     */
    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update Coupon as a set a.ifDel = 'true' where a.id = ?1")
    void delete(Integer id);

    /**
     * 查找所有没有被删除的优惠券
     * @return 查找结果
     */
    @Query(value = "select c from Coupon as c where c.ifDel = 'false'")
    List<Coupon> findNotDel();
}
