package com.qawhcb.shadow.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 主键生成器,默认32位
 * 
 * @author 辰
 *
 */
public class GetPrimaryKey {

	// 生成随机数长度
	protected static int length = 32;

	// 随机数字典
	private static char[] chars = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
			'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9' };
	// 随机数字典
	private static char[] charsToNum = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private static Random ran = new Random();

	/**
	 * 生成指定位数的验证码（推荐6位）
	 * 
	 * @param leng
	 *            验证码长度
	 * 
	 * @return 指定位数的纯数字验证码
	 */
	public static String getIdentifyingCode(int leng) {

		StringBuffer identifying = new StringBuffer();

		for (int i = 0; i < leng; i++) {
			identifying.append(ran.nextInt(10));
		}

		return identifying.toString();
	}

	/**
	 * 生成32位数的纯数字主键
	 * 
	 * @return 自动生成32位纯数字随机数
	 */
	public static Integer getPrimayKeyToNum() {

		StringBuffer str = new StringBuffer();

		// 循环生成32位数
		for (int i = 0; i < length; i++) {
			str.append(charsToNum[ran.nextInt(charsToNum.length)]);
		}

		return Integer.getInteger(str.toString());
	}

	/**
	 * 生成32位数的主键
	 * 
	 * @return 自动生成32位随机数
	 */
	public static String getPrimayKey() {

		StringBuffer str = new StringBuffer();

		// 循环生成32位数
		for (int i = 0; i < length; i++) {
			str.append(chars[ran.nextInt(chars.length)]);
		}

		return str.toString();
	}

	/**
	 * 按照指定位置添加词缀
	 * 
	 * @param position
	 *            要追加词缀的位置（"prefix"，"suffix"）。默认为prefix
	 * @param affix
	 *            要追加的字符
	 * @return 指定位置和指定次生成的随机数
	 */
	public static String getPrimayKey(String position, String affix) {

		StringBuffer str = new StringBuffer();

		// 判断词缀位置
		if ("suffix".equalsIgnoreCase(position)) {
			// 判断词缀长度。大于要生成长度就截取，不大于就生成
			if (affix.length() >= length) {
				str.append(affix.substring(0, length));
			} else {
				for (int i = 0; i < length - affix.length(); i++) {
					str.append(chars[ran.nextInt(chars.length)]);
				}
				str.append(affix);
			}
		} else {
			// 判断词缀长度。大于要生成长度就截取，不大于就生成
			if (affix.length() >= length) {
				str.append(affix.substring(0, length));
			} else {
				str.append(affix);
				for (int i = 0; i < length - affix.length(); i++) {
					str.append(chars[ran.nextInt(chars.length)]);
				}
			}
		}
		return str.toString();
	}

	/**
	 * 按照系统时间，生成纯数字主键
	 * 
	 * @return 生成纯数字随机数
	 */
	public static Integer getPrimayKeyByTimeToNum() {

		StringBuffer str = new StringBuffer();

		// 追加日期
		String time = getTime();
		str.append(time);

		// 循环生成32位数
		for (int i = 0; i < length - time.length(); i++) {
			str.append(chars[ran.nextInt(chars.length)]);
		}

		String strEnd = str.length() > length ? str.substring(0, length) : str.toString();

		return Integer.parseInt(strEnd);
	}

	/**
	 * 按照系统时间，生成主键
	 * 
	 * @return 生成随机数
	 */
	public static String getPrimayKeyByTime() {

		StringBuffer str = new StringBuffer();

		// 追加日期
		String time = getTime();
		str.append(time);

		// 循环生成32位数
		for (int i = 0; i < length - time.length(); i++) {
			str.append(chars[ran.nextInt(chars.length)]);
		}

		return str.length() > length ? str.substring(0, length) : str.toString();
	}

	/**
	 * 按照系统时间、指定位置添加词缀
	 * 
	 * @param position
	 *            要追加词缀的位置（"prefix"，"suffix"）。默认为prefix
	 * @param affix
	 *            要追加的字符
	 * @return 指定位置和指定词缀生成的随机数
	 */
	public static String getPrimayKeyByTime(String position, String affix) {

		StringBuffer str = new StringBuffer();

		// 判断词缀位置
		if ("suffix".equalsIgnoreCase(position)) {
			// 判断词缀长度。大于要生成长度就截取，不大于就生成
			if (affix.length() >= length) {
				str.append(affix.substring(0, length));
			} else {
				// 追加日期
				String time = getTime();
				str.append(time);
				for (int i = 0; i < length - affix.length() - time.length(); i++) {
					str.append(chars[ran.nextInt(chars.length)]);
				}

				str.append(affix);
			}
		} else {
			// 判断词缀长度。大于要生成长度就截取，不大于就生成
			if (affix.length() >= length) {
				str.append(affix.substring(0, length));
			} else {
				str.append(affix);

				// 追加日期
				str.append(getTime());
				for (int i = 0; i < length - str.length(); i++) {
					str.append(chars[ran.nextInt(chars.length)]);
				}
			}
		}

		return str.length() > length ? str.substring(0, length) : str.toString();
	}

	private static String getTime() {
		// 设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		// 为获取当前系统时间
		return df.format(new Date());
	}
}
