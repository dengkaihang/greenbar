package com.qawhcb.shadow.service;

import java.util.Map;

/**
 * @author Taoism <br/>
 * Created on 2018/3/28 <br/>
 * 统计业务层
 */
public interface IStatisticsService {
    /**
     * 客户端数据统计
     * @return 统计map 结果
     */
    public Map<String, Object> clientData();

    /**
     * 周统计
     * @return 周统计结果
     */
    Map<String,Object> week();
}
