package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Great;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Taoism <br/>
 * Created on 2018/3/23 <br/>
 */
public interface IGreatDao extends JpaRepository<Great, Integer>, JpaSpecificationExecutor<Great> {

    /**
     * 用户取消点赞
     * @param userId 用户id
     * @param postId 文章id
     * @return 操作后的数据
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update Great as g set g.ifPraise = 'false' where g.userId = ?1 and g.postId = ?2")
    public Great cancel(Integer userId, Integer postId);

    /**
     * 用户点赞
     * @param userId 用户id
     * @param postId 文章id
     * @return 操作后的数据
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "update Great as g set g.ifPraise = 'true' where g.userId = ?1 and g.postId = ?2")
    public Great praise(Integer userId, Integer postId);

    /**
     * 查询用户是否点赞 或者 点过赞(不用查询是否点赞 即 ifPraise = 'true')
     * @param userId 用户id
     * @param postId 文章id
     * @return 查询到的数据
     */
//    @Modifying(clearAutomatically = true)
    @Query(value = "select g from Great as g where g.userId = ?1 and g.postId = ?2")
    public Great ispraise(Integer userId, Integer postId);

    /**
     * 查询用户是否点赞 (需要查询是否点赞 即 ifPraise = 'true')
     * @param userId 用户id
     * @param postId 文章id
     * @return 查询到的数据
     */
    @Query(value = "select g from Great as g where g.userId = ?1 and g.postId = ?2 and g.ifPraise = 'true'")
    public Great praised(Integer userId, Integer postId);
}
