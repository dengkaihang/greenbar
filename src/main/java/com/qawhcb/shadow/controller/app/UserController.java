package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.User;
import com.qawhcb.shadow.service.IUserService;
import com.qawhcb.shadow.service.impl.UtilsService;
import com.qawhcb.shadow.utils.LoggerUtil;
import com.qawhcb.shadow.utils.MD5Util;
import com.qawhcb.shadow.utils.VerifyUtil;
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
 * 用户controller
 * Created by kane on 18-3-6
 */
@Api(tags = "user app", description = "用户登录注册操作")
@RestController
@RequestMapping("/app/user")
public class UserController {


    /**
     * 验证码登录／注册
     *
     * @param phone
     * @param code
     * @param codeMsg
     * @return
     */
    @ApiOperation("验证码登录／注册")
    @PostMapping(value = "/codeLogin")
    public String codeLogin(@ApiParam(name = "phone", value = "手机号码") @RequestParam String phone,
                            @ApiParam(name = "code", value = "验证码") @RequestParam String code,
                            @ApiParam(name = "codeMsg", value = "用户输入的验证码") @RequestParam String codeMsg) {
        User user = null;
        Map<String, Object> map = new HashMap<>();
        String token = MD5Util.createId();
        try {
            if (VerifyUtil.verify(code, codeMsg)) {
                user = iUserService.codeLogin(phone);
                if (null != user) {
                    user.setToken(token);
                    iUserService.modify(user);
                    map.put("msg", "验证码登录成功");
                    map.put("code", "1");
                    map.put("obj", user);
                } else {
                    user = iUserService.regist(phone);
                    user.setToken(token);
                    iUserService.modify(user);
                    map.put("msg", "注册成功");
                    map.put("code", "1");
                    map.put("obj", user);
                }
            } else {
                map.put("msg", "验证码无效");
                map.put("code", "-1");
            }
        } catch (Exception e) {
            LoggerUtil.getLogger().error("验证码登录／注册失败" + e.toString());
            map.put("msg", "电话号码或验证码错误");
            map.put("code", "-1");
            return JSONArray.toJSONString(map);
        }

        return JSONArray.toJSONString(map);
    }


    @ApiOperation("用户信息修改")
    @PatchMapping(value = "/update/{token}/{userId}")
    public String update(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                         @ApiParam(name = "user", value = "用户信息") @RequestBody() User user) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        user.setId(userId);

        User save = iUserService.update(user);

        map.put("code", 1);
        map.put("msg", "修改用户信息成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation("用户头像修改")
    @PatchMapping(value = "/updateVar/{token}/{userId}")
    public String updateVar(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                            @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                            @ApiParam(name = "files", value = "用户头像") @RequestBody() MultipartFile[] files) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        User save = iUserService.updateVar(userId, files);

        map.put("code", 1);
        map.put("msg", "修改用户头像成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "第三方绑定验证", notes = "QQ 第三方登录 传入 qq 。 微信第三方登录 传入 wechat")
    @PatchMapping(value = "/isRegisterBySide")
    public String isRegisterBySide(@ApiParam(name = "token", value = "第三方token") @RequestParam(value = "token") String token,
                                   @ApiParam(name = "mode", value = "第三方登录方式") @RequestParam(value = "mode") String mode) {

        Map<String, Object> map = new HashMap<>(8);

        User user = iUserService.isRegisterBySide(token, mode);

        map.put("code", 1);
        map.put("msg", user);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation("第三方绑定")
    @PatchMapping(value = "/loginBySide")
    public String loginBySide(@ApiParam(name = "token", value = "第三方token") @RequestParam(value = "token") String token,
                              @ApiParam(name = "mode", value = "第三方登录方式") @RequestParam(value = "mode") String mode,
                              @ApiParam(name = "phone", value = "绑定的手机号") @RequestParam(value = "phone") String phone) {

        Map<String, Object> map = new HashMap<>(8);

        User save = iUserService.loginBySide(token, mode, phone);

        map.put("code", 1);
        map.put("msg", "登录成功");

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "环信关注好友", notes = "friend 为要添加好友的手机号")
    @PostMapping(value = "/follow/{token}/{userId}")
    public String follow(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                         @ApiParam(name = "friend", value = "要添加的好友id") @RequestParam(value = "friend") Integer friend) {

        Map<String, Object> map = new HashMap<>(8);

        iUserService.follow(userId, friend);

        map.put("code", 1);
        map.put("msg", "关注好友成功");

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "查询环信关注好友", notes = "phones 环信关注 id 所拼接成的手机号字符串")
    @GetMapping(value = "/follow/{token}/{userId}")
    public String findByPhone(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                              @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                              @ApiParam(name = "phones", value = "关注手机号") @RequestParam(value = "phones") String phones) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        List<User> users = iUserService.findByPhones(phones);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", users);

        return JSONArray.toJSONString(map);
    }

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UtilsService utilsService;
}
