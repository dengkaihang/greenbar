package com.qawhcb.shadow;

import com.qawhcb.shadow.utils.IdentifyingCodeUtils;
import org.junit.Test;

/**
 * @author Taoism <br/>
 * Created on 2018/3/8 <br/>
 */
public class TestController {

    @Test
    public void test(){
        IdentifyingCodeUtils.sendIdentifyingCode("15180047865");
    }
}
