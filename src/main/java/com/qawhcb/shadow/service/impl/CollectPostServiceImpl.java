package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.ICollectPostDao;
import com.qawhcb.shadow.service.ICollectPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Taoism <br/>
 * Created on 2018/3/7 <br/>
 */
@Service
public class CollectPostServiceImpl implements ICollectPostService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(Integer id) {
        iCollectPostDao.delete(id);
    }

    @Autowired
    private ICollectPostDao iCollectPostDao;
}
