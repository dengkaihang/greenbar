package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.User;
import com.qawhcb.shadow.service.IUserService;
import com.qawhcb.shadow.service.impl.UtilsService;
import com.qawhcb.shadow.utils.IdentifyingCodeUtils;
import com.qawhcb.shadow.utils.LoggerUtil;
import com.qawhcb.shadow.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户controller
 * Created by kane on 18-3-6
 */
@Api(value = "爱影动萌用户", description = "用户登录注册操作")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IUserService iUserService;

    /**
     * 获取验证码
     * @param phone　用户手机号码
     * @return
     */
    @ApiOperation("获取验证码")
    @GetMapping(value = "/identify/{phone}")
    public String identify(@ApiParam("手机号码") @PathVariable(value = "phone", required = true) String phone){
        Map<String, Object> map = new HashMap<>();
        if(phone == null && phone.trim().isEmpty()){
            map.put("msg", "电话号码格式错误");
            map.put("code", "-1");
        }
        String code = IdentifyingCodeUtils.sendIdentifyingCode(phone);
        code = MD5Util.md5(code);
        return code;
    }

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
                            @ApiParam(name = "phone", value = "验证码") @RequestParam String code,
                            @ApiParam(name = "phone", value = "用户输入的验证码") @RequestParam String codeMsg){
        User user = null;
        Map<String, Object> map = new HashMap<>();
        String token = MD5Util.createId();
        try {
            codeMsg = MD5Util.md5(codeMsg);
            if(null != code && null != codeMsg && code.equals(codeMsg)){
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
