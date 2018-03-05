package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/2 <br/>
 */
public interface OrderDao extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order>{

    @Transactional
    @Modifying
    @Query("update Order as o set o.label1 = ?1 where o.id=?2")
    int updateNameById(String name, int id);


}
