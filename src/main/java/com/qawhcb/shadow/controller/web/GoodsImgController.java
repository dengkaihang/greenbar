package com.qawhcb.shadow.controller.web;

/**
 * @author Taoism <br/>
 * Created on 2018/3/12 <br/>
 */

import com.qawhcb.shadow.service.IGoodsImgService;
import com.qawhcb.shadow.service.impl.UtilsService;
import com.qawhcb.shadow.utils.ImgUploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 商品图片控制类
 */
@Api(value = "商品图片相关接口", description = "商品图片上传及读取相关")
@RestController
@RequestMapping(value = "/web/goodsImg")
public class GoodsImgController {

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

        String names = ImgUploadUtils.storeImgUpload(files, storeId + "/cover");



        return "";
    }


    @Autowired
    private IGoodsImgService iGoodsImgService;

    @Autowired
    private UtilsService utilsService;
}