package com.qawhcb.shadow;

import com.qawhcb.shadow.utils.IdentifyingCodeUtils;
import com.qawhcb.shadow.utils.SMSUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShadowApplicationTests {

	@Test
	public void contextLoads() {
		IdentifyingCodeUtils.sendIdentifyingCode("15708409562");
	}

}
