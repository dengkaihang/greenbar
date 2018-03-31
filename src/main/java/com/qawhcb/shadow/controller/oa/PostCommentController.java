package com.qawhcb.shadow.controller.oa;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.dataModel.PostCommentVo;
import com.qawhcb.shadow.service.IEmployeeService;
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
@Api(tags = "postComment oa", description = "社区评论相关")
@RestController(value = "oaPostCommentController")
@RequestMapping(value = "/oa/postComment")
public class PostCommentController {

    @ApiOperation(value = "查看当前帖子所有评论")
    @GetMapping(value = "/find/{token}/{account}")
    public String find(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                       @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account,
                       @ApiParam(name = "postId", value = "帖子id", required = true) @RequestParam() Integer postId) {


        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "postComment_r")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }

        List<PostCommentVo> postComments = iPostCommentService.findByPostId(postId);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", postComments);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "删除帖子评论")
    @DeleteMapping(value = "/find/{token}/{account}/{postId}")
    public String delete(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                         @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account,
                         @ApiParam(name = "postId", value = "帖子id", required = true) @PathVariable() Integer postId) {


        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "postComment_d")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }

        iPostCommentService.delete(postId);

        map.put("code", 1);
        map.put("msg", "删除成功");

        return JSONArray.toJSONString(map);
    }

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IPostCommentService iPostCommentService;

    @Autowired
    private IEmployeeService iEmployeeService;
}
