package com.qawhcb.shadow.service;

import com.qawhcb.shadow.entity.GoodsImg;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
public interface IGoodsImgService {

    /**
     * 删除用户信息
     * @param id 用户id
     */
    public void delete(Integer id);

    /**
     * 保存店铺封面图片
     * @param id 店铺id
     * @param names 封面图片保存路径
     * @return 保存后的图片对象
     */
    public GoodsImg updateCoverImg(Integer id, String names);
}
