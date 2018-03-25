package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Feedback;
import com.qawhcb.shadow.service.IFeedbackService;
import com.qawhcb.shadow.utils.UploadFileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taoism <br/>
 * Created on 2018/3/18 <br/>
 * 反馈相关
 */
@Api(tags = "feedback app", description = "用户反馈")
@RestController(value = "appFeedbackController")
@RequestMapping(value = "/app/feedback")
public class FeedbackController {

    @ApiOperation(value = "提交反馈")
    @PostMapping(value = "/add")
    public String add(@ApiParam(name = "userId", value = "评论用户Id") @RequestParam(value = "userId") Integer userId,
                      @ApiParam(name = "text", value = "评论类容") @RequestParam(value = "text") String text,
                      @ApiParam(name = "imgs", value = "评论图片") @RequestParam(value = "imgs") MultipartFile[] imgs) {

        Feedback feedback = new Feedback();

        String names = null;
        if (imgs != null){
            names = UploadFileUtils.userImgUpload(imgs, userId.toString());
        }

        feedback.setUserId(userId);
        feedback.setText(text);
        feedback.setImg(names);

        Feedback feedback1 = iFeedbackService.save(feedback);


        Map<String, Object> map = new HashMap<>(8);


        map.put("code", 1);
        map.put("msg", "提交反馈成功");
        map.put("obj", feedback1);

        return JSONArray.toJSONString(map);
    }

    @Autowired
    private IFeedbackService iFeedbackService;
}
