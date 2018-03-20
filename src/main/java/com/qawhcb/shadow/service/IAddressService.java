package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Address;

import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IAddressService {

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 保存常用地址
     *
     * @param address 地址对象
     * @return 保存的地址对象
     */
    public Address save(Address address);

    /**
     * 查询所有常用地址
     *
     * @param userId 用户Id
     * @return 查询到的所有地址
     */
    public List<Address> findAll(Integer userId);
}
