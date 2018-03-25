package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.dao.ICollectStoreDao;
import com.qawhcb.shadow.entity.Address;
import com.qawhcb.shadow.entity.CollectStore;
import com.qawhcb.shadow.entity.dataModel.CollectStoreVo;
import com.qawhcb.shadow.service.ICollectStoreService;
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
 * Created on 2018/3/18 <br/>
 * 收藏相关
 */
@Api(tags = "collectStore app", description = "店铺收藏相关")
@RestController(value = "appCollectStoreController")
@RequestMapping(value = "/app/collectStore")
public class CollectStoreController {

    @ApiOperation(value = "是否收藏", notes = "若为收藏则收藏，若已收藏则取消收藏")
    @PostMapping(value = "/add/{token}/{userId}")
    public String save(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                       @ApiParam(name = "storeId", value = "收藏店铺的id") @RequestParam(value = "storeId") Integer storeId) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        CollectStore collectStore =  iCollectStoreService.save(userId, storeId);

        if (collectStore != null){
            map.put("code", 1);
            map.put("mag", "操作成功");
        }else {
            map.put("code", -1);
            map.put("mag", "用户或者店铺未选定");
        }
        map.put("obj", collectStore);

        return JSONArray.toJSONString(map);

    }

    @ApiOperation(value = "查询用户收藏列表")
    @GetMapping(value = "/find/{token}/{userId}")
    public String find(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        List<CollectStoreVo> collectStoreVos =  iCollectStoreService.findAll(userId);

        map.put("code", 1);
        map.put("mag", "查询成功");
        map.put("obj", collectStoreVos);

        return JSONArray.toJSONString(map);

    }


    @Autowired
    private ICollectStoreService iCollectStoreService;

    @Autowired
    private UtilsService utilsService;

}
