package com.qawhcb.shadow.controller.oa;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Activity;
import com.qawhcb.shadow.service.IActivityService;
import com.qawhcb.shadow.service.IEmployeeService;
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
 * Created on 2018/3/30 <br/>
 * 活动
 */
@Api(tags = "activity oa", description = "活动相关")
@RestController(value = "oaActivityController")
@RequestMapping("/oa/activity")
public class ActivityController {

    @ApiOperation(value = "新增活动")
    @PostMapping(value = "/add/{token}/{account}")
    public String add(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                      @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account,
                      @ApiParam(name = "title", value = "活动标题") @RequestParam(value = "title") String title,
                      @ApiParam(name = "text", value = "活动标题") @RequestParam(value = "text") String text,
                      @ApiParam(name = "files", value = "活动图片") @RequestParam(value = "files") MultipartFile[] files,
                      @ApiParam(name = "covers", value = "活动封面图片") @RequestParam(value = "covers") MultipartFile[] covers) {

        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "activity_c")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }


        Activity save = iActivityService.save(account, text, title, files, covers);

        map.put("code", 1);
        map.put("msg", "发布活动成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);
    }


    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private IActivityService iActivityService;

}
