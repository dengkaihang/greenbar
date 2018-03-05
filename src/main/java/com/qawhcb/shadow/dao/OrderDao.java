package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/2 <br/>
 */
public interface OrderDao extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order>{


    @Modifying(clearAutomatically = true)
    @Query("update Order as o set o.label1 = :name where o.id=:id")
    int updateNameById(@Param("name") String name, @Param("id") int id);


    @Modifying(clearAutomatically = true)
    @Query("update Order as o set o.label2 = :name where o.id=:id")
    int er(@Param("name") String name, @Param("id") int id);

}
