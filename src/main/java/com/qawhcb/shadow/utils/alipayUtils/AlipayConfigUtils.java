package com.qawhcb.shadow.utils.alipayUtils;

import com.qawhcb.shadow.utils.LoggerUtil;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author Taoism <br/>
 * Created on 2018/3/16 <br/>
 */
public class AlipayConfigUtils {

    /**
     * alipay 固定编码方式
     */
    public final static String charset = "UTF-8";

    public static String ali_web_gateway;
    public static String ali_app_id;
    public static String ali_app_private_key;
    public static String ali_alipay_public_key;
    public static String notify_url;

    static {
        try {
            InputStream is = AlipayConfigUtils.class.getResourceAsStream("/properties/alipaySys.properties");
            Properties properties = new Properties();
            properties.load(is);

            ali_web_gateway = properties.getProperty("ali_web_gateway");
            ali_app_id = properties.getProperty("ali_app_id");
            ali_app_private_key = properties.getProperty("ali_app_private_key");
            ali_alipay_public_key = properties.getProperty("ali_alipay_public_key");
            notify_url = properties.getProperty("notify_url_ali");

        } catch (Exception ex) {
            LoggerUtil.getLogger().error("加载配置文件：" + ex.getMessage());
        }
    }

}
