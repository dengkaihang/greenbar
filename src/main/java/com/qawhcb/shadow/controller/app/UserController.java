package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.User;
import com.qawhcb.shadow.service.IUserService;
import com.qawhcb.shadow.utils.LoggerUtil;
import com.qawhcb.shadow.utils.MD5Util;
import com.qawhcb.shadow.utils.VerifyUtil;
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
 * 用户controller
 * Created by kane on 18-3-6
 */
@Api(value = "爱影动萌用户", description = "用户登录注册操作")
@RestController
@RequestMapping("/app/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 验证码登录／注册
     * @param phone
     * @param code
     * @param codeMsg
     * @return
     */
    @ApiOperation("验证码登录／注册")
    @PostMapping(value = "/codeLogin")
    public String codeLogin(@ApiParam(name = "phone", value = "手机号码") @RequestParam String phone,
                            @ApiParam(name = "code", value = "验证码") @RequestParam String code,
                            @ApiParam(name = "codeMsg", value = "用户输入的验证码") @RequestParam String codeMsg){
        User user = null;
        Map<String, Object> map = new HashMap<>();
        String token = MD5Util.createId();
        try {
            if(VerifyUtil.verify(code, codeMsg)){
                user = iUserService.codeLogin(phone);
                if(null != user){
                    user.setToken(token);
                    iUserService.modify(user);
                    map.put("msg", "验证码登录成功");
                    map.put("code", "1");
                    map.put("obj", user);
                }else {
                    user = iUserService.regist(phone);
                    user.setToken(token);
                    iUserService.modify(user);
                    map.put("msg", "注册成功");
                    map.put("code", "0");
                    map.put("obj", user);
                }
            }else {
                map.put("msg", "验证码无效");
                map.put("code", "-1");
            }
        } catch (Exception e) {
            LoggerUtil.getLogger().error("验证码登录／注册失败"+e.toString());
            map.put("msg", "电话号码或验证码错误");
            map.put("code", "-1");
            return JSONArray.toJSONString(map);
        }

        return JSONArray.toJSONString(map);
    }

}
