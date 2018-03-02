package com.qawhcb.shadow.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 获取本地时间，并且按照指定的格式，格式化本地时间
 */
public class GetLocalDateTime {

	private static SimpleDateFormat dateFormat;

	static {
		dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 *  返回当前日期
	 * @return 返回当前日期 (yyyy-MM-dd)
	 */
	public static String getLocalData() {
		String localDataTime = getLocalDataTime();

		return localDataTime.substring(0, localDataTime.indexOf(" "));
	}

	/**
	 *  返回当前时间
	 * @return返回当前时间 (HH:mm:ss)
	 */
	public static String getLocalTime() {
		String localDataTime = getLocalDataTime();

		return localDataTime.substring(localDataTime.indexOf(" "));
	}

	/**
	 *  返回当前日期时间
	 * @return 返回当前日期时间 (yyyy-MM-dd HH:mm:ss)
	 */
	public static String getLocalDataTime() {
		return dateFormat.format(new Date());
	}

}
