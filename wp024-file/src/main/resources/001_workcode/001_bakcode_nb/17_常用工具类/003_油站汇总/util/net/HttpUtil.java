package com.catt.common.util.net;

import com.catt.common.util.json.JsonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * http接口调用工具类
 *
 * @author 纪建宏
 *
 */
public class HttpUtil {

	/**
	 * 超时配置，单位：毫秒
	 */
	private static int TIMEOUT = 30000;
	/**
	 * 默认编码
	 */
	private static final String DEFAULT_CHARSET = "UTF-8";

	private static final Logger LOG = LoggerFactory.getLogger(com.catt.common.util.net.HttpUtil.class);

	/**
	 * 设置超时时间
	 *
	 * @param timeout
	 *            超时时间
	 */
	public static void setTimeout(int timeout) {
		TIMEOUT = timeout;
	}

	/**
	 * Get方式调用
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String get(String url) throws Exception {
		return get(url, DEFAULT_CHARSET);
	}

	/**
	 * Get方式调用
	 *
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String get(String url, String charset) throws Exception {
		return send(url, "GET", null, charset, null);
	}

	/**
	 * Get方式调用
	 *
	 * @param url
	 * @param params
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public static String get(String url, Map<String, String> params)
			throws Exception {
		return get(url, params, DEFAULT_CHARSET);
	}

	/**
	 * Get方式调用
	 *
	 * @param url
	 * @param params
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public static String get(String url, Map<String, String> params,
							 String charset) throws Exception {
		return get(UrlUtil.joinParam(url, params), charset);
	}

	/**
	 * Post方式调用
	 *
	 * @param url
	 * @param data
	 *            数据
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, String data) throws Exception {
		return post(url, data, DEFAULT_CHARSET);
	}

	/**
	 * Post方式调用
	 *
	 * @param url
	 * @param data
	 *            数据
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, String data, String charset)
			throws Exception {
		return post(url, data, charset, null);
	}

	/**
	 * Post方式调用
	 *
	 * @param url
	 * @param data
	 *            数据
	 * @param charset 数据传输编码
	 * @param headers 自定义Header参数
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, String data, String charset, Map<String, String> headers)
			throws Exception {
		return send(url, "POST", data, charset, headers);
	}

	/**
	 * Post方式调用
	 *
	 * @param url
	 * @param params
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Map<String, Object> params)
			throws Exception {
		return post(url, params, DEFAULT_CHARSET);
	}
	
	/**
	 * Post方式 json调用
	 *
	 * @param url
	 * @param params
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public static String postJson(String url, Map<String, Object> reqMap, Map<String, String> headers)
			throws Exception {
		String jsonData = JsonUtils.toJson(reqMap);
		return sendJson(url, jsonData, DEFAULT_CHARSET, headers);
	}

	/**
	 * Post方式调用
	 *
	 * @param url
	 * @param params
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public static String post(String url, Map<String, Object> params,
							  String charset) throws Exception {
		String data = null;

		if (params != null && !params.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (String key : params.keySet()) {
				if (key != null && params.get(key) != null) {
					String param = key
							+ "="
							+ URLEncoder.encode(params.get(key).toString(),
							"UTF-8") + "&";

					sb.append(param);
				}
			}

			if (sb.length() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}

			data = sb.toString();
		}

		return post(url, data, charset);
	}

	/**
	 * http请求
	 *
	 * @param url
	 * @param type
	 *            请求方式
	 * @param data
	 *            请求数据
	 * @param charset 数据传输编码
	 * @param headers 自定义Header参数
	 * @return
	 * @throws Exception
	 */
	private static String send(String url, String type, String data,
							   String charset, Map<String, String> headers) throws Exception {
		LOG.info("调用http接口【" + url + "】，请求类型【" + type + "】，数据内容【" + data + "】");
		long begin = System.currentTimeMillis();

		OutputStream out = null;
		InputStream in = null;
		HttpURLConnection urlConnection = null;
		try {
			urlConnection = (HttpURLConnection) new URL(url).openConnection();
			// 连接超时
			urlConnection.setConnectTimeout(TIMEOUT);
			// 读取超时 --服务器响应比较慢，增大时间
			urlConnection.setReadTimeout(TIMEOUT);
			urlConnection.setRequestMethod(type);
			urlConnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			if(headers != null){
				for (String key : headers.keySet()){
					urlConnection.setRequestProperty(key, headers.get(key));
				}
			}

			if (urlConnection instanceof HttpsURLConnection) {
				TrustManager[] tm = { new com.catt.common.util.net.MyX509TrustManager() };
				SSLContext sslContext = SSLContext
						.getInstance("SSL", "SunJSSE");
				sslContext.init(null, tm, new SecureRandom());
				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();

				HttpsURLConnection httpsUrlConnection = (HttpsURLConnection) urlConnection;
				httpsUrlConnection.setSSLSocketFactory(ssf);
			}

			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.connect();

			if (StringUtils.isNotEmpty(data)) {
				out = urlConnection.getOutputStream();
				out.write(data.getBytes(charset));
				out.flush();
				out.close();
			}

			in = urlConnection.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in,
					charset));

