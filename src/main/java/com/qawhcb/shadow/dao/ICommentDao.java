package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.CollectStore;
import com.qawhcb.shadow.entity.Comment;
import com.qawhcb.shadow.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/6 <br/>
 */
public interface ICommentDao extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {

    /**
     * 按指定id逻辑删除数据
     * @param id 删除数据id
     */
    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update Comment as a set a.ifDel = 'true' where a.id = ?1")
    void delete(Integer id);

    /**
     * 通过订单id查询所有评论
     * @param orderId 店铺id
     * @return 店铺下所有订单
     */
    @Query(value = "select c from Comment as c where c.orderId = ?1 and c.ifDel = 'false'")
    List<Comment> findByOrder(String orderId);
}
