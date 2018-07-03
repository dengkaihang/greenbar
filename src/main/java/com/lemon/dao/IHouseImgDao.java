package com.lemon.dao;

import com.lemon.entity.HouseImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by kane on 18-7-2
 */
public interface IHouseImgDao extends JpaSpecificationExecutor<HouseImg>,JpaRepository<HouseImg,String> {
}
