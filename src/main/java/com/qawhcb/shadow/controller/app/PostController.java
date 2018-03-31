package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Post;
import com.qawhcb.shadow.entity.dataModel.PostVo;
import com.qawhcb.shadow.service.IPostService;
import com.qawhcb.shadow.service.impl.UtilsService;
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
 * Created on 2018/3/20 <br/>
 * 帖子控制类
 */

@Api(tags = "post app", description = "社区相关")
@RestController(value = "appPostController")
@RequestMapping(value = "/app/post")
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


        Post save = iPostService.save(userId, text, files, publishAddress);

        map.put("code", 1);
        map.put("msg", "添加地址成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);
    }


    @ApiOperation(value = "分类查询发贴", notes = "当传递查询类型为1即‘关注’时，requirement 为当前用户id  当传递查询类型为5即‘同城’时，requirement 为当前用户所在城市  " +
            "用户是否点赞暂存 post 的 label1中")
    @GetMapping(value = "/find")
    public String find(@ApiParam(name = "page", value = "当前页") @RequestParam(value = "page", defaultValue = "1") int page,
                       @ApiParam(name = "userId", value = "用户id", required = false) @RequestParam(value = "userId", required = false) Integer userId,
                       @ApiParam(name = "type", value = "分类") @RequestParam(value = "type") String type,
                       @ApiParam(name = "requirement", value = "查询条件", required = false) @RequestParam(value = "requirement", required = false) String requirement) {

        Map<String, Object> map = new HashMap<>(8);

        if (page < 1) {
            page = 1;
        }

        List<PostVo> posts = iPostService.find(userId, type, requirement, --page);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", posts);

        return JSONArray.toJSONString(map);
    }


    @Autowired
    private IPostService iPostService;

    @Autowired
    private UtilsService utilsService;
}
