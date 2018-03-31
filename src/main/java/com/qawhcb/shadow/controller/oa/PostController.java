package com.qawhcb.shadow.controller.oa;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Post;
import com.qawhcb.shadow.entity.dataModel.PostVo;
import com.qawhcb.shadow.service.IEmployeeService;
import com.qawhcb.shadow.service.IPostService;
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
 * Created on 2018/3/20 <br/>
 * 帖子控制类
 */

@Api(tags = "post oa", description = "社区相关")
@RestController(value = "oaPostController")
@RequestMapping(value = "/oa/post")
public class PostController {


    // 需要修改 。。。。
    @ApiOperation(value = "分类查询发贴", notes = "当传递查询类型为1即‘关注’时，requirement 为当前用户id  当传递查询类型为5即‘同城’时，requirement 为当前用户所在城市  " +
            "用户是否点赞暂存 post 的 label1中")
    @GetMapping(value = "/find/{token}/{account}/")
    public String find(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                       @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account,
                       @ApiParam(name = "page", value = "当前页") @RequestParam(value = "page", defaultValue = "1") int page,
                       @ApiParam(name = "userId", value = "用户id", required = false) @RequestParam(value = "userId", required = false) Integer userId,
                       @ApiParam(name = "type", value = "分类") @RequestParam(value = "type") String type,
                       @ApiParam(name = "requirement", value = "查询条件", required = false) @RequestParam(value = "requirement", required = false) String requirement) {

        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }


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

    @ApiOperation(value = "删除帖子")
    @DeleteMapping(value = "/delete/{token}/{account}/{postId}")
    public String delete(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                         @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account,
                         @ApiParam(name = "postId", value = "删除的帖子id") @PathVariable(value = "postId") Integer postId) {


        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }

        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "post_d")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }

        iPostService.delete(postId);

        map.put("code", 1);
        map.put("msg", "删除成功");

        return JSONArray.toJSONString(map);
    }


    @ApiOperation(value = "设置帖子为推荐")
    @PatchMapping(value = "/delete/{token}/{account}")
    public String nominate(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                           @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account,
                           @ApiParam(name = "postIds", value = "推荐的帖子id") @RequestParam(value = "postIds") Integer[] postIds) {


        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }

        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "post_u")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }

        List<Post> posts = iPostService.nominate(postIds);

        map.put("code", 1);
        map.put("msg", "删除成功");
        map.put("obj", posts);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "查询所有推荐帖子")
    @GetMapping(value = "/delete/{token}/{account}")
    public String findNominate(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                               @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account) {


        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }

        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "post_r")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }

        List<Post> posts = iPostService.findNominate();

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", posts);

        return JSONArray.toJSONString(map);
    }

    @Autowired
    private IPostService iPostService;

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IEmployeeService iEmployeeService;
}
