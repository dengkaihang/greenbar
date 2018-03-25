package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Post;
import com.qawhcb.shadow.entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/6 <br/>
 */
public interface IPostCommentDao extends JpaRepository<PostComment, Integer>, JpaSpecificationExecutor<PostComment> {

    /**
     * 按指定id逻辑删除数据
     * @param id 删除数据id
     */
    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update PostComment as a set a.ifDel = 'true' where a.id = ?1")
    void delete(Integer id);

    @Query(value = "select p from PostComment as p where p.postId = ?1 and p.ifDel = 'false'")
    List<PostComment> findByPostId(Integer postId);
}
