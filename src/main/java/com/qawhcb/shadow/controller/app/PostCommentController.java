package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.PostComment;
import com.qawhcb.shadow.entity.dataModel.PostCommentVo;
import com.qawhcb.shadow.service.IPostCommentService;
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
 * Created on 2018/3/23 <br/>
 */
@Api(tags = "postComment app", description = "社区评论相关")
@RestController(value = "appPostCommentController")
@RequestMapping(value = "/app/postComment")
public class PostCommentController {

    @ApiOperation(value = "评论发帖")
    @PostMapping(value = "/add/{token}/{userId}")
    public String save(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                       @ApiParam(name = "postComment", value = "帖子评论", required = true) @RequestBody() PostComment postComment) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        postComment.setUserId(userId);

        PostComment save = iPostCommentService.save(postComment);

        map.put("code", 1);
        map.put("msg", "评论成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "查看当前帖子所有评论")
    @GetMapping(value = "/find")
    public String find(@ApiParam(name = "postId", value = "帖子id", required = true) @RequestParam() Integer postId) {

        Map<String, Object> map = new HashMap<>(8);

        List<PostCommentVo> postComments = iPostCommentService.findByPostId(postId);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", postComments);

        return JSONArray.toJSONString(map);
    }

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IPostCommentService iPostCommentService;

}
