package com.qawhcb.shadow.controller.oa;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Employee;
import com.qawhcb.shadow.service.IEmployeeService;
import com.qawhcb.shadow.service.impl.UtilsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Taoism <br/>
 * Created on 2018/3/26 <br/>
 * 员工相关
 */
@Api(tags = "employee oa", description = "平台管理员")
@RestController(value = "oaEmployeeController")
@RequestMapping("/oa/employee")
public class EmployeeController {

    @ApiOperation(value = "员工登录")
    @PostMapping(value = "/login")
    public String save(@ApiParam(name = "account", value = "帐号") @RequestParam(value = "account") String account,
                       @ApiParam(name = "password", value = "密码") @RequestParam(value = "password") String password) {

        Map<String, Object> map = new HashMap<>(8);

        Employee employee = iEmployeeService.login(account, password);

        if (employee == null) {

            map.put("code", -1);
            map.put("msg", "用户名或密码错误");
        } else {
            map.put("code", 1);
            map.put("msg", "登录成功");
            map.put("obj", employee);
        }


        return JSONArray.toJSONString(map);
    }


    @ApiOperation(value = "员工注册")
    @PostMapping(value = "/register/{token}/{account}")
    public String register(@ApiParam(name = "token", value = "token") @PathVariable(value = "token") String token,
                           @ApiParam(name = "account", value = "帐号") @PathVariable(value = "account") String account,
                           @ApiParam(name = "employee", value = "新建员工帐号") @RequestBody() Employee employee) {

        // 验证token
        String verifyToken = utilsService.employeeVerifyAndReturn(token, account);
        if (verifyToken != null) {
            // 验证不通过，直接返回错误信息
            return verifyToken;
        }
        Map<String, Object> map = new HashMap<>(8);

        // 验证帐号权限
        if (!iEmployeeService.confirmation(account, "employee_c")) {
            map.put("code", -2);
            map.put("msg", "当前操作权限不够");
            return JSONArray.toJSONString(map);
        }

        Employee register = iEmployeeService.register(employee);

        if (register == null) {
            map.put("code", -1);
            map.put("msg", "帐号重复");
        } else {
            map.put("code", 1);
            map.put("msg", "注册员工成功");
            map.put("obj", register);
        }

        return JSONArray.toJSONString(map);
    }

    @Autowired
    private IEmployeeService iEmployeeService;

    @Autowired
    private UtilsService utilsService;
}
