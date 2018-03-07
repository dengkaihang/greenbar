package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.ICollectStoreDao;
import com.qawhcb.shadow.service.ICollectStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class CollectStoreServiceImpl implements ICollectStoreService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iCollectStoreDao.delete(id);
    }

    @Autowired
    private ICollectStoreDao iCollectStoreDao;
}
