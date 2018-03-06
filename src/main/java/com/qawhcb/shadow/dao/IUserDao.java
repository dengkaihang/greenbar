package com.qawhcb.shadow.dao;

import com.qawhcb.shadow.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by kane on 18-3-5
 */
public interface IUserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User>{
}
