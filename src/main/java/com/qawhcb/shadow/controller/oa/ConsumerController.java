package com.qawhcb.shadow.controller.oa;

import com.qawhcb.shadow.service.IEmployeeService;
import com.qawhcb.shadow.service.IUserService;
import com.qawhcb.shadow.service.impl.UtilsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taoism <br/>
 * Created on 2018/3/25 <br/>
 * 平台对用户的管理
 */
@Api(tags = "consumer oa", description = "平台对的用户管理")
@RestController
@RequestMapping("/oa/consumer")
public class ConsumerController {


    @Autowired
    private IUserService iUserService;

    @Autowired
    private UtilsService utilsService;

    @Autowired
    private IEmployeeService iEmployeeService;
}
