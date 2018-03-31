package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Goods;
import com.qawhcb.shadow.entity.dataModel.GoodsVo;
import com.qawhcb.shadow.service.IGoodsService;
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
 * Created on 2018/3/12 <br/>
 * 商品相关信息
 */
@Api(tags = "goods app", description = "店铺商品相关操作")
@RestController(value = "appGoodsController")
@RequestMapping(value = "/app/goods")
public class GoodsController {

    @ApiOperation(value = "查询所有商品", notes = "goods对象 label1暂存价格,label2暂存销售总量")
    @GetMapping(value = "/findAll/{page}")
    public String save(@ApiParam(name = "page", value = "当前页") @PathVariable(value = "page") int page,
                       @ApiParam(name = "type", value = "查询类型") @RequestParam() String type) {
        Map<String, Object> map = new HashMap<>(8);

        if (page < 1) {
            page = 1;
        }

        List<Goods> all = iGoodsService.findAllByType(--page, type);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", all);

        return JSONArray.toJSONString(map);
    }


    @ApiOperation(value = "单个商品详情")
    @GetMapping(value = "/findOne/{goodsId}")
    public String findOne(@ApiParam(name = "goodsId", value = "商品Id") @PathVariable(value = "goodsId") Integer goodsId) {

        Map<String, Object> map = new HashMap<>(8);

        GoodsVo obj = iGoodsService.findByGoodsId(goodsId);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", obj);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "查询指定店铺下所有的商品", notes = "goods对象 label2暂存销售总量")
    @GetMapping(value = "/findAll")
    public String findAll(@ApiParam(name = "storeId", value = "店铺id") @RequestParam(value = "storeId") Integer storeId) {

        Map<String, Object> map = new HashMap<>(8);

        List<GoodsVo> allByStore = iGoodsService.findAllByStore(storeId);

        map.put("code", 1);
        map.put("msg", "查询成功");
        map.put("obj", allByStore);

        return JSONArray.toJSONString(map);

    }


    @Autowired
    private IGoodsService iGoodsService;

//    @Autowired
//    private UtilsService utilsService;
//
//    @Autowired
//    private IOrderService iOrderService;

}
