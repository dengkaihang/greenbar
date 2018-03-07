package com.qawhcb.shadow.controller.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
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
    @ResponseBody
    public String regist(@ApiParam(name = "phone", value = "验证码") @PathVariable String code,
                         @ApiParam(name = "phone", value = "用户输入的验证码") @PathVariable String codeMsg,
                        @ApiParam(name = "store", value = "店铺") @RequestBody(required = true) Store store){
        JSONObject obj = new JSONObject();
        if(VerifyUtil.verify(code, codeMsg)){
            store = iStoreService.selectByPhone(store.getPhone());
            if(store != null){
                obj.put("msg", "此号码已注册，请直接登录");
                obj.put("code", "-1");
                return obj.toString();
            }
            iStoreService.regist(store);
            obj.put("msg", "注册成功");
            obj.put("code", "1");
        }else {
            obj.put("msg", "验证码错误");
            obj.put("code", "0");
        }
        return obj.toString();
    }

    /**
     * 密码登录
     * @param phone
     * @param password
     * @return
     */
    @ApiOperation("店铺密码登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public String login(@ApiParam(name = "phone", value = "手机号码") @RequestBody(required = true) String phone,
                        @ApiParam(name = "password", value = "密码") @RequestBody(required = true) String password){
        Store store = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", "登录失败，请核对账号和密码是否正确");
        map.put("code", "-1");
        store = iStoreService.login(phone, password);
        if(store != null){
            map.put("msg", "登录成功");
            map.put("code", "1");
            map.put("store", store);
        }

        return JSONArray.toJSONString(map);
    }

    /**
     * 重置密码
     * @param code
     * @param codeMsg
     * @param phone
     * @param password
     * @return
     */
    @ApiOperation("店铺密码登录")
    @PostMapping(value = "/modify")
    @ResponseBody
    public String modify(@ApiParam(name = "phone", value = "验证码") @PathVariable String code,
                         @ApiParam(name = "phone", value = "用户输入的验证码") @PathVariable String codeMsg,
                         @ApiParam(name = "phone", value = "手机号码") @RequestBody(required = true) String phone,
                         @ApiParam(name = "password", value = "密码") @RequestBody(required = true) String password){
        JSONObject obj = new JSONObject();
        if(VerifyUtil.verify(code, codeMsg)){
            Store store = iStoreService.selectByPhone(phone);
            store.setPassword(password);
            iStoreService.modify(store);
            obj.put("msg", "重置密码成功");
            obj.put("code", "1");
        }else {
            obj.put("msg", "重置密码失败，验证码错误");
            obj.put("code", "-1");
        }
        return obj.toString();
    }


}
