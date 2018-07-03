package com.lemon.controller;

import com.alibaba.fastjson.JSONArray;
import com.lemon.entity.House;
import com.lemon.entity.Order;
import com.lemon.service.IHouseService;
import com.lemon.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kane on 18-7-2
 */
@Api(value = "房源查询",description = "房源查询")
@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    private IHouseService iHouseService;

    @Autowired
    private IOrderService iOrderService;


    @ApiOperation("根据时间查询房源预定情况")
    @GetMapping("/findByTime")
    public String findByTime(@ApiParam(name = "liveTime", value = "入住时间") @RequestParam(value = "liveTime",required = false) String liveTime,
                             @ApiParam(name = "leaveTime", value = "离开时间") @RequestParam(value = "leaveTime",required = false) String leaveTime){
        Map<String, Object> map = new HashMap<>();
        //Integer[] ids = new Integer[]{};
        List<House> houseList = new ArrayList<>();
        List<House> houses = iHouseService.findAll();

        for(int i=0; i< houses.size(); i++){
            House h=houses.get(i);
            Integer id = h.getId();
            //ids[i]= h.getId();
            List<Order> orders = iOrderService.findByTime(id, liveTime,leaveTime);
            h.setOrders(orders);
            houseList.add(h);
        }
        map.put("msg", "查询成功");
        map.put("code", "1");
        map.put("obj", houseList);

        return JSONArray.toJSONString(map);
    }

    /**
     *查询所有房源
     * @return
     */
    @ApiOperation("查询所有房源,不用传参")
    @GetMapping("/findAll")
    public String findAll(){
        Map<String, Object> map = new HashMap<>();
        List<House> houseList = iHouseService.findAll();
        map.put("msg", "查询成功");
        map.put("code", "1");
        map.put("obj", houseList);
        return JSONArray.toJSONString(map);
    }

    /**
     * 查询房源详情
     * @param id
     * @return
     */
    @ApiOperation("查询房源详情")
    @GetMapping("/findOne")
    public String findOne(@ApiParam(name = "id", value = "房源id") @RequestParam(value = "id",required = false) Integer id){
        Map<String, Object> map = new HashMap<>();
        House house = iHouseService.findOne(id);
        if(null != house){
            map.put("msg", "查询成功");
            map.put("code", "1");
            map.put("obj", house);
        }else{
            map.put("msg", "查询失败,查看id是否正确。");
            map.put("code", "-1");
        }
        return JSONArray.toJSONString(map);
    }
}
