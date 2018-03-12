package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Goods;
import com.qawhcb.shadow.entity.Pack;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IPackService {

    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);


    /**
     * 保存商品套餐对象
     *
     * @param pack 商品套餐对象
     * @return 保存的商品套餐对象
     */
    public Pack save(Pack pack);

}
