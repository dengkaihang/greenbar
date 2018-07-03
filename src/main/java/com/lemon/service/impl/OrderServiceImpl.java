package com.lemon.service.impl;

import com.lemon.dao.IOrderDao;
import com.lemon.entity.Order;
import com.lemon.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kane on 18-7-2
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao iOrderDao;

    @Override
    public Order save(Order order) {
        return iOrderDao.save(order);
    }

    @Override
    public List<Order> findAll(Integer id) {
        List<Order> orderList = iOrderDao.findAll(new Specification<Order>(){

            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.equal(root.get("userId"), id));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        });
        return orderList;
    }

    @Override
    public Order findOne(Integer id) {
        Order order = iOrderDao.findOne(new Specification<Order>(){

            @Override
            public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(cb.equal(root.get("id"), id));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        });
        return order;
    }

    @Override
    public List<Order> findByTime(Integer houseId, String liveTime, String leaveTime) {
        return iOrderDao.findByTime(houseId,liveTime,leaveTime);
    }
}
