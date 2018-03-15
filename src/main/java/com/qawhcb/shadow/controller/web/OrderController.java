package com.qawhcb.shadow.controller.web;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Order;
import com.qawhcb.shadow.service.IOrderService;
import com.qawhcb.shadow.service.impl.UtilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taoism <br/>
 * Created on 2018/3/14 <br/>
 * 商品相关信息
 */
@Api(value = "订单相关", description = "订单相关操作")
@RestController(value = "webOrderController")
@RequestMapping(value = "/web/order")
public class OrderController {

    @ApiOperation(value = "查询商户所有订单")
    @GetMapping(value = "/find/{storeId}")
    public String findAll(@ApiParam(name = "storeId", value = "商户Id") @PathVariable(value = "storeId") Integer storeId) {

        Map<String, Object> map = new HashMap<>(8);

        List<Order> orders = iOrderService.findAllByStore(storeId);

        map.put("code", 1);
        map.put("mag", "查询成功");
        map.put("obj", orders);

        return JSONArray.toJSONString(map);

    }

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private UtilsService utilsService;
}
