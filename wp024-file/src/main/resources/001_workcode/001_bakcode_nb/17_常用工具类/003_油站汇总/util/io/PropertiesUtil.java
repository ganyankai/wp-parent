package com.catt.common.util.io;

import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Properties;

/**
 * 属性文件工具类
 * 
 * @author 纪建宏
 *
 */
public class PropertiesUtil {

	/**
	 * 加载属性文件
	 * 
	 * @param file
	 *            文件
	 * @param encoding
	 *            编码
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static Properties loadProperties(File file, String encoding)
			throws UnsupportedEncodingException, IOException {
		return loadProperties(new FileInputStream(file), encoding);
	}

	/**
	 * 加载属性文件
	 * 
	 * @param inputStream
	 *            输入源
	 * @param encoding
	 *            编码
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws IOException
	 */
	public static Properties loadProperties(InputStream inputStream,
			String encoding) throws UnsupportedEncodingException, IOException {
		return loadProperties(new InputStreamReader(inputStream, encoding));
	}

	/**
	 * 加载属性文件
	 * 
	 * @param reader
	 *            输入源
	 * @return
	 * @throws IOException
	 */
	public static Properties loadProperties(Reader reader) throws IOException {
		Properties properties = new Properties();
		properties.load(reader);
		return properties;
	}

	/**
	 * 加载属性文件为LinkedHashMap对象，用于保存配置顺序
	 * 
	 * @param reader
	 *            输入源
	 * @return
	 * @throws IOException
	 */
	public static LinkedHashMap<String, String> loadPropertiesAsLinkedHashMap(
			Reader reader) throws Exception {
		LinkedHashMap<String, String> result = new LinkedHashMap<String, String>();

		PropertiesConfiguration config = new PropertiesConfiguration();
		config.load(reader);
		//本身读取就是正常顺序,还进行转换？？
		Iterator<String> keys = config.getKeys();

		while (keys.hasNext()){
			String key = keys.next();
			String val = config.getString(key);
			result.put(key,val);
		}

		return result;
	}

}
