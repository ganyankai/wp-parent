package com.catt.common.util.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

import java.io.Writer;

/**
 * xstream工厂
 *
 */
public class XStreamFactory {

	private static final String PREFIX_CDATA = "<![CDATA[";
	private static final String SUFFIX_CDATA = "]]>";

	/**
	 * 初始化XStream 可支持某一字段可以加入CDATA标签 如果需要某一字段使用原文
	 * 就需要在String类型的text的头加上"&lt;![CDATA["和结尾处加上"]]&gt;"标签， 以供XStream输出时进行识别
	 *
	 * @param isAddCDATA
	 *            是否支持CDATA标签
	 * @return
	 */
	public static XStream init(boolean isAddCDATA) {
		XStream xstream = null;
		if (isAddCDATA) {
			xstream = new XStream(new XppDriver() {
				public HierarchicalStreamWriter createWriter(Writer out) {
					return new PrettyPrintWriter(out, new XmlFriendlyNameCoder("_-", "_")) {
						protected void writeText(QuickWriter writer, String text) {
							if (!text.startsWith(PREFIX_CDATA)) {
								text = PREFIX_CDATA + text + SUFFIX_CDATA;
							}
							writer.write(text);
						}
					};
				};
			});
		} else {
			// 直接用jaxp dom来解释
			xstream = new XStream(new DomDriver());
			// 指定编码解析器,直接用jaxp dom来解释
			// xstream=new XStream(new DomDriver("utf-8"));
		}
		return xstream;
	}
}
