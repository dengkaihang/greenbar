package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.CollectPost;
import com.qawhcb.shadow.entity.dataModel.PostVo;
import com.qawhcb.shadow.service.ICollectPostService;
import com.qawhcb.shadow.service.impl.UtilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taoism <br/>
 * Created on 2018/3/24 <br/>
 * 帖子收藏
 */
@Api(tags = "collectPost app", description = "帖子收藏相关")
@RestController(value = "appCollectPostController")
@RequestMapping(value = "/app/collectPost")
public class CollectPostController {

    @ApiOperation(value = "收藏发帖")
    @PostMapping(value = "/add/{token}/{userId}")
    public String save(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                       @ApiParam(name = "postId", value = "帖子id") @RequestParam(value = "postId") Integer postId) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        CollectPost collectPost = iCollectPostService.save(userId, postId);

        map.put("code", 1);
        map.put("msg", "操作成功");
        map.put("obj", collectPost);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "查询收藏列表")
    @GetMapping(value = "/find/{token}/{userId}")
    public String find(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        List<PostVo> postVos = iCollectPostService.find(userId);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", postVos);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "取消收藏帖子")
    @DeleteMapping(value = "/delete/{token}/{userId}")
    public String delete(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                         @ApiParam(name = "postId", value = "帖子id") @RequestParam(value = "postId") Integer postId) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        iCollectPostService.deleteByUserIdAndPostId(userId, postId);

        map.put("code", 1);
        map.put("msg", "取消收藏成功");

        return JSONArray.toJSONString(map);

    }

    @Autowired
    private UtilsService utilsService;


    @Autowired
    private ICollectPostService iCollectPostService;
}
