package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Coupon;
import com.qawhcb.shadow.service.ICouponService;
import com.qawhcb.shadow.service.IEmployeeService;
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
 * Created on 2018/3/25 <br/>
 * 后台优惠券管理
 */
@Api(tags = "coupon app", description = "优惠券操作")
@RestController(value = "appCouponController")
@RequestMapping("/app/coupon")
public class CouponController {

    @ApiOperation(value = "领取优惠券")
    @PostMapping(value = "/receive/{token}/{userId}")
    public String receive(@ApiParam(name = "token", value = "帐号") @PathVariable(value = "token") String token,
                          @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                          @ApiParam(name = "couponId", value = "优惠券Id") @RequestParam(value = "couponId") Integer couponId) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        Coupon save = iCouponService.receive(userId, couponId);

        map.put("code", 1);
        if (save == null) {
            map.put("msg", "活动结束");
        } else {
            map.put("msg", "领取优惠券成功");
        }
        map.put("obj", save);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "查询用户持有优惠券", notes = "优惠券有效期暂存lable1中")
    @GetMapping(value = "/find")
    public String find(@ApiParam(name = "userId", value = "用户id") @RequestParam(value = "userId") Integer userId) {

        Map<String, Object> map = new HashMap<>(8);

        List<Coupon> coupons = iCouponService.find(userId);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", coupons);

        return JSONArray.toJSONString(map);
    }

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private ICouponService iCouponService;

}
