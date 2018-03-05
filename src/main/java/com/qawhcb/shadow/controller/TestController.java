package com.qawhcb.shadow.controller;

import com.qawhcb.shadow.dao.OrderDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taoism <br/>
 * Created on 2018/3/5 <br/>
 */
@Api(tags = "tags", value = "value")
@RestController
public class TestController {

    @ApiOperation(value = "value", tags = {"tags"})
    @PostMapping(value = "/test")
    public String test(){
        int i = orderDao.updateNameById("你好", 1);

        if (i > 0){
            return orderDao.findOne(1).toString();
        }else {
            return "失败！";
        }
    }

    @Autowired
    private OrderDao orderDao;
}
