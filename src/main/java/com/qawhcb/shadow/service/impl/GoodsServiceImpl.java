package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IGoodsDao;
import com.qawhcb.shadow.dao.IPackDao;
import com.qawhcb.shadow.entity.Goods;
import com.qawhcb.shadow.entity.Pack;
import com.qawhcb.shadow.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iGoodsDao.delete(id);
    }

    @Override
    public Goods updateCoverImg(String names) {

        Goods goods = new Goods();
        goods.setCover(names);

        return iGoodsDao.save(goods);
    }

    @Override
    public Goods save(Goods goods) {

        if (goods.getLabel1() != null){
            Pack one = iPackDao.findOne(Integer.parseInt(goods.getLabel1()));

            one.setGoodsId(goods.getId());

            iPackDao.save(one);
        }

        return iGoodsDao.save(goods);
    }

    @Override
    public Goods findOne(Integer id) {
        return iGoodsDao.findOne(id);
    }

    @Override
    public List<Goods> findAll(int page, HashMap<String, String> reqm) {

        iGoodsDao.findAll(new Specification<Goods>() {
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                // 条件集合
                List<Predicate> re = new ArrayList<>(16);

                for (Map.Entry<String, String> entry :
                        reqm.entrySet()) {
                    if (entry.getValue() != null || !"".equals(entry.getValue())){
                        re.add(cb.equal(root.get(entry.getKey()), entry.getValue()));
                    }
                }
                re.add(cb.equal(root.get("ifDel"), "false"));
                Predicate[] p = new Predicate[re.size()];
                return cb.and(re.toArray(p));
            }
        }, new PageRequest(page, 10));

        return (List<Goods>) iGoodsDao.findAll(new PageRequest(page, 10));
    }

    @Override
    public List<Goods> findAllByType(int page, String type) {

        Page<Goods> all = iGoodsDao.findAll(new Specification<Goods>() {
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> re = new ArrayList<>(8);

                re.add(cb.equal(root.get("type"), type));
                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        }, new PageRequest(page, 10));

        return all.getContent();
    }

    @Autowired
    private IGoodsDao iGoodsDao;

    @Autowired
    private IPackDao iPackDao;
}
