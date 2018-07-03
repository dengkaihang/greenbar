package com.lemon.controller;

import com.alibaba.fastjson.JSONArray;
import com.lemon.entity.Feedback;
import com.lemon.service.IFeedbackService;
import com.lemon.utils.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kane on 18-7-3
 */
@Api(value = "反馈", description = "用户反馈")
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private IFeedbackService iFeedbackService;

    @ApiOperation("提交反馈")
    @PostMapping("/add")
    public String add(@ApiParam(name = "id", value = "用户id") @RequestParam(value = "id",required = false) Integer id,
                      @ApiParam(name = "text", value = "反馈内容") @RequestParam(value = "text",required = false) String text){

        Map<String, Object> map = new HashMap<>();
        Feedback feedback = new Feedback();
        feedback.setUserId(id);
        feedback.setText(text);
        feedback.setTime(DateUtils.currTimeToString());
        feedback = iFeedbackService.save(feedback);

        map.put("msg", "反馈成功。");
        map.put("code", "1");
        map.put("obj", feedback);

        return JSONArray.toJSONString(map);
    }
}
