package com.qawhcb.shadow.controller.oa;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.service.IEmployeeService;
import com.qawhcb.shadow.service.IStatisticsService;
import com.qawhcb.shadow.service.impl.UtilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taoism <br/>
 * Created on 2018/3/28 <br/>
 * oa 系统统计
 */
@Api(tags = "statistics oa", description = "店铺相关数据统计")
@RestController(value = "oaStatisticsController")
@RequestMapping(value = "/oa/statistics")
public class StatisticsController {

    @ApiOperation(value = "后台数据统计")
    @GetMapping(value = "/register/{token}/{account}")
    public String register(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                           @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account) {

        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }

        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "statistics_r")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }

        Map<String, Object> result = iStatisticsService.clientData();

        pottingResult(map, result);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "周统计")
    @GetMapping(value = "/week/{token}/{account}")
    public String week(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                       @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account) {

        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }

        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "statistics_r")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }

        Map<String, Object> result = iStatisticsService.week();

        pottingResult(map, result);

        return JSONArray.toJSONString(map);
    }

    /**
     * 统计结果封装
     *
     * @param map    接收封装的容器
     * @param result 要封装的结果
     */
    private void pottingResult(Map<String, Object> map, Map<String, Object> result) {
        if (result.size() > 0) {
            map.put("code", 1);
            map.put("msg", result);
        } else {
            map.put("code", -1);
            map.put("msg", "统计失败");
        }
    }

    @Autowired
    private IStatisticsService iStatisticsService;

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IEmployeeService iEmployeeService;

}
