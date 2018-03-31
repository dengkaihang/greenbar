package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Pack;
import com.qawhcb.shadow.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/6 <br/>
 */
public interface IPostDao extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {

    /**
     * 按指定id逻辑删除数据
     * @param id 删除数据id
     */
    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update Post as a set a.ifDel = 'true' where a.id = ?1")
    void delete(Integer id);

    /**
     * 查询所有推荐帖子
     * @return 查询结果集
     */
    @Query(value = "select p from Post as p where p.nominate = 'true' and p.ifDel = 'false'")
    List<Post> findNominate();
}
