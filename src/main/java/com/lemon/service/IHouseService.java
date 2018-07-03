package com.lemon.service;

import com.lemon.entity.House;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * Created by kane on 18-7-2
 */
public interface IHouseService {

    /**
     * 查看所有房源
     * @return
     */
    public List<House> findAll();

    /**
     * 查询房源详情
     * @param id
     * @return
     */
    public House findOne(Integer id);
}
