package com.qawhcb.shadow.controller.web;

/**
 * @author Taoism <br/>
 * Created on 2018/3/12 <br/>
 */

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Goods;
import com.qawhcb.shadow.service.IGoodsService;
import com.qawhcb.shadow.service.impl.UtilsService;
import com.qawhcb.shadow.utils.ImgUploadUtils;
import com.qawhcb.shadow.utils.UpdateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品相关信息
 */
@Api(value = "商品相关操作", description = "店铺商品相关操作")
@RestController(value = "webGoodsController")
@RequestMapping(value = "/web/goods")
public class GoodsController {


    @ApiOperation(value = "商品封面图片上传")
    @PostMapping(value = "/cover/{token}/{storeId}")
    public String uploadImg(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                            @ApiParam(name = "storeId", value = "店铺id") @PathVariable(value = "storeId") Integer storeId,
                            @ApiParam(name = "files", value = "图片文件,files", required = true) @RequestParam("files") MultipartFile[] files) {
        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(16);

        String names = ImgUploadUtils.storeImgUpload(files, storeId + "/cover");

        Goods goods = iGoodsService.updateCoverImg(names);

        map.put("code", 1);
        map.put("mag", "图片上传成功");
        map.put("obj", goods);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "新建商品")
    @PostMapping(value = "/add/{token}/{storeId}")
    public String save(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                            @ApiParam(name = "storeId", value = "店铺id") @PathVariable(value = "storeId") Integer storeId,
                            @ApiParam(name = "goods", value = "商品信息", required = true) @RequestBody() Goods goods) {
        // 验证token
        String verifyToken = utilsService.storeVerifyAndReturn(token, storeId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(16);

//        // 测试UpdateUtils
//        Goods target = iGoodsService.findOne(goods.getId());
//
//        UpdateUtils.copyNonNullProperties(goods, target);

        Goods save = iGoodsService.save(goods);

        map.put("code", 1);
        map.put("mag", "添加成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);
    }



    @Autowired
    private IGoodsService iGoodsService;

    @Autowired
    private UtilsService utilsService;

}
