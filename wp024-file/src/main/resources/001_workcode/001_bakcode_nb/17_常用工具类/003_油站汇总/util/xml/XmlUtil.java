package com.catt.common.util.xml;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * xml工具类
 * 
 */
public class XmlUtil {

	/**
	 * 节点文本的属性名称
	 */
	public static final String VALUE_KEY_NAME = "$text";

	/**
	 * 转换xml字符串为map<br>
	 * 属性和子节点均存放于同个map里，对于同名属性或子节点，合并为list对象
	 * 
	 * @param xml
	 *            xml字符串
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException
	 */
	public static Map<String, Object> xmlToMap(String xml)
			throws UnsupportedEncodingException, DocumentException {
		return xmlToMap(xml, false);
	}

	/**
	 * 转换xml字符串为map<br>
	 * 属性和子节点均存放于同个map里，对于同名属性或子节点，合并为list对象
	 * 
	 * @param xml
	 *            xml字符串
	 * @param stringWhenNoAttrAndChild
	 *            遇到无属性和子节点的节点，是否直接返回文本值
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws DocumentException
	 */
	public static Map<String, Object> xmlToMap(String xml,
			boolean stringWhenNoAttrAndChild)
			throws UnsupportedEncodingException, DocumentException {
		xml = preprocess(xml);
		String encoding = "UTF-8";
		xml = getXmlHeader(encoding) + xml;

		// 获得解析器
		SAXReader saxreader = new SAXReader();
		// 获得document文档对象
		Document doc = saxreader.read(new ByteArrayInputStream(xml
				.getBytes(encoding)));
		// 获得根节点
		Element rootElement = doc.getRootElement();

		Map<String, Object> result = new LinkedHashMap<String, Object>();

		Object rootValue = elementToObject(rootElement,
				stringWhenNoAttrAndChild);
		result.put(rootElement.getName(), rootValue);

		return result;
	}

	/**
	 * 转换map为xml字符串<br>
	 * 1、map中的元素值如果为map类型，则转换为子节点<br>
	 * 2、map中的元素值如果不是map和list类型，则转换为属性<br>
	 * 3、map中的元素值如果是list类型，则取出list中的元素，按照1、2步处理
	 * 
	 * @param map
	 * @return
	 */
	public static String mapToXml(Map<String, Object> map) {
		return mapToXml(map, false);
	}

	/**
	 * 转换map为xml字符串<br>
	 * 1、map中的元素值如果为map类型，则转换为子节点<br>
	 * 2、map中的元素值如果不是map和list类型，则根据ignoreAttribute的值转换为子节点或属性<br>
	 * 3、map中的元素值如果是list类型，则取出list中的元素，按照1、2步处理
	 * 
	 * @param map
	 * @param ignoreAttribute
	 *            是否将map对象基本类型属性转为子节点，而不是元素属性
	 * @return
	 */
	public static String mapToXml(Map<String, Object> map,
			boolean ignoreAttribute) {
		return mapToXml(map, false, null);
	}

	/**
	 * 转换map为xml字符串<br>
	 * 1、map中的元素值如果为map类型，则转换为子节点<br>
	 * 2、map中的元素值如果不是map和list类型，则转换为属性<br>
	 * 3、map中的元素值如果是list类型，则取出list中的元素，按照1、2步处理
	 * 
	 * @param map
	 * @param header
	 *            是否包含xml头部
	 * @param encoding
	 *            头部编码
	 * 
	 * @return
	 */
	public static String mapToXml(Map<String, Object> map, boolean header,
			String encoding) {
		return mapToXml(map, false, header, encoding);
	}

	/**
	 * 转换map为xml字符串<br>
	 * 1、map中的元素值如果为map类型，则转换为子节点<br>
	 * 2、map中的元素值如果不是map和list类型，则根据ignoreAttribute的值转换为子节点或属性<br>
	 * 3、map中的元素值如果是list类型，则取出list中的元素，按照1、2步处理
	 * 
	 * @param map
	 * @param ignoreAttribute
	 *            是否将map对象基本类型属性转为子节点，而不是元素属性
	 * @param header
	 *            是否包含xml头部
	 * @param encoding
	 *            头部编码
	 * 
	 * @return
	 */
	public static String mapToXml(Map<String, Object> map,
			boolean ignoreAttribute, boolean header, String encoding) {
		if (map == null || map.isEmpty()) {
			return "";
		}

		if (map.size() != 1) {
			throw new IllegalArgumentException("map的大小不等于1，无法转换为根节点！");
		}

		String rootName = null;
		Set<String> keySet = map.keySet();
		for (String key : keySet) {
			rootName = key;
		}

		Document doc = new DOMDocument();
		// doc.setXMLEncoding(encoding);
		Object rootData = map.get(rootName);
		Element root = null;
		if (rootData instanceof Map) {
			root = mapToElement(rootName, (Map<String, Object>) rootData,
					ignoreAttribute);
		} else {
			root = new DOMElement(rootName);
			root.addCDATA(rootData.toString());
		}

		doc.add(root);

		String xml = doc.asXML();

		xml = preprocess(xml);
		if (header) {
			xml = getXmlHeader(encoding) + xml;
		}

		return xml;
	}

