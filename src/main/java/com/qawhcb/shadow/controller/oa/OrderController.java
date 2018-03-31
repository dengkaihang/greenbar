package com.qawhcb.shadow.controller.oa;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Order;
import com.qawhcb.shadow.service.IEmployeeService;
import com.qawhcb.shadow.service.IOrderService;
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
 * Created on 2018/3/29 <br/>
 */
@Api(tags = "order oa", description = "订单相关操作")
@RestController(value = "oaOrderController")
@RequestMapping(value = "/oa/order")
public class OrderController {

    @ApiOperation(value = "查询纠纷，待处理订单", notes = "分页参数暂存 order lable1中")
    @GetMapping(value = "/findAll/{token}/{account}")
    public String findAll(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                          @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account,
                          @ApiParam(name = "page", value = "分页参数") @RequestParam(value = "page") Integer page) {

        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "order_r")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }

        if (page < 1) {
            page = 1;
        }

        List<Order> orders = iOrderService.findBySubmit(--page);

        map.put("code", 1);
        map.put("msg", orders);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "后台直接退款订单")
    @PatchMapping(value = "/refund/{token}/{account}")
    public String refund(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                         @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account,
                         @ApiParam(name = "orderId", value = "处理订单") @RequestParam(value = "orderId") String orderId) {

        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "order_u")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }

        String order = iOrderService.oaRefund(account, orderId);

        map.put("code", 1);
        map.put("msg", order);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "平台拒绝退款")
    @PatchMapping(value = "/refuse/{token}/{account}")
    public String refuse(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "account", value = "操作人员帐号") @PathVariable(value = "account") String account,
                         @ApiParam(name = "orderId", value = "拒绝退款订单id") @RequestParam(value = "orderId") String orderId,
                         @ApiParam(name = "cause", value = "拒绝退款原因") @RequestParam(value = "cause") String cause) {

        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "order_u")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }

        Order order = iOrderService.oaRefuse(account, orderId, cause);

        map.put("code", 1);
        map.put("msg", "订单拒绝已处理");
        map.put("obj", order);

        return JSONArray.toJSONString(map);

    }


    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private UtilsService utilsService;
}
