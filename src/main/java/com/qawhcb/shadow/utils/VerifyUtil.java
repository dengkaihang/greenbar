package com.qawhcb.shadow.utils;


public class VerifyUtil {
	public static boolean verify(String code, String codeMsg) {
		codeMsg = MD5Util.md5(codeMsg);
		if(null != code && null != codeMsg && code.equals(codeMsg)) {
			return true;
		}
		return  false;
	}
}
