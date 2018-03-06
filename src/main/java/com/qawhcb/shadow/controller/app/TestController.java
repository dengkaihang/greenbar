package com.qawhcb.shadow.controller.app;

import com.qawhcb.shadow.service.TestServer;
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
        int i = testServer.test("你好呀呀你好呀呀你好呀呀你好呀呀你好呀呀你好呀呀你好呀呀你好呀呀", 1);

        if (i > 0){
            return "成功";
        }else {
            return "失败！";
        }
    }

    @Autowired
    private TestServer testServer;
}
