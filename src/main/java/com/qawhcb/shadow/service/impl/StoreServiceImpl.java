package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.*;
import com.qawhcb.shadow.entity.*;
import com.qawhcb.shadow.entity.dataModel.StoreVo;
import com.qawhcb.shadow.service.IStoreService;
import com.qawhcb.shadow.utils.GetLocalDateTime;
import com.qawhcb.shadow.utils.UpdateUtils;
import com.qawhcb.shadow.utils.easemobUtils.ImUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kane on 18-3-6
 */
@Service
public class StoreServiceImpl implements IStoreService {

    @Autowired
    private IStoreDao iStoreDao;

    /**
     * 注册店铺
     *
     * @param store
     * @return
     */
    @Override
    public Store regist(Store store) {

        // 因为浏览器提交异步，如用户连点，跳过之前手机号唯一验证，则在此再次同步验证
        // 手机号加入唯一，莫名其妙的成功

        store.setCreateTime(GetLocalDateTime.getLocalDataTime());

        Store save = iStoreDao.save(store);

        // 注册环信即时通讯用户
        if (save != null) {
            ImUtils.register(save.getPhone());
        }

        return save;
    }

    @Override
    public Store login(String phone, String password) {

        Store store = iStoreDao.findOne(new Specification<Store>() {
            @Override
            public Predicate toPredicate(Root<Store> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("phone"), phone));
                list.add(cb.equal(root.get("password"), password));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        });
        return store;
    }

    @Override
    public Store selectByPhone(String phone) {
        Store store = iStoreDao.findOne(new Specification<Store>() {
            @Override
            public Predicate toPredicate(Root<Store> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("phone"), phone));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        });
        return store;
    }

    @Override
    public Store modify(Store store) {
        return iStoreDao.save(store);
    }

    @Override
    public Store selectById(Integer storeId) {
        return iStoreDao.findOne(storeId);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iStoreDao.delete(id);
    }

    @Override
    public StoreVo findOne(Integer userId, Integer storeId) {
        Store store = iStoreDao.findOne(storeId);

        StoreVo storeVo = new StoreVo();


        if (userId != null && !"".equals(userId)) {

            List<CollectStore> collectStores = iCollectStoreDao.findAll(new Specification<CollectStore>() {
                @Override
                public Predicate toPredicate(Root<CollectStore> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> re = new ArrayList<>(16);

                    re.add(cb.equal(root.get("userId"), userId));
                    re.add(cb.equal(root.get("storeId"), storeId));
                    re.add(cb.equal(root.get("ifDel"), "false"));

                    Predicate[] p = new Predicate[re.size()];

                    return cb.and(re.toArray(p));
                }
            });

            storeVo.setIsCollect(collectStores.size() != 0);
            storeVo.setStore(store);

        } else {
            storeVo.setIsCollect(false);
            storeVo.setStore(store);
        }

        // 统计店铺评分
        double praiseRate = 0.0;
        double depict = 0.0;
        double service = 0.0;
        double speed = 0.0;
        int count = 0;

        // 查询店铺下所有商品
        List<Goods> goods = iGoodsDao.findAll(new Specification<Goods>() {
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(8);

                re.add(cb.equal(root.get("storeId"), storeId));
                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        });

        // 遍历商品，查询所有订单
        for (Goods g :
                goods) {

            List<Order> orders = iOrderDao.findAll(new Specification<Order>() {
                @Override
                public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> re = new ArrayList<>(8);

                    re.add(cb.equal(root.get("goodsId"), g.getId()));
                    re.add(cb.equal(root.get("ifDel"), "false"));

                    Predicate[] p = new Predicate[re.size()];

                    return cb.and(re.toArray(p));
                }
            });

            // 遍历订单，查询评价。计算总分
            for (Order o :
                    orders) {

                List<Comment> comments = iCommentDao.findAll(new Specification<Comment>() {
                    @Override
                    public Predicate toPredicate(Root<Comment> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                        List<Predicate> re = new ArrayList<>(8);

                        re.add(cb.equal(root.get("orderId"), o.getId()));
                        re.add(cb.equal(root.get("ifDel"), "false"));

                        Predicate[] p = new Predicate[re.size()];

                        return cb.and(re.toArray(p));
                    }
                });

                for (Comment comment :
                        comments) {

                    Double.parseDouble(comment.getDepict());

                    depict += Double.parseDouble(comment.getDepict());
                    service += Double.parseDouble(comment.getService());
                    speed += Double.parseDouble(comment.getSpeed());

                    double aver = (depict + service + speed) / 3;

                    if (aver >= 4) {
                        praiseRate++;
                    }

                    count++;
                }
            }
        }

        if (count > 0) {
            storeVo.setPraiseRate(praiseRate / count);
            storeVo.setDepictNum(depict / count);
            storeVo.setServicNum(service / count);
            storeVo.setSpeedNum(speed / count);
        }else {
            storeVo.setPraiseRate(0);
            storeVo.setDepictNum(0);
            storeVo.setServicNum(0);
            storeVo.setSpeedNum(0);
        }


        return storeVo;
    }

    @Override
    public Store find(Integer storeId) {
        return iStoreDao.findOne(storeId);
    }

    @Override
    public Store update(Store store) {

        Store target = iStoreDao.findOne(store.getId());

        UpdateUtils.copyNonNullProperties(store, target);

        return iStoreDao.save(target);

    }

    @Autowired
    private ICollectStoreDao iCollectStoreDao;

    @Autowired
    private IGoodsDao iGoodsDao;

    @Autowired
    private IOrderDao iOrderDao;

    @Autowired
    private ICommentDao iCommentDao;
}
