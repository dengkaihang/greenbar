package com.qawhcb.shadow.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.dao.*;
import com.qawhcb.shadow.entity.*;
import com.qawhcb.shadow.entity.dataModel.StatisticsOrderVo;
import com.qawhcb.shadow.entity.dataModel.StoreVo;
import com.qawhcb.shadow.service.IStoreService;
import com.qawhcb.shadow.utils.GetLocalDateTime;
import com.qawhcb.shadow.utils.UpdateUtils;
import com.qawhcb.shadow.utils.UploadFileUtils;
import com.qawhcb.shadow.utils.easemobUtils.ImUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

        getGrade(storeId, storeVo);

        return storeVo;
    }

    /**
     * 获取商铺评分
     * @param storeId 店铺id
     * @param storeVo 要将评分设置进去的 Vo 对象
     */
    private void getGrade(Integer storeId, StoreVo storeVo) {
        // 统计店铺评分
        double praiseRate = 0.0;
        double depict = 0.0;
        double service = 0.0;
        double speed = 0.0;
        int count = 0;

        // 查询店铺下所有商品
        List<Goods> goods = iGoodsDao.findByStore(storeId);

        // 遍历商品，查询所有订单
        for (Goods g :
                goods) {

            List<Order> orders = iOrderDao.findByGoods(g.getId());

            // 遍历订单，查询评价。计算总分
            for (Order o :
                    orders) {

                List<Comment> comments = iCommentDao.findByOrder(o.getId());

                for (Comment comment :
                        comments) {

//                    Double.parseDouble(comment.getDepict());

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
        } else {
            storeVo.setPraiseRate(0);
            storeVo.setDepictNum(0);
            storeVo.setServicNum(0);
            storeVo.setSpeedNum(0);
        }
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

    @Override
    public Store updateImg(Integer storeId, String type, MultipartFile[] files) {

        Store store = iStoreDao.findOne(storeId);

        String names = UploadFileUtils.storeImgUpload(files, String.valueOf(storeId));

        if ("card".equals(type)) {
            store.setHandIdCard(names);
        } else if ("portrait".equals(type)) {
            store.setPortrait(names);
        }

        return iStoreDao.save(store);
    }

    @Override
    public StatisticsOrderVo statisticsOrder(Integer storeId) {

        // 查询所有订单
        List<Order> byStore = iOrderDao.findByStore(storeId);

        double money = 0.0;
        int comment = 0;
        int pending = 0;
        int orderNum = 0;
        int obligation = 0;
        int refundsPending = 0;
        int underWay = 0;
        int fulfil = 0;
        int evaluated = 0;

        orderNum = byStore.size();

        for (Order o :
                byStore) {
            List<Comment> byOrder = iCommentDao.findByOrder(o.getId());

//            money += Double.parseDouble(o.getPrice());

            // 总评论数
            comment += byOrder.size();

            switch (o.getStatus()) {
                case "dfk":

                    pending++;

                    obligation++;

                    break;
                case "dtk":

                    refundsPending++;

                    break;
                case "jxz":

                    pending++;
                    underWay++;

                    break;
                // 已完成
                case "ywc":
                    money += Double.parseDouble(o.getPrice());

                    fulfil++;

                    break;
                case "tkwc":

                    fulfil++;

                    break;
                case "dpj":

                    money += Double.parseDouble(o.getPrice());

                    evaluated++;

                    break;

                default:
                    break;
            }

        }

        StatisticsOrderVo sv = new StatisticsOrderVo();

        sv.setMoney(money);
        sv.setComment(comment);
        sv.setPending(pending);
        sv.setOrderNum(orderNum);
        sv.setObligation(obligation);
        sv.setRefundsPending(refundsPending);
        sv.setUnderWay(underWay);
        sv.setFulfil(fulfil);
        sv.setEvaluated(evaluated);

        return sv;
    }

    @Override
    public Store showInHome(Integer storeId) {
        Store store = iStoreDao.findOne(storeId);

        List<CollectStore> byStore = iCollectStoreDao.findByStore(storeId);

        store.setLabel1(String.valueOf(byStore.size()));

        return store;
    }

    @Override
    public StoreVo grade(Integer storeId) {

        StoreVo storeVo = new StoreVo();

        getGrade(storeId, storeVo);

        return storeVo;
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
