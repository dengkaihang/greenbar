package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by kane on 18-3-5
 */
public interface IUserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

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
     * 根据手机号查询用户
     *
     * @param p 手机号
     * @return 用户信息
     */
    @Query(value = "select u.id, u.nickName, u.portrait from User as u where u.phone = ?1 and u.ifDel = 'false'")
    User findByPhone(String p);
}
