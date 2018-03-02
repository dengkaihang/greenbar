package com.qawhcb.shadow.controller;

import com.qawhcb.shadow.utils.easemobUtils.ImUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Taoism <br/>
 * Created on 2018/2/7 <br/>
 */
@RestController
@RequestMapping("/im")
public class ImController {

    @PostMapping("/push")

    public void test() {

        String[] users = new String[]{"15180047865"};

        String msg = "我们给你备好的套套怎么办？";

        ImUtils.send(users, msg);

    }

}

