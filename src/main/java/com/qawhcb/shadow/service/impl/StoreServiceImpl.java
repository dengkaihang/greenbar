package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IStoreDao;
import com.qawhcb.shadow.entity.Store;
import com.qawhcb.shadow.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iStoreDao.delete(id);
    }
}
