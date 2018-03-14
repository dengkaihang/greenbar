package com.qawhcb.shadow;

import com.qawhcb.shadow.utils.GetPrimaryKey;
import com.qawhcb.shadow.utils.IdentifyingCodeUtils;
import com.qawhcb.shadow.utils.MD5Util;
import org.junit.Test;

/**
 * @author Taoism <br/>
 * Created on 2018/3/8 <br/>
 */
public class TestController {

    @Test
    public void test() {
        IdentifyingCodeUtils.sendIdentifyingCode("15180047865");
    }


    @Test
    public void test1() {

        for (int i = 1; i < 100; i++) {

            String code = GetPrimaryKey.getIdentifyingCode(6);

            String s = MD5Util.md5(code + "shadow");

            if (s.contains("/")) {
                System.out.println(code + ":" + s);
            }

        }

        System.out.println("结束");

    }
}
