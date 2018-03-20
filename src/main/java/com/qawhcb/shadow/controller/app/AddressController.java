package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Address;
import com.qawhcb.shadow.entity.dataModel.CommentVo;
import com.qawhcb.shadow.service.IAddressService;
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
 * Created on 2018/3/16 <br/>
 * 订单邮寄地址
 */
@Api(value = "地址", description = "订单邮寄地址")
@RestController(value = "appAddressController")
@RequestMapping(value = "/app/address")
public class AddressController {

    @ApiOperation(value = "添加常用收货地址")
    @PostMapping(value = "/add/{token}/{userId}")
    public String save(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                       @ApiParam(name = "address", value = "地址对象") @RequestBody() Address address) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);


        Address save = iAddressService.save(address);

        map.put("code", 1);
        map.put("mag", "添加地址成功");
        map.put("obj", save);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "查询所有收货地址")
    @GetMapping(value = "/findAll/{token}/{userId}")
    public String save(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                       @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        List<Address> addresses = iAddressService.findAll(userId);

        map.put("code", 1);
        map.put("mag", "查询收货地址成功");
        map.put("obj", addresses);

        return JSONArray.toJSONString(map);
    }

    @ApiOperation(value = "删除常用地址")
    @GetMapping(value = "/delete/{token}/{userId}")
    public String delete(@ApiParam(name = "token", value = "token验证") @PathVariable(value = "token") String token,
                         @ApiParam(name = "userId", value = "用户id") @PathVariable(value = "userId") Integer userId,
                         @ApiParam(name = "addressId", value = "地址Id") @RequestParam(value = "addressId") Integer addressId) {

        // 验证token
        String verifyToken = utilsService.userVerifyAndReturn(token, userId);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        iAddressService.delete(addressId);

        map.put("code", 1);
        map.put("mag", "删除成功");

        return JSONArray.toJSONString(map);
    }

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IAddressService iAddressService;

}
