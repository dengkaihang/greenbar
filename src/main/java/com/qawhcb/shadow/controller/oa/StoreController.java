package com.qawhcb.shadow.controller.oa;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Store;
import com.qawhcb.shadow.service.IEmployeeService;
import com.qawhcb.shadow.service.IStoreService;
import com.qawhcb.shadow.service.impl.UtilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taoism <br/>
 * Created on 2018/3/18 <br/>
 * 店铺相关
 */
@Api(tags = "store oa", description = "店铺相关")
@RestController(value = "oaStoreController")
@RequestMapping(value = "/oa/store")
public class StoreController {


    @ApiOperation(value = "查询所有店铺")
    @GetMapping(value = "/find/{token}/{account}")
    public String find(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                       @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account) {

        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }

        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "store_r")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }


        List<Store> stores = iStoreService.findNotDel();

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", stores);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "设置店铺为精选")
    @PatchMapping(value = "/nominate/{token}/{account}")
    public String nominate(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                           @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account,
                           @ApiParam(name = "ids", value = "精选店铺id数组") @PathVariable(value = "ids") String[] ids) {

        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }

        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "store_u")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }


        List<Store> stores = iStoreService.nominate(ids);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", stores);

        return JSONArray.toJSONString(map);

    }


    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IStoreService iStoreService;
}
