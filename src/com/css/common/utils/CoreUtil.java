package com.css.common.utils;

public class CoreUtil {
	/**
	 * 生成uuid,32位
	 */
	public static String uuid32() {
		return uuid().replaceAll("-", "");
	}

	/**
	 * 生成uuid,35位
	 */
	public static String uuid() {
		return java.util.UUID.randomUUID().toString();
	}
}
