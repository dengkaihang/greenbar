package com.qawhcb.shadow.controller.app;

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
@RestController(value = "appOrderController")
@RequestMapping(value = "/app/order")
public class OrderController {

    @ApiOperation(value = "新增订单")
    @PostMapping(value = "/add/{token}/{userId}")
    public String save(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                       @ApiParam(name = "order", value = "商品信息", required = true) @RequestBody() Order order) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        Order save = iOrderService.save(order);

        map.put("code", 1);
        map.put("mag", "添加成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "订单文件上传")
    @PatchMapping(value = "/upload/{token}/{userId}/{orderId}")
    public String save(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                       @ApiParam(name = "orderId", value = "订单id") @PathVariable(value = "orderId") Integer orderId,
                       @ApiParam(name = "files", value = "订单处理图片", required = true) @RequestBody() MultipartFile[] files) {
        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        Order order = iOrderService.uploadFile(orderId, files);

        map.put("code", 1);
        map.put("mag", "文件上传成功");
        map.put("obj", order);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "查询用户所有订单")
    @GetMapping(value = "/find/{userId}")
    public String findAll(@ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId) {

        Map<String, Object> map = new HashMap<>(8);

        List<Order> orders = iOrderService.findAll(userId);

        map.put("code", 1);
        map.put("mag", "添加成功");
        map.put("obj", orders);

        return JSONArray.toJSONString(map);

    }

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private UtilsService utilsService;
}
