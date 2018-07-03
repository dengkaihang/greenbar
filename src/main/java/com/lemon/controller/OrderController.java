package com.lemon.controller;

import com.alibaba.fastjson.JSONArray;
import com.lemon.entity.House;
import com.lemon.entity.Order;
import com.lemon.service.IHouseService;
import com.lemon.service.IOrderService;
import com.lemon.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kane on 18-7-2
 */
@Api(value = "订单controller", description = "订单相关操作")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IHouseService iHouseService;


    @ApiOperation("添加订单")
    @PostMapping("/add")
    public String add(@ApiParam(name = "order", value = "订单信息") @RequestBody() Order order){
        Map<String, Object> map = new HashMap<>();
        House house = iHouseService.findOne(order.getHouseId());
        String img = house.getImg();
        order.setImg(img);
        order.setCreateTime(DateUtils.currTimeToString());

        Order order1 = iOrderService.save(order);
        map.put("msg", "订单已生成");
        map.put("code", "1");
        map.put("obj", order1);
        return JSONArray.toJSONString(map);
    }

    @ApiOperation("查看所有订单")
    @GetMapping("/findAll")
    public String findAll(@ApiParam(name = "id", value = "用户id") @RequestParam(value = "id",required = false) Integer id){
        Map<String, Object> map = new HashMap<>();
        List<Order> orders = iOrderService.findAll(id);
        if(orders.size()>0){
            map.put("msg", "查询成功。");
            map.put("code", "1");
            map.put("obj", orders);
        }else {
            map.put("msg", "没有任何订单信息");
            map.put("code", "-1");
        }
        return JSONArray.toJSONString(map);
    }

    @ApiOperation("查看订单详情")
    @GetMapping("/findOne")
    public String findOne(@ApiParam(name = "id", value = "订单id") @RequestParam(value = "id",required = false) Integer id){
        Map<String, Object> map = new HashMap<>();
        Order order = iOrderService.findOne(id);
        map.put("msg", "查询成功。");
        map.put("code", "1");
        map.put("obj", order);
        return JSONArray.toJSONString(map);
    }
}
