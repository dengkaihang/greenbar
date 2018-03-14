package com.qawhcb.shadow.controller.app;

/**
 * @author Taoism <br/>
 * Created on 2018/3/14 <br/>
 */

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Comment;
import com.qawhcb.shadow.entity.dataModel.CommentVo;
import com.qawhcb.shadow.service.ICommentService;
import com.qawhcb.shadow.service.IGoodsService;
import com.qawhcb.shadow.service.impl.UtilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品相关信息
 */
@Api(value = "评论相关", description = "评论相关操作")
@RestController(value = "appCommentController")
@RequestMapping(value = "/app/comment")
public class CommentController {

    @ApiOperation(value = "查询指定商品的所有评论")
    @GetMapping(value = "/findAll/{goodsId}")
    public String findAll(@ApiParam(name = "goodsId", value = "商品Id") @PathVariable(value = "goodsId") int goodsId) {

        Map<String, Object> map = new HashMap<>(16);

        List<CommentVo> commentVos = iCommentService.findAll(goodsId);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", commentVos);

        return JSONArray.toJSONString(map);

    }


    @Autowired
    private ICommentService iCommentService;

    @Autowired
    private UtilsService utilsService;
}
