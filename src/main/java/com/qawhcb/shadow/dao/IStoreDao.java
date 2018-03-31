package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by kane on 18-3-6
 */
public interface IStoreDao extends JpaRepository<Store, Integer>, JpaSpecificationExecutor<Store> {

    /**
     * 按指定id逻辑删除数据
     *
     * @param id 删除数据id
     */
    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update User as a set a.ifDel = 'true' where a.id = ?1")
    void delete(Integer id);

    /**
     * 查询一周内店铺注册量
     *
     * @param date    本周周一
     * @param endDate 结束时间
     * @return 查询结果
     */
    @Query(value = "select s from Store as s where s.createTime >= ?1 and s.createTime < ?2")
    List<Store> findByWeek(String date, String endDate);

    /**
     * 查询所有为被删除的店铺信息
     * @return 插叙结果
     */
    @Query(value = "select s from Store as s where s.ifDel = 'false'")
    List<Store> findNotDel();
}
