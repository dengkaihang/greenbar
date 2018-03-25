package com.qawhcb.shadow.service;


import com.qawhcb.shadow.entity.Version;

import java.util.List;

/**
 * 查询版本信息
 * Created by kane on 18-2-1.
 */
public interface IVersionService {

    public List<Version> selectVersion();
}
