package com.qawhcb.shadow.utils.weixinUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * 微信的配置参数
 * @author 辰
 *
 */
@SuppressWarnings("unused")
public class WeixinConfigUtils {

	private static final Log log = LogFactory.getLog(WeixinConfigUtils.class);


	public static String appid;
	public static String mch_id;
	public static String notify_url;

	static {
		/*ResourceBundle bundle = ResourceBundle.getBundle("resources/sys");
		appid = bundle.getString("appid");
		mch_id = bundle.getString("mch_id");
		notify_url = bundle.getString("notify_url");*/

		try{
			InputStream is = WeixinConfigUtils.class.getResourceAsStream("/properties/weixinSys.properties");
			Properties properties = new Properties();
			properties.load(is);
			
			appid = properties.getProperty("appid");
			mch_id = properties.getProperty("mch_id");
			notify_url = properties.getProperty("notify_url");
			
			/*appid = "wxc2222fa595265736";
			mch_id = "1490420532";
			notify_url = "http://www.lemengxiangju.com/lemon/get.do";*/
			
		}catch(Exception ex){
			log.debug("加载配置文件："+ex.getMessage());
		}
	}

}
