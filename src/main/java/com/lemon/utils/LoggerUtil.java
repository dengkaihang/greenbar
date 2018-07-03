package com.lemon.utils;

import org.apache.log4j.Logger;

public class LoggerUtil {

	/**
	 * logger 日志对象
	 */
	private static Logger logger = null;

	public static Logger getLogger() {

		// 判断日志对象是否为空，如果为null则为日志对象赋值
		if (null == logger) {
			// 从当前的异常栈中获取第一个类对象，即使用日志对象的类，获取其类名。用以创建日志
			logger = Logger.getLogger(new Exception().getStackTrace()[1].getClassName());
		}
		return logger;
	}
}