			String valueString = null;
			StringBuffer bufferRes = new StringBuffer();
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			String result = bufferRes.toString();

			long end = System.currentTimeMillis();
			LOG.info("调用http接口【" + url + "】，返回数据内容【" + result + "】，花费了"
					+ (end - begin) / 1000d + "秒");

			return result;
		} catch (Exception e) {
			long end = System.currentTimeMillis();
			LOG.error("调用http接口【" + url + "】出现异常，请求类型【" + type + "】，数据内容【"
					+ data + "】，花费了" + (end - begin) / 1000d + "秒", e);
			throw e;
		} finally {
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					LOG.error("关闭输出流失败", e);
				}
			}

			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					LOG.error("关闭输入流失败", e);
				}
			}

			if (urlConnection != null) {
				// 关闭连接
				urlConnection.disconnect();
			}
		}
	}
	
	/**
	 * http Json请求
	 *
	 * @param url
	 * @param type
	 *            请求方式
	 * @param data
	 *            请求数据
	 * @param charset 数据传输编码
	 * @param headers 自定义Header参数
	 * @return
	 * @throws Exception
	 */
	private static String sendJson(String url, String jsonData,
							   String charset, Map<String, String> headers) throws Exception {
		LOG.info("调用http接口【" + url + "】，请求类型【POST】，json内容【" + jsonData + "】");
		long begin = System.currentTimeMillis();

		OutputStream out = null;
		InputStream in = null;
		HttpURLConnection urlConnection = null;
		try {
			urlConnection = (HttpURLConnection) new URL(url).openConnection();
			// 连接超时
			urlConnection.setConnectTimeout(TIMEOUT);
			// 读取超时 --服务器响应比较慢，增大时间
			urlConnection.setReadTimeout(TIMEOUT);
			urlConnection.setRequestMethod("POST");
			urlConnection.setRequestProperty("Content-Type",
					"application/json");

			if(headers != null){
				for (String key : headers.keySet()){
					urlConnection.setRequestProperty(key, headers.get(key));
				}
			}

			if (urlConnection instanceof HttpsURLConnection) {
				TrustManager[] tm = { new com.catt.common.util.net.MyX509TrustManager() };
				SSLContext sslContext = SSLContext
						.getInstance("SSL", "SunJSSE");
				sslContext.init(null, tm, new SecureRandom());
				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();

				HttpsURLConnection httpsUrlConnection = (HttpsURLConnection) urlConnection;
				httpsUrlConnection.setSSLSocketFactory(ssf);
			}

			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.connect();

			if (StringUtils.isNotEmpty(jsonData)) {
				out = urlConnection.getOutputStream();
				out.write(jsonData.getBytes(charset));
				out.flush();
				out.close();
			}

			in = urlConnection.getInputStream();
			BufferedReader read = new BufferedReader(new InputStreamReader(in,
					charset));

			String valueString = null;
			StringBuffer bufferRes = new StringBuffer();
			while ((valueString = read.readLine()) != null) {
				bufferRes.append(valueString);
			}
			String result = bufferRes.toString();

			long end = System.currentTimeMillis();
			LOG.info("调用http接口【" + url + "】，返回数据内容【" + result + "】，花费了"
					+ (end - begin) / 1000d + "秒");

			return result;
		} catch (Exception e) {
			long end = System.currentTimeMillis();
			LOG.error("调用http接口【" + url + "】出现异常，请求类型【POST】，数据内容【"
					+ jsonData + "】，花费了" + (end - begin) / 1000d + "秒", e);
			throw e;
		} finally {
			if (out != null) {
				try {
					out.close();
					out = null;
				} catch (IOException e) {
					LOG.error("关闭输出流失败", e);
				}
			}

			if (in != null) {
				try {
					in.close();
					in = null;
				} catch (IOException e) {
					LOG.error("关闭输入流失败", e);
				}
			}

			if (urlConnection != null) {
				// 关闭连接
				urlConnection.disconnect();
			}
		}
	}

}

/**
 * 自定义证书
 *
 * @author Administrator
 *
 */
class MyX509TrustManager implements X509TrustManager {

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

}