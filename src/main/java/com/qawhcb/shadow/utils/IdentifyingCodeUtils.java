package com.qawhcb.shadow.utils;

public class IdentifyingCodeUtils {

	/**
	 * 想指定手机号发送验证码，并将验证码返回
	 * @param phone 指定手机号
	 * @return 生成的验证码
	 */
	public static String sendIdentifyingCode(String phone) {
		
		String code = GetPrimaryKey.getIdentifyingCode(6);
		
		if (phone == null || "".equals(phone)) {
			LoggerUtil.getLogger().error("手机号为空 , 验证码生成错误！");
			throw new RuntimeException("手机号为空！");
		}

		try {
			SMSUtils.send(phone, " 尊敬的用户，您的短信验证码是" + code + "。请尽快完成验证。若非本人发送，请忽略此短信。");
		} catch (Exception e) {
			LoggerUtil.getLogger().error("验证码生成错误！");
		}

		return code;
	}
}
