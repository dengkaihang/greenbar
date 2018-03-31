package com.qawhcb.shadow.controller.app;

import com.alibaba.fastjson.JSONArray;
import com.qawhcb.shadow.entity.Version;
import com.qawhcb.shadow.service.IVersionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 版本更新controller
 * Created by kane on 18-2-1.
 */
@Api(tags = "version app", description = "app版本更新的接口")
@RestController
@RequestMapping("/version")
public class VersionController {

    @Autowired
    private IVersionService iVersionService;

    /**
     * 版本更新
     *
     * @param num
     * @return
     */
    @ApiOperation("版本号对比,提示更新")
    @GetMapping("/update")
    public String update(@ApiParam("版本号") @RequestParam int num) {

        Map<String, Object> map = new HashMap<>();

        List<Version> list = iVersionService.selectVersion();
        Version version = list.get(0);
        int versionNum = version.getVersionNum();
        if (num < versionNum && num > 0) {
            map.put("msg", "有新版本,请更新.");
            map.put("code", "1");
            map.put("obj", version);
        } else if (num == versionNum) {
            map.put("msg", "当前版本已是最新版本");
            map.put("code", "0");
        } else {
            map.put("msg", "版本号错误!");
            map.put("code", "-1");
        }

        return JSONArray.toJSONString(map);
    }
}
