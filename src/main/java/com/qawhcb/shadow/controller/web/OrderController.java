package com.qawhcb.shadow.controller.web;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Order;
import com.qawhcb.shadow.entity.dataModel.OrderVo;
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
@Api(tags = "order web", description = "订单相关操作")
@RestController(value = "webOrderController")
@RequestMapping(value = "/web/order")
public class OrderController {

    @ApiOperation(value = "查询商户所有订单", notes = "总页数预存在ordre的label1中")
    @GetMapping(value = "/findAll/{token}/{storeId}")
    public String findAll(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                          @ApiParam(name = "storeId", value = "商户Id") @PathVariable(value = "storeId") Integer storeId,
                          @ApiParam(name = "page", value = "当前页") @RequestParam(value = "page") Integer page) {

        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }

        page = page<1?1:page;

        Map<String, Object> map = new HashMap<>(8);

        List<OrderVo> orderVos = iOrderService.findAllByStore(storeId, --page);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", orderVos);

        return JSONArray.toJSONString(map);

    }


    @ApiOperation(value = "根据类型查询商户订单", notes = "总页数预存在ordre的label1中")
    @GetMapping(value = "/findByType/{token}/{storeId}")
    public String findByType(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                          @ApiParam(name = "storeId", value = "商户Id") @PathVariable(value = "storeId") Integer storeId,
                          @ApiParam(name = "page", value = "当前页") @RequestParam(value = "page") Integer page,
                             @ApiParam(name = "status", value = "状态") @RequestParam(value = "status") String status) {

        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }

        page = page<1?1:page;

        Map<String, Object> map = new HashMap<>(8);

        List<OrderVo> orderVos = iOrderService.sotreFindByStatus(storeId, status, --page, 5);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", orderVos);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "查询商户订单详情")
    @GetMapping(value = "/find/{token}/{storeId}")
    public String findOne(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                          @ApiParam(name = "storeId", value = "商户Id") @PathVariable(value = "storeId") Integer storeId,
                          @ApiParam(name = "orderId", value = "订单Id") @RequestParam(value = "orderId") String orderId) {

        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        Order order = iOrderService.findOneInStore(orderId);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", order);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "商户确认退款")
    @PatchMapping(value = "/refund/{token}/{storeId}")
    public String refund(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "storeId", value = "商户id") @PathVariable(value = "storeId") Integer storeId,
                         @ApiParam(name = "orderId", value = "订单id") @RequestParam(value = "orderId") String orderId){

        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        String refund = iOrderService.refund(orderId);

        map.put("code", 1);
        map.put("msg", refund);

        return JSONArray.toJSONString(map);
    }


    @ApiOperation(value = "修改订单")
    @PatchMapping(value = "/update/{token}/{storeId}")
    public String update(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "storeId", value = "商户id") @PathVariable(value = "storeId") Integer storeId,
                         @ApiParam(name = "order", value = "订单") @RequestBody() Order order){

        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        Order updateOrder = iOrderService.update(order);

        map.put("code", 1);
        map.put("msg", "订单修改成功");
        map.put("obj", updateOrder);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "拒绝退款")
    @PatchMapping(value = "/refuse/{token}/{storeId}")
    public String refuse(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "storeId", value = "商户id") @PathVariable(value = "storeId") Integer storeId,
                         @ApiParam(name = "orderId", value = "拒绝退款订单id") @RequestParam(value = "orderId") String orderId,
                         @ApiParam(name = "cause", value = "拒绝退款原因") @RequestParam(value = "cause") String cause){

        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        Order order = iOrderService.refuse(orderId, cause);

        map.put("code", 1);
        map.put("msg", "订单拒绝已处理");
        map.put("obj", order);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "订单完成，订单文件上传")
    @PatchMapping(value = "/result/{token}/{storeId}/{orderId}")
    public String result(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "storeId", value = "商户id") @PathVariable(value = "storeId") Integer storeId,
                         @ApiParam(name = "orderId", value = "订单id") @PathVariable(value = "orderId") String orderId,
                         @ApiParam(name = "files", value = "订单处理文件") @RequestParam(value = "files") MultipartFile[] files){

        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        Order order = iOrderService.result(orderId, files);

        map.put("code", 1);
        map.put("msg", "上传成功");
        map.put("obj", order);

        return JSONArray.toJSONString(map);

    }

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private UtilsService utilsService;
}
