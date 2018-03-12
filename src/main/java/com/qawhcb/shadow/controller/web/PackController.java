package com.qawhcb.shadow.controller.web;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Pack;
import com.qawhcb.shadow.service.IPackService;
import com.qawhcb.shadow.service.impl.UtilsService;
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
 * Created on 2018/3/12 <br/>
 */
@Api(value = "商品套餐", description = "商品套餐相关操作")
@RestController(value = "webPackController")
@RequestMapping(value = "/web/pack")
public class PackController {

    @ApiOperation(value = "添加商品套餐")
    @PostMapping(value = "/add/{token}/{storeId}")
    public String save(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "storeId", value = "店铺id") @PathVariable(value = "storeId") Integer storeId,
                       @ApiParam(name = "pack", value = "商品套餐", required = true) @RequestBody() Pack pack) {

        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(16);

        Pack save = iPackService.save(pack);

        map.put("code", 1);
        map.put("msg", "添加成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);
    }

    @Autowired
    private IPackService iPackService;

    @Autowired
    private UtilsService utilsService;
}
