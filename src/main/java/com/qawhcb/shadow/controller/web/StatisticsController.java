package com.qawhcb.shadow.controller.web;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Store;
import com.qawhcb.shadow.entity.dataModel.StatisticsOrderVo;
import com.qawhcb.shadow.entity.dataModel.StoreVo;
import com.qawhcb.shadow.service.IStoreService;
import com.qawhcb.shadow.service.impl.UtilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Taoism <br/>
 * Created on 2018/3/23 <br/>
 */
@Api(tags = "statistics web", description = "店铺相关数据统计")
@RestController(value = "webStatisticsController")
@RequestMapping(value = "/web/statistics")
public class StatisticsController {

    @ApiOperation(value = "订单相关统计")
    @GetMapping(value = "/order/{token}/{sotreId}")
    public String statisticsOrder(@ApiParam(value = "token", name = "token") @PathParam(value = "token") String token,
                                  @ApiParam(name = "storeId", value = "店铺id") @PathVariable(value = "storeId") Integer storeId){
        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        StatisticsOrderVo statisticsOrderVo = iStoreService.statisticsOrder(storeId);

        map.put("code", 1);
        map.put("msg", "统计完成");
        map.put("obj", statisticsOrderVo);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "首页商铺信息展示", notes = "店铺收藏数 暂存 label1中")
    @GetMapping(value = "/store/{token}/{sotreId}")
    public String showStore(@ApiParam(value = "token", name = "token") @PathParam(value = "token") String token,
                                  @ApiParam(name = "storeId", value = "店铺id") @PathVariable(value = "storeId") Integer storeId){
        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        Store store = iStoreService.showInHome(storeId);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", store);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "店铺评分查询")
    @GetMapping(value = "/grade/{token}/{sotreId}")
    public String grade(@ApiParam(value = "token", name = "token") @PathParam(value = "token") String token,
                            @ApiParam(name = "storeId", value = "店铺id") @PathVariable(value = "storeId") Integer storeId){
        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        StoreVo storeVo = iStoreService.grade(storeId);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", storeVo);

        return JSONArray.toJSONString(map);

    }



    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IStoreService iStoreService;
}
