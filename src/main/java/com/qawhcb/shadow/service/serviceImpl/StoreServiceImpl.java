package com.qawhcb.shadow.service.serviceImpl;

import com.qawhcb.shadow.dao.IStoreDao;
import com.qawhcb.shadow.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kane on 18-3-6
 */
@Service
public class StoreServiceImpl implements IStoreService{

    @Autowired
    private IStoreDao iStoreDao;

}
