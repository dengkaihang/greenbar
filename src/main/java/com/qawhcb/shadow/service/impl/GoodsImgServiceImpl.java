package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IGoodsDao;
import com.qawhcb.shadow.dao.IGoodsImgDao;
import com.qawhcb.shadow.entity.GoodsImg;
import com.qawhcb.shadow.service.IGoodsImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class GoodsImgServiceImpl implements IGoodsImgService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iGoodsImgDao.delete(id);
    }

    @Override
    public GoodsImg updateCoverImg(Integer id, String names) {

        String[] name = names.split(",");

        GoodsImg goodsImg = null;
       for (int i = 0; i < name.length; i++){

           goodsImg = new GoodsImg();

           goodsImg.setAddress(name[i]);
           goodsImg.setGoodsId(id);

           iGoodsImgDao.save(goodsImg);
       }

        return null;
    }

    @Autowired
    private IGoodsImgDao iGoodsImgDao;
}
