package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Taoism <br/>
 * Created on 2018/3/6 <br/>
 */
public interface IFeedbackDao extends JpaRepository<Feedback, Integer>, JpaSpecificationExecutor<Feedback> {

    /**
     * 按指定id逻辑删除数据
     * @param id 删除数据id
     */
    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update Feedback as a set a.ifDel = 'true' where a.id = ?1")
    void delete(Integer id);

}
