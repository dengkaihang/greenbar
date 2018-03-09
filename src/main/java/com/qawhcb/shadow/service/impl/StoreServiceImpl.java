package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IStoreDao;
import com.qawhcb.shadow.entity.Store;
import com.qawhcb.shadow.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kane on 18-3-6
 */
@Service
public class StoreServiceImpl implements IStoreService{

    @Autowired
    private IStoreDao iStoreDao;

    /**
     * 注册店铺
     * @param store
     * @return
     */
    @Override
    public Store regist(Store store) {
        return iStoreDao.save(store);
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
}
