package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.CollectPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/6 <br/>
 */
public interface ICollectPostDao extends JpaRepository<CollectPost, Integer>, JpaSpecificationExecutor<CollectPost> {

    /**
     * 按指定id逻辑删除数据
     * @param id 删除数据id
     */
    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update CollectPost as a set a.ifDel = 'true' where a.id = ?1")
    void delete(Integer id);

    /**
     * 查询用处收藏过的所有帖子
     * @param userId 用户id
     * @return 所有帖子列表
     */
    @Query(value = "select c from CollectPost as c where c.userId = ?1 and c.ifDel = 'false'")
    List<CollectPost> findByUserId(Integer userId);

    /**
     * 判断用户之前是否收藏过此贴
     * @param userId 用户id
     * @param postId 帖子id
     * @return 结果
     */
    @Query(value = "select c from CollectPost as c where c.userId = ?1 and c.postId = ?2")
    CollectPost fingByUserIdAndPostId(Integer userId, Integer postId);

    /**
     * 用户取消收藏指定帖子
     * @param userId 用户id
     * @param postId 帖子id
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update CollectPost as a set a.ifDel = 'true' where a.userId = ?1 and a.postId = ?2")
    void deleteByUserIdAndPostId(Integer userId, Integer postId);
}
