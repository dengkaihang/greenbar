package com.qawhcb.shadow.controller.app;

/**
 * @author Taoism <br/>
 * Created on 2018/3/14 <br/>
 */

import com.qawhcb.shadow.entity.Goods;
import com.qawhcb.shadow.entity.Order;
import com.qawhcb.shadow.service.ICommentService;
import com.qawhcb.shadow.service.IOrderService;
import com.qawhcb.shadow.service.impl.UtilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品相关信息
 */
@Api(value = "订单相关", description = "订单相关操作")
@RestController(value = "appOrderController")
@RequestMapping(value = "/app/order")
public class OrderController {

    @ApiOperation(value = "新增订单")
    @GetMapping(value = "/add/{token}/{userId}")
    public String save(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                       @ApiParam(name = "order", value = "商品信息", required = true) @RequestBody() Order order){

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(16);

        Order save = iOrderService.save(order);

    }

    @Autowired
    private IOrderService iOrderService;

    @Autowired
    private UtilsService utilsService;
}
