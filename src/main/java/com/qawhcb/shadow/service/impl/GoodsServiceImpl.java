package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.*;
import com.qawhcb.shadow.entity.*;
import com.qawhcb.shadow.entity.dataModel.CommentVo;
import com.qawhcb.shadow.entity.dataModel.GoodsVo;
import com.qawhcb.shadow.service.IGoodsService;
import com.qawhcb.shadow.service.IOrderService;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Goods save(Goods goods) {
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
                    if (entry.getValue() != null || !"".equals(entry.getValue())) {
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


        List<Goods> content = all.getContent();

        for (Goods g : content) {

            // 查询商品套餐最低价格
            List<Pack> allPack = iPackDao.findAll(new Specification<Pack>() {
                @Override
                public Predicate toPredicate(Root<Pack> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                    List<Predicate> re = new ArrayList<>(8);

                    re.add(cb.equal(root.get("goodsId"), g.getId()));
                    re.add(cb.equal(root.get("ifDel"), "false"));

                    Predicate[] p = new Predicate[re.size()];

                    query.where(cb.and(re.toArray(p)));
                    query.orderBy(cb.asc(root.get("price")));

                    return query.getRestriction();
                }
            });

            // label1预存价格
            g.setLabel1(allPack.get(0).getPrice());

            List<Order> allOrder = iOrderService.getAll(g.getId());

            // label2预存销售总量
            g.setLabel2(String.valueOf(allOrder.size()));
        }

        return all.getContent();
    }

    @Override
    public GoodsVo findByGoodsId(int goodsId) {

        GoodsVo goodsVo = new GoodsVo();

        Goods goods = iGoodsDao.findOne(goodsId);
        goodsVo.setGoods(goods);

        Store storeSrc = iStoreDao.findOne(goods.getStoreId());
        // 重新接收店铺，消除敏感信息
        Store storeTager = new Store();

        storeTager.setDepict(storeSrc.getDepict());
        storeTager.setPhone(storeSrc.getPhone());
        storeTager.setIfDel(null);

        goodsVo.setStore(storeTager);

        // 查找评论，通过商品id查找订单
        List<Order> orders = iOrderService.getAll(goodsId);

        // 如果商品订单不为null，查询该商品的一条订单的评论
        if (orders.size() != 0) {

            List<Comment> comments = iCommentDao.findAll(new Specification<Comment>() {
                @Override
                public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> re = new ArrayList<>(8);

                    re.add(cb.equal(root.get("orderId"), orders.get(0).getId()));
                    re.add(cb.equal(root.get("ifDel"), "false"));

                    Predicate[] p = new Predicate[re.size()];

                    return cb.and(re.toArray(p));
                }
            });

            if (comments.size() != 0){
                CommentVo commentVo = new CommentVo();

                commentVo.setComment(comments.get(0));

                // 添加评论用户
                Integer userId = comments.get(0).getUserId();

                User userSrc = iUserDao.findOne(userId);

                User userTarget = new User();

                userTarget.setNickName(userSrc.getNickName());
                userTarget.setPortrait(userSrc.getPortrait());

                commentVo.setUser(userTarget);

                goodsVo.setCommentVo(commentVo);


            }
        }

        // 查询商品相关套餐
        List<Pack> packs = iPackDao.findAll(new Specification<Pack>() {
            @Override
            public Predicate toPredicate(Root<Pack> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(8);

                re.add(cb.equal(root.get("goodsId"), goodsId));
                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        });
        goodsVo.setPacks(packs);

        return goodsVo;
    }

    @Override
    public List<GoodsVo> findAllByStore(int storeId, int page) {

        List<GoodsVo> list = new ArrayList<>(16);

        // 查询当前店铺所遇商品
        Page<Goods> goods = iGoodsDao.findAll(new Specification<Goods>() {
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(8);

                re.add(cb.equal(root.get("storeId"), storeId));
                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        }, new PageRequest(page, 4));

        // 查询每个商品的套餐
        for (Goods g :
                goods.getContent()) {

            GoodsVo goodsVo = new GoodsVo();

            List<Pack> packs = iPackDao.findAll(new Specification<Pack>() {
                @Override
                public Predicate toPredicate(Root<Pack> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> re = new ArrayList<>(16);

                    re.add(cb.equal(root.get("goodsId"), g.getId()));
                    re.add(cb.equal(root.get("ifDel"), "false"));

                    Predicate[] p = new Predicate[re.size()];

                    return cb.and(re.toArray(p));
                }
            });

            goodsVo.setGoods(g);
            goodsVo.setPacks(packs);

            list.add(goodsVo);
        }

        return list;
    }

    @Autowired
    private IGoodsDao iGoodsDao;

    @Autowired
    private IPackDao iPackDao;

    @Autowired
    private IStoreDao iStoreDao;

    @Autowired
    private ICommentDao iCommentDao;

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IUserDao iUserDao;
}
