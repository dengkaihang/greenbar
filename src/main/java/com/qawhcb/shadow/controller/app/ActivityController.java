package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Activity;
import com.qawhcb.shadow.service.IActivityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Taoism <br/>
 * Created on 2018/3/30 <br/>
 * 活动
 */
@Api(tags = "activity app", description = "app活动查询")
@RestController(value = "appActivityController")
@RequestMapping("/app/activity")
public class ActivityController {

    @ApiOperation(value = "查询活动")
    @GetMapping(value = "/find")
    public String find() {

        Map<String, Object> map = new HashMap<>(8);

        List<Activity> activities = iActivityService.find();

        map.put("code", 1);
        map.put("msg", "查询活动成功");
        map.put("obj", activities);

        return JSONArray.toJSONString(map);
    }


    @Autowired
    private IActivityService iActivityService;

}
