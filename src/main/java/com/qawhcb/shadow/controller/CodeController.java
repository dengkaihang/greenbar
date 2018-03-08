package com.qawhcb.shadow.controller;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.utils.IdentifyingCodeUtils;
import com.qawhcb.shadow.utils.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取验证码
 * Created by kane on 18-3-7
 */
@Api(value = "获取验证码", description = "用于获取验证码操作")
@RestController
@RequestMapping("/code")
public class CodeController {

    /**
     * 获取验证码
     *
     * @param phone 　用户手机号码
     * @return
     */
    @ApiOperation("获取验证码")
    @GetMapping(value = "/identify/{phone}")
    public String identify(@ApiParam("手机号码") @PathVariable(value = "phone", required = true) String phone) {
        Map<String, Object> map = new HashMap<>();
        if (phone == null && phone.trim().isEmpty()) {
            map.put("msg", "电话号码错误");
            map.put("code", "-1");
        } else {

            String code = IdentifyingCodeUtils.sendIdentifyingCode(phone);
            if ("-1".equals(code)) {
                map.put("msg", "验证码发送失败");
                map.put("code", "-1");
            } else {

                code = MD5Util.md5(code);
                return code;
            }
        }

        return JSONArray.toJSONString(map);
    }

}
