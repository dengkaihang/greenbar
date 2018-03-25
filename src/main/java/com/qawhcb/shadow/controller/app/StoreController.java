package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qawhcb.shadow.entity.Store;
import com.qawhcb.shadow.entity.dataModel.GoodsVo;
import com.qawhcb.shadow.entity.dataModel.StoreVo;
import com.qawhcb.shadow.service.IStoreService;
import com.qawhcb.shadow.service.impl.UtilsService;
import com.qawhcb.shadow.utils.MD5Util;
import com.qawhcb.shadow.utils.UploadFileUtils;
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
 * @author Taoism <br/>
 * Created on 2018/3/18 <br/>
 * 店铺相关
 */
@Api(tags = "store app", description = "店铺相关")
@RestController(value = "appStoreController")
@RequestMapping(value = "/app/store")
public class StoreController {


    @ApiOperation(value = "店铺查询", notes = "带是否收藏")
    @GetMapping(value = "/findOne")
    public String findOne(@ApiParam(name = "userId", value = "用户Id，如果用户没登录则传null") @RequestParam(value = "userId") Integer userId,
                          @ApiParam(name = "storeId", value = "店铺id") @RequestParam(value = "storeId") Integer storeId) {

        Map<String, Object> map = new HashMap<>(8);

        StoreVo storeVo = iStoreService.findOne(userId, storeId);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", storeVo);

        return JSONArray.toJSONString(map);

    }



    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IStoreService iStoreService;
}
