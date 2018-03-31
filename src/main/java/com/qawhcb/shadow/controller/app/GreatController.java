package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Great;
import com.qawhcb.shadow.service.IGreatService;
import com.qawhcb.shadow.service.impl.UtilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taoism <br/>
 * Created on 2018/3/23 <br/>
 */
@Api(description = "点赞操作", tags = "great app")
@RestController(value = "/app/great")
public class GreatController {

    @ApiOperation(value = "用户点赞或者取消点赞")
    @PostMapping(value = "/click/{token}/{userId}")
    public String click(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                        @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                        @ApiParam(name = "postId", value = "所点击文章的id") @RequestParam(value = "postId") Integer postId) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }

        Map<String, Object> map = new HashMap<>(8);

        Great ispraise = iGreatService.click(userId, postId);

        map.put("code", 1);

        map.put("msg", ispraise == null ? "操作失败" : "操作成功");
        map.put("obj", ispraise);

        return JSONArray.toJSONString(map);
    }

    @Autowired
    private IGreatService iGreatService;

    @Autowired
    private UtilsService utilsService;
}
