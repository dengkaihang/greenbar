package com.qawhcb.shadow.controller.oa;

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
@Api(tags = "coupon oa", description = "优惠券操作")
@RestController(value = "oaCouponController")
@RequestMapping("/oa/coupon")
public class CouponController {

    @ApiOperation(value = "优惠券发布", notes = "优惠券过期时间，以天数计算")
    @PostMapping(value = "/publish/{token}/{account}")
    public String publish(@ApiParam(name = "token", value = "帐号") @PathVariable(value = "token") String token,
                       @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account,
                       @ApiParam(name = "coupon", value = "优惠券") @RequestBody() Coupon coupon) {

        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);


        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "coupon_c")) {
            map.put("code", -2);

            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }

        coupon.setEmployeeAccount(account);

        Coupon save = iCouponService.publish(coupon);

        map.put("code", 1);
        map.put("msg", "添加优惠券成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "查看全部优惠券")
    @GetMapping(value = "/find/{token}/{account}")
    public String find(@ApiParam(name = "token", value = "帐号") @PathVariable(value = "token") String token,
                          @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account) {

        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);


        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "coupon_c")) {
            map.put("code", -2);

            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }


        List<Coupon> coupons = iCouponService.findNotDel();

        map.put("code", 1);
        map.put("msg", "添加优惠券成功");
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
