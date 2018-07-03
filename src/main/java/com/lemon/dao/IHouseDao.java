package com.lemon.dao;

import com.lemon.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by kane on 18-7-2
 */
public interface IHouseDao extends JpaSpecificationExecutor<House>,JpaRepository<House,String> {
}
