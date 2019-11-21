package com.catt.common.util.lang;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数组工具类
 *
 */
public class ArrayUtil extends ArrayUtils {

	/**
	 * 从数组中获取指定索引值
	 *
	 * @param array 数组
	 * @param idx   需要获取值的索引
	 * @return 索引值
	 */
	public static <T> T get(T[] array, Integer idx) {
		return get(array, idx, null);
	}

	/**
	 * 从数组中获取指定索引值
	 *
	 * @param array     数组
	 * @param idx       需要获取值的索引
	 * @param defaultVal 默认值
	 * @return T 索引值
	 */
	public static <T> T get(T[] array, Integer idx, T defaultVal) {
		if (isEmpty(array) || idx < 0) {
			return defaultVal;
		}

		if (array.length < idx + 1) {
			return defaultVal;
		}

		return array[idx];
	}

	/**
	 * 拼接数组为字符串
	 * 
	 * @param array
	 *            数组
	 * @return
	 */
	public static String join(String[] array) {
		StringBuilder sb = new StringBuilder();

		for (String e : array) {
			sb.append(",");
			sb.append(e);
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(0);
		}

		return sb.toString();
	}

	/**
	 * 拼接数组为字符串
	 * 
	 * @param array
	 *            数组
	 * @return
	 */
	public static String join(int[] array) {
		StringBuilder sb = new StringBuilder();

		for (int e : array) {
			sb.append(",");
			sb.append(e);
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(0);
		}

		return sb.toString();
	}

	/**
	 * 拼接数组为字符串
	 * 
	 * @param array
	 *            数组
	 * @return
	 */
	public static String join(long[] array) {
		StringBuilder sb = new StringBuilder();

		for (long e : array) {
			sb.append(",");
			sb.append(e);
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(0);
		}

		return sb.toString();
	}

	/**
	 * 拼接数组为字符串
	 * 
	 * @param array
	 *            数组
	 * @return
	 */
	public static String join(double[] array) {
		StringBuilder sb = new StringBuilder();

		for (double e : array) {
			sb.append(",");
			sb.append(e);
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(0);
		}

		return sb.toString();
	}

	/**
	 * 拼接数组为字符串
	 * 
	 * @param array
	 *            数组
	 * @return
	 */
	public static String join(Object[] array) {
		StringBuilder sb = new StringBuilder();

		for (Object e : array) {
			sb.append(",");
			sb.append(e);
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(0);
		}

		return sb.toString();
	}
}
