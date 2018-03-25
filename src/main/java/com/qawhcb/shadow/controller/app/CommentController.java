package com.qawhcb.shadow.controller.app;


import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Comment;
import com.qawhcb.shadow.entity.dataModel.CommentVo;
import com.qawhcb.shadow.service.ICommentService;
import com.qawhcb.shadow.service.impl.UtilsService;
import com.qawhcb.shadow.utils.GetLocalDateTime;
import com.qawhcb.shadow.utils.UploadFileUtils;
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
 * Created on 2018/3/14 <br/>
 * 商品相关信息
 */
@Api(tags = "comment app", description = "评论相关操作")
@RestController(value = "appCommentController")
@RequestMapping(value = "/app/comment")
public class CommentController {

    @ApiOperation(value = "查询指定商品的所有评论")
    @GetMapping(value = "/findAll/{goodsId}")
    public String findAll(@ApiParam(name = "goodsId", value = "商品Id") @PathVariable(value = "goodsId") Integer goodsId) {

        Map<String, Object> map = new HashMap<>(8);

        List<CommentVo> commentVos = iCommentService.findAll(goodsId);

        String depictNum = "0";
        String speedNum = "0";
        String serviceNum = "0";
        String totalPointsNum = "0";

        int size = commentVos.size();
        if (size > 0) {

            depictNum = commentVos.get(0).getDepictNum();
            speedNum = commentVos.get(0).getSpeedNum();
            serviceNum = commentVos.get(0).getServiceNum();
            totalPointsNum = commentVos.get(0).getTotalPointsNum();
        }

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("depictNum", depictNum);
        map.put("speedNum", speedNum);
        map.put("serviceNum", serviceNum);
        map.put("totalPointsNum", totalPointsNum);
        map.put("obj", commentVos);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "添加评价")
    @PostMapping(value = "/add")
    public String add(@ApiParam(name = "orderId", value = "订单Id") @RequestParam(value = "orderId") String orderId,
                          @ApiParam(name = "userId", value = "评论用户Id") @RequestParam(value = "userId") Integer userId,
                          @ApiParam(name = "text", value = "评论类容") @RequestParam(value = "text") String text,
                          @ApiParam(name = "imgs", value = "评论图片") @RequestParam(value = "imgs") MultipartFile[] imgs,
                          @ApiParam(name = "depict", value = "描述相符评分") @RequestParam(value = "depict") String depict,
                          @ApiParam(name = "service", value = "服务评分") @RequestParam(value = "service") String service,
                          @ApiParam(name = "speed", value = "发货速度评分") @RequestParam(value = "speed") String speed) {

        Map<String, Object> map = new HashMap<>(8);

        String imgNames = UploadFileUtils.orderFileUpload(imgs, orderId);

        Comment comment = new Comment();

        comment.setOrderId(orderId);
        comment.setUserId(userId);
        comment.setText(text);
        comment.setImg(imgNames);
        comment.setDepict(depict);
        comment.setService(service);
        comment.setSpeed(speed);
        comment.setCommentTime(GetLocalDateTime.getLocalDataTime());

        Comment targer = iCommentService.save(comment);

        map.put("code", 1);
        map.put("msg", "评价成功");
        map.put("obj", targer);

        return JSONArray.toJSONString(map);

    }


    @Autowired
    private ICommentService iCommentService;

    @Autowired
    private UtilsService utilsService;
}
