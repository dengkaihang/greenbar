package com.qawhcb.shadow.controller.oa;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author Taoism <br/>
 * Created on 2018/3/25 <br/>
 * 后台优惠券管理
 */
@Api(tags = "coupon oa", description = "优惠券操作")
@RestController
@RequestMapping("/oa/coupon")
public class CouponController {

    @GetMapping("/test")
    public void test2(HttpSession httpSession){
        httpSession.setAttribute("name", "张三");
    }
    @GetMapping("/test2")
    public void test1(HttpSession httpSession){
        httpSession.setAttribute("name", null);
    }

    @GetMapping("/test1")
    public void test3(HttpSession httpSession){
        System.out.println(httpSession.getAttribute("name"));
    }

}