	/**
	 * 获得xml声明头
	 * 
	 * @param encoding
	 *            编码
	 * @return xml声明头
	 */
	public static String getXmlHeader(String encoding) {
		return "<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>";
	}

	/**
	 * 转换map对象为元素
	 * 
	 * @param elementName
	 *            元素名称
	 * @param map
	 *            map对象
	 * @param ignoreAttribute
	 *            是否将map对象基本类型属性转为子节点，而不是元素属性
	 * 
	 * @return
	 */
	private static Element mapToElement(String elementName,
			Map<String, Object> map, boolean ignoreAttribute) {
		Element element = new DOMElement(elementName);

		for (String key : map.keySet()) {
			Object value = map.get(key);

			if (value != null) {
				if (VALUE_KEY_NAME.equals(key)) {
					element.addCDATA(value.toString());
				} else {
					if (value instanceof Map) {
						element.add(mapToElement(key,
								(Map<String, Object>) value, ignoreAttribute));
					} else if (value instanceof List) {
						for (Object o : (List<Object>) value) {
							if (o instanceof Map) {
								element.add(mapToElement(key,
										(Map<String, Object>) o,
										ignoreAttribute));
							} else {
								if (ignoreAttribute) {
									Element e = new DOMElement(key);
									e.addCDATA(o.toString());
									element.add(e);
								} else {
									element.addAttribute(key, o.toString());
								}
							}
						}
					} else {
						if (ignoreAttribute) {
							Element e = new DOMElement(key);
							e.addCDATA(value.toString());
							element.add(e);
						} else {
							element.addAttribute(key, value.toString());
						}
					}
				}
			}
		}

		return element;
	}

	/**
	 * 转换element对象为map或字符串
	 * 
	 * @param element
	 * @param stringWhenNoAttrAndChild
	 *            遇到无属性和子节点的节点，是否直接返回文本值
	 * @return
	 */
	private static Object elementToObject(Element element,
			boolean stringWhenNoAttrAndChild) {
		Map<String, Object> result = new LinkedHashMap<String, Object>();

		// 处理属性值
		List<Attribute> attrList = element.attributes();
		if (attrList != null) {
			for (Attribute attribute : attrList) {
				result.put(attribute.getName(), attribute.getValue());
			}
		}

		List<Element> children = element.elements();
		if (children != null && children.size() > 0) {// 存在子节点
			for (Element child : children) {
				Object elementValue = elementToObject(child,
						stringWhenNoAttrAndChild);
				String name = child.getName();

				if (result.containsKey(name)) {
					Object value = result.get(name);
					if (value instanceof List) {
						((List<Object>) value).add(elementValue);
					} else {
						List<Object> list = new ArrayList<Object>();
						list.add(value);
						list.add(elementValue);
						result.put(name, list);
					}
				} else {
					result.put(name, elementValue);
				}
			}
		} else {
			String text = element.getText();
			if (stringWhenNoAttrAndChild && result.size() == 0) {// 没有属性值
				return text;
			}

			result.put(VALUE_KEY_NAME, text);
		}

		return result;
	}

	/**
	 * 预处理xml字符串
	 * 
	 * @param xml
	 * @return
	 */
	private static String preprocess(String xml) {
		xml = xml.trim();

		if (xml.startsWith("<?")) {
			xml = xml.substring(xml.indexOf("?>") + 2);
		}

		return xml;
	}

	public static void main(String[] args) throws UnsupportedEncodingException,
			DocumentException {
		String xml = "<?xml version=\"1.0\" encoding=\"GBk\"?><cc><bb>123</bb></cc>";
		System.out.println(xml);
		Map<String, Object> map = xmlToMap(xml, true);
		System.out.println(map);
		System.out.println(mapToXml(map, true, true, "GBK"));
	}

}
