package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IOrderDao;
import com.qawhcb.shadow.entity.Order;
import com.qawhcb.shadow.service.IOrderService;
import com.qawhcb.shadow.utils.UploadFileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iOrderDao.delete(id);
    }

    @Override
    public List<Order> getAll(Integer goodsId) {
        return iOrderDao.findAll(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(16);

                re.add(cb.equal(root.get("goodsId"), goodsId));
                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        });
    }

    @Override
    public Order save(Order order) {
        return iOrderDao.save(order);
    }

    @Override
    public List<Order> findAll(Integer userId) {
        return iOrderDao.findAll(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(16);

                re.add(cb.equal(root.get("userId"), userId));
                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        });

    }

    @Override
    public Order uploadFile(Integer orderId, MultipartFile[] files) {

        Order order = iOrderDao.findOne(orderId);

        String fileNames = UploadFileUtils.orderFileUpload(files, String.valueOf(orderId));

        order.setOriginalFile(fileNames);

        Order save = iOrderDao.save(order);

        return save;
    }

    @Override
    public List<Order> findAllByStore(Integer storeId) {
        return iOrderDao.findAll(new Specification<Order>() {
            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> re = new ArrayList<>(16);

                re.add(cb.equal(root.get("storeId"), storeId));
                re.add(cb.equal(root.get("ifDel"), "false"));

                Predicate[] p = new Predicate[re.size()];

                return cb.and(re.toArray(p));
            }
        });
    }

    @Autowired
    private IOrderDao iOrderDao;
}
