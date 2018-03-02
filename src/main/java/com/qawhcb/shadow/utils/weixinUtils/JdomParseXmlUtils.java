package com.qawhcb.shadow.utils.weixinUtils;

import com.qawhcb.shadow.entity.weixin.UnifiedorderResult;
import com.qawhcb.shadow.entity.weixin.WXPayResult;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;


/**
 * 微信解析xml：带有CDATA格式的
 * @author 辰
 *
 */
public class JdomParseXmlUtils {
	
	/**
	 * 1、统一下单获取微信返回
	 * 解析的时候自动去掉CDMA
	 * @param xml
	 */
	@SuppressWarnings("unchecked")
	public static UnifiedorderResult getUnifiedorderResult(String xml){
		UnifiedorderResult unifieorderResult = new UnifiedorderResult();
		try { 
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc;
			doc = (Document) sb.build(source);

			Element root = doc.getRootElement();// 指向根节点
			List<Element> list = root.getChildren();

			if(list!=null&&list.size()>0){
				for (Element element : list) {
					
					System.out.println("key是："+element.getName()+"，值是："+element.getText());
					
					if("return_code".equals(element.getName())){
						unifieorderResult.setReturn_code(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						unifieorderResult.setReturn_msg(element.getText());
					}
					
					if("appid".equals(element.getName())){
						unifieorderResult.setAppid(element.getText());
					}
					
					
					if("mch_id".equals(element.getName())){
						unifieorderResult.setMch_id(element.getText());
					}
					
					if("nonce_str".equals(element.getName())){
						unifieorderResult.setNonce_str(element.getText());
					}
					
					if("sign".equals(element.getName())){
						unifieorderResult.setSign(element.getText());
					}
					
					if("result_code".equals(element.getName())){
						unifieorderResult.setResult_code(element.getText());
					}
					
					if("prepay_id".equals(element.getName())){
						unifieorderResult.setPrepay_id(element.getText());
					}
					
					if("trade_type".equals(element.getName())){
						unifieorderResult.setTrade_type(element.getText());
					}
				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return unifieorderResult;
	}
	
	
	/**
	 * 2、微信回调后参数解析
	 * 解析的时候自动去掉CDMA
	 * @param xml
	 */
	@SuppressWarnings("unchecked")
	public static WXPayResult getWXPayResult(String xml){
		WXPayResult wXPayResult = new WXPayResult();
		try { 
			StringReader read = new StringReader(xml);
			// 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
			InputSource source = new InputSource(read);
			// 创建一个新的SAXBuilder
			SAXBuilder sb = new SAXBuilder();
			// 通过输入源构造一个Document
			Document doc;
			doc = (Document) sb.build(source);

			Element root = doc.getRootElement();// 指向根节点
			List<Element> list = root.getChildren();

			if(list!=null&&list.size()>0){
				for (Element element : list) {
					System.out.println("key是："+element.getName()+"，值是："+element.getText());
					
					if("return_code".equals(element.getName())){
						wXPayResult.setReturn_code(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						wXPayResult.setReturn_msg(element.getText());
					}
					
					
					if("appid".equals(element.getName())){
						wXPayResult.setAppid(element.getText());
					}
					
					if("mch_id".equals(element.getName())){
						wXPayResult.setMch_id(element.getText());
					}
					
					if("nonce_str".equals(element.getName())){
						wXPayResult.setNonce_str(element.getText());
					}
					
					if("sign".equals(element.getName())){
						wXPayResult.setSign(element.getText());
					}
					
					if("result_code".equals(element.getName())){
						wXPayResult.setResult_code(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						wXPayResult.setReturn_msg(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						wXPayResult.setReturn_msg(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						wXPayResult.setReturn_msg(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						wXPayResult.setReturn_msg(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						wXPayResult.setReturn_msg(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						wXPayResult.setReturn_msg(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						wXPayResult.setReturn_msg(element.getText());
					}
					
					if("return_msg".equals(element.getName())){
						wXPayResult.setReturn_msg(element.getText());
					}
					
				}
			}

		} catch (JDOMException e) {
			e.printStackTrace();
		}  catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return wXPayResult;
	}

}
