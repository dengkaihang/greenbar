package com.qawhcb.shadow.controller.web;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Store;
import com.qawhcb.shadow.service.IStoreService;
import com.qawhcb.shadow.utils.VerifyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 店铺ｃｏｎｔｒｏｌｌｅｒ
 * Created by kane on 18-3-7
 */
@Api(value = "店铺类(技师账户信息)", description = "店铺登录注册")
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private IStoreService iStoreService;

    /**
     * 店铺注册
     * @param code　系统发送的验证码
     * @param codeMsg　输入的验证码
     * @param store　店铺对象
     * @return
     */
    @ApiOperation("店铺注册")
    @PostMapping(value = "/regist/{code}/{codeMsg}")
    public String regist(@ApiParam(name = "phone", value = "验证码") @PathVariable String code,
                         @ApiParam(name = "phone", value = "用户输入的验证码") @PathVariable String codeMsg,
                        @ApiParam(name = "store", value = "店铺") @RequestBody(required = true) Store store){
        Map<String, Object> map = new HashMap<>();
        if(VerifyUtil.verify(code, codeMsg)){
            iStoreService.regist(store);
            map.put("msg", "注册成功");
            map.put("code", "1");
            map.put("obj", store);
        }else {
            map.put("msg", "验证码错误");
            map.put("code", "-1");
        }
        return JSONArray.toJSONString(map);
    }


    @ApiOperation("店铺密码登录")
    @PostMapping(value = "/login")
    public String login(@ApiParam(name = "phone", value = "手机号码") @RequestBody(required = true) String phone,
                        @ApiParam(name = "password", value = "密码") @RequestBody(required = true) String password){
        Map<String, Object> map = new HashMap<>();


        return JSONArray.toJSONString(map);
    }
}
