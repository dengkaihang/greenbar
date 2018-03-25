package com.qawhcb.shadow.service.impl;

import com.qawhcb.shadow.dao.IVersionDao;
import com.qawhcb.shadow.entity.Version;
import com.qawhcb.shadow.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kane on 18-2-1.
 */
@Service
public class VersionServiceImpl implements IVersionService {

    @Autowired
    private IVersionDao iVersionDao;

    @Override
    public List<Version> selectVersion() {

        List<Version> version = iVersionDao.findAll();

        return version;
    }
}
