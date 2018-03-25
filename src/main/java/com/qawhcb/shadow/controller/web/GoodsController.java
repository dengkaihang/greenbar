package com.qawhcb.shadow.controller.web;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Goods;
import com.qawhcb.shadow.entity.dataModel.GoodsVo;
import com.qawhcb.shadow.service.IGoodsService;
import com.qawhcb.shadow.service.impl.UtilsService;
import com.qawhcb.shadow.utils.UploadFileUtils;
import com.qawhcb.shadow.utils.UpdateUtils;
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
 * @author Taoism <br/>
 * Created on 2018/3/12 <br/>
 * 商品相关信息
 */
@Api(tags = "goods web", description = "店铺商品相关操作")
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
        Map<String, Object> map = new HashMap<>(8);

        String names = UploadFileUtils.storeImgUpload(files, storeId + "/cover");

        Goods goods = iGoodsService.updateCoverImg(names);

        map.put("code", 1);
        map.put("msg", "图片上传成功");
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
        Map<String, Object> map = new HashMap<>(8);

        // 测试UpdateUtils
        Goods target = iGoodsService.findOne(goods.getId());

        UpdateUtils.copyNonNullProperties(goods, target);

        target.setStoreId(storeId);

        Goods save = iGoodsService.save(target);

        map.put("code", 1);
        map.put("msg", "添加成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "查询当前店铺下所有商品及套餐信息", notes = "goods的label1暂存页数")
    @GetMapping(value = "/findAllByStore/{storeId}/{page}")
    public String findOne(@ApiParam(name = "storeId", value = "店铺id") @PathVariable(value = "storeId") int storeId,
                          @ApiParam(name = "page", value = "当前页") @PathVariable(value = "page") int page) {

        Map<String, Object> map = new HashMap<>(8);

        if (page < 1) {
            page = 1;
        }

        List<GoodsVo> obj = iGoodsService.findAllByStore(storeId, --page);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", obj);

        return JSONArray.toJSONString(map);
    }

    @Autowired
    private IGoodsService iGoodsService;

    @Autowired
    private UtilsService utilsService;

}
