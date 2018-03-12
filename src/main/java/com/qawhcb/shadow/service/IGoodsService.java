package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.Goods;
import com.qawhcb.shadow.entity.GoodsImg;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IGoodsService {

    /**
     * 删除用户信息
     *
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 保存店铺封面图片
     *
     * @param names 封面图片保存路径
     * @return 保存后的图片对象
     */
    public Goods updateCoverImg(String names);

    /**
     * 保存商品对象
     *
     * @param goods 商品对象
     * @return 保存的商品对象
     */
    public Goods save(Goods goods);

    /**
     * 按照id查找
     *
     * @param id 商品id
     * @return 商品实体
     */
    public Goods findOne(Integer id);

    /**
     * 查找所有商品
     *
     * @param page 当前页
     * @param reqm 查询条件
     * @return 商品实体
     */
    public List<Goods> findAll(int page, HashMap<String, String> reqm);

    /**
     * 按照商品类型查找所有商品
     *
     * @param page 当前页
     * @param type 商品类型
     * @return 商品实体
     */
    public List<Goods> findAllByType(int page, String type);
}
