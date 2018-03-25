package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Order;
import com.qawhcb.shadow.entity.dataModel.OrderVo;
import com.qawhcb.shadow.service.IOrderService;
import com.qawhcb.shadow.service.impl.UtilsService;
import com.qawhcb.shadow.utils.UpdateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taoism <br/>
 * Created on 2018/3/14 <br/>
 * 商品相关信息
 */
@Api(tags = "order app", description = "订单相关操作")
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

        order.setUserId(userId);

        Order save = iOrderService.save(order);

        map.put("code", 1);
        map.put("msg", "添加成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "订单文件上传")
    @PatchMapping(value = "/upload/{token}/{userId}/{orderId}")
    public String save(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                       @ApiParam(name = "orderId", value = "订单id") @PathVariable(value = "orderId") String orderId,
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
        map.put("msg", "文件上传成功");
        map.put("obj", order);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "查询用户所有订单")
    @GetMapping(value = "/find/{token}/{userId}")
    public String findAll(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                          @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        List<Order> orders = iOrderService.findAll(userId);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", orders);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "按状态查询订单")
    @GetMapping(value = "/findByStatus/{token}/{userId}")
    public String findByStatus(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                               @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                               @ApiParam(name = "status", value = "状态") @RequestParam(value = "status") String status,
                               @ApiParam(name = "page", value = "当前页") @RequestParam(value = "page") Integer page) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        page = page < 1 ? 1 : page;

        List<OrderVo> orderVos = iOrderService.findByStatus(userId, status, --page, 10);


        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", orderVos);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "查询订单详情")
    @GetMapping(value = "/findOne/{token}/{userId}")
    public String findOne(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                          @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                          @ApiParam(name = "orderId", value = "订单id") @RequestParam(value = "orderId") String orderId) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);


        OrderVo orderVo = iOrderService.findOne(orderId);


        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", orderVo);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "取消订单")
    @GetMapping(value = "/delete/{token}/{userId}")
    public String delete(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                         @ApiParam(name = "orderId", value = "订单id") @RequestParam(value = "orderId") String orderId) {
        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        iOrderService.delete(orderId);

        map.put("code", 1);
        map.put("msg", "取消订单成功");

        return JSONArray.toJSONString(map);
    }


    @ApiOperation(value = "确认收货")
    @GetMapping(value = "/finish/{token}/{userId}")
    public String finish(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                         @ApiParam(name = "orderId", value = "订单id") @RequestParam(value = "orderId") String orderId) {
        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        Order order = iOrderService.finish(orderId);

        map.put("code", 1);
        map.put("msg", "已确认收货");
        map.put("obj", order);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "订单付款")
    @PatchMapping(value = "/pay")
    public String pay(@ApiParam(name = "order", value = "商品信息", required = true) @RequestBody() Order order) {

        Map<String, Object> map = new HashMap<>(8);


        String fruit = iOrderService.pay(order);

        map.put("code", 1);
        if ("".equals(fruit)){
            map.put("msg", "提交支付失败");
        }else {
            map.put("msg", "提交支付成功");
            map.put("fruit", fruit);
        }

        return JSONArray.toJSONString(map);
    }


    @RequestMapping (value = "/fruit_ali")
    public void fruitAli(HttpServletRequest request){
        iOrderService.fruitAli(request);
    }

    @RequestMapping (value = "/fruit_wx")
    public String fruitWx(HttpServletRequest request){
        return  iOrderService.fruitWx(request);
    }

    @ApiOperation(value = "用户申请退款")
    @PatchMapping(value = "/refund/{token}/{userId}")
    public String refund(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                         @ApiParam(name = "orderId", value = "订单id") @RequestParam(value = "orderId") String orderId,
                         @ApiParam(name = "cause", value = "退款原因") @RequestParam(value = "cause") String cause){

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        String refund = iOrderService.askRefund(orderId, cause);

        map.put("code", 1);
        map.put("msg", refund);

        return JSONArray.toJSONString(map);
    }

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private UtilsService utilsService;
}
