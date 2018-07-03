package com.lemon.controller;

import com.alibaba.fastjson.JSONArray;
import com.lemon.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kane on 18-7-2
 */
@Api(value = "添加用户",description = "将微信用户信息保存到后台")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @ApiOperation("添加用户")
    @PostMapping("/add")
    public String add(@ApiParam(name = "portrait", value = "头像") @RequestParam(value = "portrait",required = false) String portrait,
                      @ApiParam(name = "nickname", value = "昵称") @RequestParam(value = "nickname",required = false) String nickname,
                      @ApiParam(name = "phone", value = "手机号码") @RequestParam(value = "phone",required = false) String phone){

        Map<String, Object> map = new HashMap<>();

        iUserService.add(portrait,nickname,phone);

        map.put("msg", "添加用户成功");
        map.put("code", "1");

        return JSONArray.toJSONString(map);
    }
}
