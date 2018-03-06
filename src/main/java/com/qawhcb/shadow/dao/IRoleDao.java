package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.Reply;
import com.qawhcb.shadow.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Taoism <br/>
 * Created on 2018/3/6 <br/>
 */
public interface IRoleDao extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {

    /**
     * 按指定id逻辑删除数据
     * @param id 删除数据id
     */
    @Override
    @Modifying(clearAutomatically = true)
    @Query(value = "update Role as a set a.ifDel = 'true' where a.id = ?1")
    void delete(Integer id);

}
