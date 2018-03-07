package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IAddressDao;
import com.qawhcb.shadow.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class AddressServiceImpl implements IAddressService {


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iAddressDao.delete(id);
    }

    @Autowired
    private IAddressDao iAddressDao;
}
