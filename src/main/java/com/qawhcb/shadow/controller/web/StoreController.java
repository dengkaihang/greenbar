package com.qawhcb.shadow.controller.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qawhcb.shadow.entity.Pack;
import com.qawhcb.shadow.entity.Store;
import com.qawhcb.shadow.service.IStoreService;
import com.qawhcb.shadow.service.impl.UtilsService;
import com.qawhcb.shadow.utils.UploadFileUtils;
import com.qawhcb.shadow.utils.MD5Util;
import com.qawhcb.shadow.utils.VerifyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 店铺ｃｏｎｔｒｏｌｌｅｒ
 * Created by kane on 18-3-7
 */
@Api(tags = "store web", description = "店铺登录注册,重置密码，修改信息，上传图片")
@RestController
@RequestMapping("/web/store")
public class StoreController {

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IStoreService iStoreService;

    /**
     * 店铺注册
     *
     * @param code    　系统发送的验证码
     * @param codeMsg 　输入的验证码
     * @param store   　店铺对象
     * @return
     */
    @ApiOperation("店铺注册")
    @PostMapping(value = "/regist")
    @ResponseBody
    public String regist(@ApiParam(name = "code", value = "验证码") @RequestParam String code,
                         @ApiParam(name = "codeMsg", value = "用户输入的验证码") @RequestParam String codeMsg,
                         @ApiParam(name = "store", value = "店铺对象") @RequestBody(required = true) Store store) {
        JSONObject obj = new JSONObject();
        if (VerifyUtil.verify(code, codeMsg)) {
            Store stores = iStoreService.selectByPhone(store.getPhone());
            int i = 0;
            if (stores != null) {
                obj.put("msg", "此号码已注册，请直接登录");
                obj.put("code", "-1");
                return obj.toString();
            }
            iStoreService.regist(store);
            obj.put("msg", "注册成功");
            obj.put("code", "1");
            obj.put("store", store);
        } else {
            obj.put("msg", "验证码错误");
            obj.put("code", "0");
        }
        return obj.toString();
    }

    /**
     * 密码登录
     *
     * @param phone
     * @param password
     * @return
     */
    @ApiOperation("店铺密码登录")
    @PostMapping(value = "/login")
    @ResponseBody
    public String login(@ApiParam(name = "phone", value = "手机号码") @RequestParam String phone,
                        @ApiParam(name = "password", value = "密码") @RequestParam String password) {
        Store store = null;
        Map<String, Object> map = new HashMap<String, Object>();
        store = iStoreService.login(phone, password);
        if (store != null) {

            store.setToken(MD5Util.createId());

            iStoreService.modify(store);

            Store storeTarget = new Store();

            storeTarget.setId(store.getId());
            storeTarget.setToken(store.getToken());
            storeTarget.setPhone(store.getPhone());
            storeTarget.setIfDel(null);

            map.put("msg", "登录成功");
            map.put("code", "1");
            map.put("store", store);
        } else {
            map.put("msg", "登录失败，请核对账号和密码是否正确");
            map.put("code", "-1");
        }

        return JSONArray.toJSONString(map);
    }

    /**
     * 重置密码
     *
     * @param code
     * @param codeMsg
     * @param phone
     * @param password
     * @return
     */
    @ApiOperation("店铺重置密码")
    @PostMapping(value = "/modify")
    public String modify(@ApiParam(name = "code", value = "验证码") @RequestParam String code,
                         @ApiParam(name = "codeMsg", value = "用户输入的验证码") @RequestParam String codeMsg,
                         @ApiParam(name = "phone", value = "手机号码") @RequestParam String phone,
                         @ApiParam(name = "password", value = "密码") @RequestParam String password) {
        JSONObject obj = new JSONObject();
        if (VerifyUtil.verify(code, codeMsg)) {
            Store store = iStoreService.selectByPhone(phone);
            store.setPassword(password);
            iStoreService.modify(store);
            obj.put("msg", "重置密码成功");
            obj.put("code", "1");
        } else {
            obj.put("msg", "重置密码失败，验证码错误");
            obj.put("code", "-1");
        }
        return obj.toString();
    }


    @ApiOperation("上传图片")
    @PostMapping(value = "/addImages/{token}/{storeId}")
    @ResponseBody
    public String addImages(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                            @ApiParam(name = "storeId", value = "店铺id") @PathVariable(value = "storeId") Integer storeId,
                            @ApiParam(name = "files", value = "图片文件,files", required = true) @RequestParam("files") MultipartFile[] files) {
        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        JSONObject obj = new JSONObject();
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            obj.put("code", -33);
            obj.put("msg", "token error");
            return obj.toJSONString();
        }

        try {
            Store store = iStoreService.selectById(storeId);
            String names = UploadFileUtils.storeImgUpload(files, storeId.toString());
            String[] name = names.split(",");

            String defaultImg = store.getDefaultImg();
            for (int i = 0; i < name.length; i++) {
                if (null == defaultImg) {
                    defaultImg = name[i];
                } else {
                    defaultImg = defaultImg + "," + name[i];
                    iStoreService.modify(store);
                }
            }
            obj.put("msg", "上传图片成功");
            obj.put("code", "1");
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("msg", "上传图片失败");
            obj.put("code", "-1");
        }
        return obj.toJSONString();
    }

    @ApiOperation(value = "上传用户信息图片", notes = "当type 为 card 表示上传的是身份证。type为 portrait 表示上传的是头像")
    @PostMapping(value = "/uploadImages/{token}/{storeId}/{type}")
    @ResponseBody
    public String upload(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                            @ApiParam(name = "storeId", value = "店铺id") @PathVariable(value = "storeId") Integer storeId,
                            @ApiParam(name = "type", value = "图片所属种类") @PathVariable(value = "type") String type,
                            @ApiParam(name = "files", value = "图片文件,files", required = true) @RequestParam("files") MultipartFile[] files) {
        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }

        Map<String, Object> map = new HashMap<>(8);

        Store update = iStoreService.updateImg(storeId, type, files);

        map.put("code", 1);
        map.put("msg", "上传文件成功");
        map.put("obj", update);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation("修改店铺信息")
    @PatchMapping(value = "/update/{token}/{storeId}")
    public String update(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "storeId", value = "店铺id") @PathVariable(value = "storeId") Integer storeId,
                         @ApiParam(name = "store", value = "店铺对象") @RequestBody() Store store) {

        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        store.setId(storeId);

        Store update = iStoreService.update(store);

        map.put("code", 1);
        map.put("msg", "添加成功");
        map.put("obj", update);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation("根据id查询店铺信息")
    @GetMapping(value = "/findOne/{token}/{storeId}")
    public String modify(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "storeId", value = "店铺id") @PathVariable(value = "storeId") Integer storeId) {


        Map<String, Object> map = new HashMap<>(8);

        Store store = iStoreService.find(storeId);

        map.put("code", 1);
        map.put("msg", "添加成功");
        map.put("obj", store);

        return JSONArray.toJSONString(map);

    }

}
