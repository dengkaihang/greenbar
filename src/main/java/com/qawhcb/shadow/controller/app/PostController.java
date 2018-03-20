package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Address;
import com.qawhcb.shadow.entity.Post;
import com.qawhcb.shadow.service.IPostService;
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
 * Created on 2018/3/20 <br/>
 * 帖子控制类
 */

@Api(value = "社区", description = "社区相关")
@RestController(value = "appPostController")
@RequestMapping(value = "/app/community")
public class PostController {
    @ApiOperation(value = "发帖")
    @PostMapping(value = "/add/{token}/{userId}")
    public String save(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                       @ApiParam(name = "text", value = "发帖内容") @RequestParam(value = "text") String text,
                       @ApiParam(name = "publishAddress", value = "发帖时所在位置") @RequestParam(value = "publishAddress") String publishAddress,
                       @ApiParam(name = "files", value = "发帖图片") @RequestParam(value = "files") MultipartFile[] files) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);


        Post save = iPostService.save(text,files,publishAddress);

        map.put("code", 1);
        map.put("mag", "添加地址成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);

    }


    @Autowired
    private IPostService iPostService;

    @Autowired
    private UtilsService utilsService;
}
