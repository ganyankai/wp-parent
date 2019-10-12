//package com.umpay.sp.web.util;
 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.commons.lang3.StringUtils;
//import org.apache.log4j.Logger;
 
//import com.umpay.sp.common.LoggerManager;
//import com.umpay.sp.util.StringUtil;
/** 
 * desc:常用正则表达式验证工具类
 * <p>创建人：domain 创建日期：2018年5月21日 </p>
 * @version V1.0  
 */
public class RegexUtils{
//	public final static Logger logger = LoggerManager.getBusiLogger();
	/** 
	 * Email正则表达式="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	 */
	public static final String EMAIL = "\\w+(\\.\\w+)*@\\w+(\\.\\w+)+"; //"^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
	/** 
	 * 电话号码正则表达式= (^(\d{2,4}[-_－—]?)?\d{3,8}([-_－—]?\d{3,8})?([-_－—]?\d{1,7})?$)|(^0?1[35]\d{9}$)  
	 */
	public static final String PHONE = "(^(\\d{2,4}[-_－—]?)?\\d{3,8}([-_－—]?\\d{3,8})?([-_－—]?\\d{1,7})?$)|(^0?1[35]\\d{9}$)" ;  
	/** 
	 * 手机号码正则表达式=^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$
	 */
	public static final String MOBILE ="^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";  
	/** 
	 * Integer正则表达式=^-?(([1-9]\d*$)|0) 
	 */
	public static final String INTEGER = "^-?(([1-9]\\d*$)|0)";  
	/** 
	 * 正整数正则表达式 >=0  ^[1-9]\d*|0$ 
	 */
	public static final String INTEGER_NEGATIVE = "^[1-9]\\d*|0$";  
	/** 
	 * 负整数正则表达式<=0  ^-[1-9]\d*|0$ 
	 */
	public static final String INTEGER_POSITIVE = "^-[1-9]\\d*|0$";      
	/** 
	 * Double正则表达式 ^-?([1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0)$ 
	 */
	public static final String DOUBLE ="^-?([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0)$";  
	/** 
	 * 正Double正则表达式 >=0  ^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$　 
	 */
	public static final String DOUBLE_NEGATIVE ="^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*|0?\\.0+|0$";  
	/** 
	 * 负Double正则表达式 <= 0  ^(-([1-9]\d*\.\d*|0\.\d*[1-9]\d*))|0?\.0+|0$ 
	 */
	public static final String DOUBLE_POSITIVE ="^(-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*))|0?\\.0+|0$";   
	/** 
	 * 年龄正则表达式 ^(?:[1-9][0-9]?|1[01][0-9]|120)$ 匹配0-120岁 
	 */
	public static final String AGE="^(?:[1-9][0-9]?|1[01][0-9]|120)$";  
	/** 
	 * 邮编正则表达式  [0-9]\d{5}(?!\d) 国内6位邮编 
	 */
	public static final String CODE="[0-9]\\d{5}(?!\\d)";    
	/** 
	 * 匹配由数字、26个英文字母或者下划线组成的字符串 ^\w+$ 
	 */
	public static final String STR_ENG_NUM_="^\\w+$";  
	/** 
	 * 匹配由数字和26个英文字母组成的字符串 ^[A-Za-z0-9]+$  
	 */
	public static final String STR_ENG_NUM="^[A-Za-z0-9]+";  
	/** 
	 * 匹配由26个英文字母组成的字符串  ^[A-Za-z]+$ 
	 */
	public static final String STR_ENG="^[A-Za-z]+$";
	/** 
	 * 过滤特殊字符串正则  regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";  
	 */
	public static final String STR_SPECIAL="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	public static final String STR_SPECIAL_="[`~!@#$%^&*()+=|{}':;',\\[\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";//不包含"/"
	public static final String SQL_INJECT_REG = "[#$*|';,]|(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b(select|update|union|and|or|delete|insert|trancate|char|into|substr|ascii|declare|exec|count|master|into|drop|execute)\\b)";
	public static final Pattern sqlPattern = Pattern.compile(SQL_INJECT_REG, Pattern.CASE_INSENSITIVE);
	/*** 
	 * 日期正则 支持： 
	 *  YYYY-MM-DD  
	 *  YYYY/MM/DD  
	 *  YYYY_MM_DD  
	 *  YYYYMMDD 
	 *  YYYY.MM.DD的形式 
	 */
	public static final String DATE_ALL="((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(10|12|0?[13578])([-\\/\\._]?)(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)" + 
			"(11|0?[469])([-\\/\\._]?)(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._]?)(0?2)([-\\/\\._]?)(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([3579][26]00)" +  
			"([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([1][89][0][48])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][0][48])([-\\/\\._]?)" +  
			"(0?2)([-\\/\\._]?)(29)$)|(^([1][89][2468][048])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._]?)(0?2)" +  
			"([-\\/\\._]?)(29)$)|(^([1][89][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$)|(^([2-9][0-9][13579][26])([-\\/\\._]?)(0?2)([-\\/\\._]?)(29)$))";
	/*** 
	 * 日期正则 支持： 
	 *  YYYY-MM-DD  
	 */
	public static final String DATE_FORMAT1="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
	/** 
	 * URL正则表达式 
	 * 匹配 http www ftp 
	 */
	public static final String URL = "^(http|www|ftp|)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?" +  
			"(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*" +  
			"(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$";
 
	/** 
	 * 身份证正则表达式 
	 */
	public static final String IDCARD="((11|12|13|14|15|21|22|23|31|32|33|34|35|36|37|41|42|43|44|45|46|50|51|52|53|54|61|62|63|64|65)[0-9]{4})" +  
			"(([1|2][0-9]{3}[0|1][0-9][0-3][0-9][0-9]{3}" +  
			"[Xx0-9])|([0-9]{2}[0|1][0-9][0-3][0-9][0-9]{3}))";
	/**
	 * 机构代码
	 */
	public static final String JIGOU_CODE = "^[A-Z0-9]{8}-[A-Z0-9]$";
 
	/**
	 * 匹配数字组成的字符串  ^[0-9]+$ 
	 */
	public static final String STR_NUM = "^[0-9]+$";
	/**
	 * desc:验证Email
	 * <p>创建人：domain , 2018年5月21日 上午10:23:13</p>
	 * @param email 邮箱地址
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkEmail(String email) { 
		String regex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?"; 
		return Pattern.matches(regex, email); 
	}
 
	/**
	 * 
	 * desc:验证身份证号码
	 * <p>创建人：domain , 2018年5月21日 上午10:24:03</p>
	 * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
	 * @return 证成功返回true，验证失败返回false
	 */
	public static boolean checkIdCard(String idCard) { 
		String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}"; 
		return Pattern.matches(regex,idCard); 
	}
	public static boolean checkIdCard_X(String idCard) { 
		String regex = "[1-9]\\d{13,16}[xX0-9]{1}"; //身份证号最后一位只能是大写X
		return Pattern.matches(regex,idCard); 
	}
	/**
	 * 
	 * desc:验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
	 * <p>创建人：domain , 2018年5月21日 上午10:25:08</p>
	 * @param mobile 移动、联通、电信运营商的号码段
	 * <p>移动的号段：134(0-8)、135、136、137、138、139、147（预计用于TD上网卡）、150、151、152、157（TD专用）、158、159、187（未启用）、188（TD专用）</p>
	 * <p>联通的号段：130、131、132、155、156（世界风专用）、185（未启用）、186（3g）</p>
	 * <p>电信的号段：133、153、180（未启用）、189</p>
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkMobile(String mobile) { 
		String regex = "(\\+\\d+)?1[34578]\\d{9}$"; 
		return Pattern.matches(regex,mobile); 
	}
 
	/**
	 * 
	 * desc:验证固定电话号码
	 * <p>创建人：domain , 2018年5月21日 上午10:26:57</p>
	 * @param phone 电话号码，格式：国家（地区）电话代码 + 区号（城市代码） + 电话号码，如：+8601012580
	 * <p><b>国家（地区） 代码 ：</b>标识电话号码的国家（地区）的标准国家（地区）代码。它包含从 0 到 9 的一位或多位数字，数字之后是空格分隔的国家（地区）代码。</p>
	 * <p><b>区号（城市代码）：</b>这可能包含一个或多个从 0 到 9 的数字，地区或城市代码放在圆括号——对不使用地区或城市代码的国家（地区），则省略该组件。</p>
	 * <p><b>电话号码：</b>这包含从 0 到 9 的一个或多个数字 </p>
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkPhone(String phone) { 
		String regex = "(\\+\\d+)?(\\d{3,4}\\-?)?\\d{7,8}$"; 
		return Pattern.matches(regex, phone); 
	}
	
	public static boolean chianese(String str) { 
		String regex = "[A-Za-z0-9]{1,16}|[\u4e00-\u9fa5]+"; 
		return Pattern.matches(regex, str); 
	}
 
	/**
	 * 
	 * desc:验证整数（正整数和负整数）
	 * <p>创建人：domain , 2018年5月21日 上午10:30:55</p>
	 * @param digit 一位或多位0-9之间的整数
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkDigit(String digit) { 
		String regex = "\\-?[1-9]\\d+"; 
		return Pattern.matches(regex,digit); 
	} 
 
	/**
	 * 
	 * desc:验证整数和浮点数（正负整数和正负浮点数）
	 * <p>创建人：domain , 2018年5月21日 上午10:31:24</p>
	 * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkDecimals(String decimals) { 
		String regex = "\\-?[1-9]\\d+(\\.\\d+)?"; 
		return Pattern.matches(regex,decimals); 
	}  
 
	/**
	 * 
	 * desc: 验证空白字符
	 * <p>创建人：domain , 2018年5月21日 上午10:32:00</p>
	 * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkBlankSpace(String blankSpace) { 
		String regex = "\\s+"; 
		return Pattern.matches(regex,blankSpace); 
	} 
 
	/**
	 * 
	 * desc:验证中文
	 * <p>创建人：domain , 2018年5月21日 上午10:32:24</p>
	 * @param chinese 中文字符
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkChinese(String chinese) { 
		String regex = "^[\u4E00-\u9FA5]+$"; 
		return Pattern.matches(regex,chinese); 
	} 
 
	/**
	 * 
	 * desc:验证日期（年月日）
	 * <p>创建人：domain , 2018年5月21日 上午10:32:46</p>
	 * @param birthday 日期，格式：1991-10-03，或1991.10.03
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkBirthday(String birthday) { 
		String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}"; 
		return Pattern.matches(regex,birthday); 
	} 
 
	/**
	 * 
	 * desc:验证URL地址
	 * <p>创建人：domain , 2018年5月21日 上午10:33:55</p>
	 * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkURL(String url) { 
		String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?"; 
		return Pattern.matches(regex, url); 
	} 
 
	/**
	 * desc:校验银行卡卡号
	 * <p>创建人：domain , 2018年8月14日 上午10:32:58</p>
	 * @param cardId
	 * @return
	 */
	public static boolean checkBankCard(String cardId) {
		char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
		if(bit == 'N'){
			return false;
		}
		return cardId.charAt(cardId.length() - 1) == bit;
	}
 
	/**
	 * desc:从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
	 * <p>创建人：domain , 2018年8月14日 上午10:45:13</p>
	 * @param nonCheckCodeCardId
	 * @return
	 */
	public static char getBankCardCheckCode(String nonCheckCodeCardId){
		if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0 || !nonCheckCodeCardId.matches("\\d+")) {
			//如果传的不是数据返回N
			return 'N';
		}
		char[] chs = nonCheckCodeCardId.trim().toCharArray();
		int luhmSum = 0;
		for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
			int k = chs[i] - '0';
			if(j % 2 == 0) {
				k *= 2;
				k = k / 10 + k % 10;
			}
			luhmSum += k; 
		}
		return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
	}
 
	/**
	 * <pre>
	 * 获取网址 URL 的一级域
	 * </pre>
	 * desc:
	 * <p>创建人：domain , 2018年5月21日 上午10:34:40</p>
	 * @param url
	 * @return
	 */
	public static String getDomain(String url) {
		Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
		// 获取完整的域名
		//        Pattern p = Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(url);
		matcher.find();
		return matcher.group();
	}
 
	/**
	 * 
	 * desc:匹配中国邮政编码
	 * <p>创建人：domain , 2018年5月21日 上午10:35:52</p>
	 * @param postcode 邮政编码
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkPostcode(String postcode) { 
		String regex = "[1-9]\\d{5}"; 
		return Pattern.matches(regex, postcode); 
	} 
 
	/**
	 * 
	 * desc:匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
	 * <p>创建人：domain , 2018年5月21日 上午10:36:22</p>
	 * @param ipAddress IPv4标准地址
	 * @return 验证成功返回true，验证失败返回false
	 */
	public static boolean checkIpAddress(String ipAddress) { 
		String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))"; 
		return Pattern.matches(regex, ipAddress); 
	}
 
	/** 
	 * 判断字段是否为空 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static synchronized boolean StrisNull(String str) {  
		return null == str || str.trim().length() <= 0 ? true : false ;  
	}  
	/** 
	 * 判断字段是非空 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean StrNotNull(String str) {  
		return !StrisNull(str) ;  
	}
	/** 
	 * 字符串null转空 
	 * @param str 
	 * @return boolean 
	 */
	public static  String nulltoStr(String str) {
		return StrisNull(str)?"":str;  
	}     
	/** 
	 * 字符串null赋值默认值  
	 * @param str    目标字符串 
	 * @param defaut 默认值 
	 * @return String 
	 */
	public static  String nulltoStr(String str,String defaut) {  
		return StrisNull(str)?defaut:str;  
	}  
	/** 
	 * 判断字段是否为Email 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isEmail(String str) {  
		return Regular(str,EMAIL);  
	}  
	/** 
	 * 判断是否为电话号码 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isPhone(String str) {  
		return Regular(str,PHONE);  
	}  
	/** 
	 * 判断是否为手机号码 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isMobile(String str) {  
		return Regular(str,MOBILE);  
	}  
	/** 
	 * 判断是否为Url 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isUrl(String str) {  
		return Regular(str,URL);  
	}     
	/**  
	 * 判断字段是否为数字 正负整数 正负浮点数 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isNumber(String str) {  
		return Regular(str,DOUBLE);  
	}  
	/** 
	 * 判断字段是否为INTEGER  符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isInteger(String str) {  
		return Regular(str,INTEGER);  
	}  
	/** 
	 * 判断字段是否为正整数正则表达式 >=0 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isINTEGER_NEGATIVE(String str) {  
		return Regular(str,INTEGER_NEGATIVE);  
	}  
	/** 
	 * 判断字段是否为负整数正则表达式 <=0 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isINTEGER_POSITIVE(String str) {  
		return Regular(str,INTEGER_POSITIVE);  
	}     
	/** 
	 * 判断字段是否为DOUBLE 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isDouble(String str) {  
		return Regular(str,DOUBLE);  
	}  
	/**  
	 * 判断字段是否为正浮点数正则表达式 >=0 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isDOUBLE_NEGATIVE(String str) {  
		return Regular(str,DOUBLE_NEGATIVE);  
	}  
	/** 
	 * 判断字段是否为负浮点数正则表达式 <=0 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isDOUBLE_POSITIVE(String str) {  
		return Regular(str,DOUBLE_POSITIVE);  
	}     
	/** 
	 * 判断字段是否为日期 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isDate(String str) {  
		return Regular(str,DATE_ALL);  
	}  
	/**
	 * 验证2010-12-10
	 * @param str
	 * @return
	 */
	public static  boolean isDate1(String str) {  
		return Regular(str,DATE_FORMAT1);  
	}  
	/** 
	 * 判断字段是否为年龄 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isAge(String str) {  
		return Regular(str,AGE) ;  
	}  
	/** 
	 * 判断字段是否超长 
	 * 字串为空返回fasle, 超过长度{leng}返回ture 反之返回false 
	 * @param str 
	 * @param leng 
	 * @return boolean 
	 */
	public static  boolean isLengOut(String str,int leng) {       
		return StrisNull(str)?false:str.trim().length() > leng ;  
	}  
	/** 
	 * 判断字段是否为身份证 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isIdCard(String str) {  
		if(StrisNull(str)) return false ;  
		if(str.trim().length() == 15 || str.trim().length() == 18) {  
			return Regular(str,IDCARD);  
		}else {  
			return false ;  
		}  
 
	}  
	/** 
	 * 判断字段是否为邮编 符合返回ture 
	 * @param str 
	 * @return boolean 
	 */
	public static  boolean isCode(String str) {  
		return Regular(str,CODE) ;  
	}  
	/** 
	 * 判断字符串是不是全部是英文字母 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isEnglish(String str) {  
		return Regular(str,STR_ENG) ;  
	}  
	/** 
	 * 判断字符串是不是全部是英文字母+数字 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isENG_NUM(String str) {  
		return Regular(str,STR_ENG_NUM) ;  
	}  
	/** 
	 * 判断字符串是不是全部是英文字母+数字+下划线 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isENG_NUM_(String str) {  
		return Regular(str,STR_ENG_NUM_) ;  
	}  
 
	/**
	 * 
	 * desc:过滤特殊字符串 返回过滤后的字符串 
	 * <p>创建人：domain , 2018年5月21日 上午11:43:34</p>
	 * @param str
	 * @return 过滤掉特殊字符之后的字符串
	 */
	public static  String filterStr(String str) {  
		Pattern p = Pattern.compile(STR_SPECIAL_);  
		Matcher m = p.matcher(str);  
		return m.replaceAll("").trim();  
	}
	
	/**
	 * 
	 * desc:过滤文件名中如../等特殊字符
	 *@author ruyiWang
	 * date:2018年6月7日 下午4:16:12
	 * @return 过滤掉特殊字符之后的文件名
	 */
	//[`~@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*+|{}【】‘；：]     [\\\\/:*?\"<>|]
	public static final String STR_SPECIAL_2="[`~!@#$%^&*()+=|{}':;',\\[\\].<>?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
	public static  String filterStr_2(String str) {  
		Pattern p = Pattern.compile(STR_SPECIAL_2);  
		//str = str.substring(str.lastIndexOf(".")); 
		//str = StringUtils.substringBeforeLast(str, '.') ;
		boolean status = str.contains(".");
		   if(status){
			   String suffixName = str.substring(str.lastIndexOf("."));
			   String beforeStr= str.substring(0,str.indexOf(suffixName));
			   Matcher m = p.matcher(beforeStr);
			   Matcher b = p.matcher(suffixName);
			   String regM = m.replaceAll("").trim();
			   String regB = b.replaceAll("").trim();
			   return regM +'.'+ regB;  
			  // return RegexUtils.filterStr_2(subStr)+fixName;
		   }else{
			   Matcher m = p.matcher(str);  
				return m.replaceAll("").trim();  
		   }
		/*Matcher m = p.matcher(str);  
		return m.replaceAll("").trim();  */
	}
 
	/**
	 * 校验机构代码格式
	 * @return
	 */
	public static boolean isJigouCode(String str){
		return Regular(str,JIGOU_CODE) ;  
	}
 
	/** 
	 * 判断字符串是不是数字组成 
	 * @param str 
	 * @return boolean 
	 */
	public static boolean isSTR_NUM(String str) {  
		return Regular(str,STR_NUM) ;
	} 
 
	/** 
	 * 匹配是否符合正则表达式pattern 匹配返回true 
	 * @param str 匹配的字符串 
	 * @param pattern 匹配模式 
	 * @return boolean 
	 */
	public static  boolean Regular(String str,String pattern){
		if(null == str || str.trim().length()<=0)  
			return false;           
		Pattern p = Pattern.compile(pattern);  
		Matcher m = p.matcher(str);  
		return m.matches();  
	}
	/**  
	 * 使用正则表达式来判断字符串中是否包含字母  
	 * @param str 待检验的字符串 
	 * @return 返回是否包含  
	 * true: 包含字母 ;false 不包含字母
	 */  
	public boolean judgeContainsStr(String str) {  
		String regex=".*[a-zA-Z]+.*";  
		Matcher m=Pattern.compile(regex).matcher(str);  
		return m.matches();  
	}
 
	/**
	 * \\b  表示 限定单词边界  比如  select 不通过   1select则是可以的
	 * desc:防御sql注入的字符串过滤（包括防止SQL从URL注入)
	 * <p>创建人：domain , 2018年5月22日 上午11:42:35</p>
	 * @param str
	 * @return 包含指定过滤字符 则返回true
	 */
	public static  Boolean sql_inject(String str) { 
		if(null == str || str.trim().length()<=0) return false;    
		Pattern p = Pattern.compile(SQL_INJECT_REG);  
		Matcher m = p.matcher(str);  
		return m.matches(); 
	}
	/**
	 * desc:防御sql注入的字符串过滤
	 * <p>创建人：domain , 2018年5月22日 上午11:56:01</p>
	 * @param str
	 * @return 返回true 参数不合法
	 */
	public static boolean sql_inj(String str){
		String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,|/*|*\\";
		String inj_stra[] = inj_str.split("\\|");
		for (int i=0 ; i < inj_stra.length ; i++ ){
			if (str.indexOf(inj_stra[i])>=0){
				return true;
			}
		}
		return false;
	}
	/**
	 * desc:对传入的查询参数字符串进行过滤
	 * <p>创建人：domain , 2018年5月22日 下午3:56:37</p>
	 * @param str
	 * @return
	 */
	public static boolean sqlInjValid(String str){ 
//		if(StringUtils.isNotEmpty(str) && str != "null"){
//			if (sqlPattern.matcher(str).find()){
//				logger.error("----参数值:"+str+"未能通过过滤器校验");
//				return false;
//			}
//		}
		return true;  
	}
	
	/**
	 * desc:测试主方法
	 * <p>创建人：domain , 2018年5月21日 上午10:20:51</p>
	 * @param args
	 */
	public static void main(String[] args) {
 
		boolean chianese = chianese("袁家博");
		System.out.println("汉字和字母数字校验结果:"+chianese);
		
		
		//String  matches（）方法。用规则匹配整个字符串，只要有一处不符合规则，就匹配结束，返回false。
 
		//String split()方法;       根据给定正则表达式的匹配拆分此字符串。返回一个数组
		String str = "avg   bb   geig   glsd   abc";
		String reg = " +";//按照多个空格来进行切割
		String[] arr = str.split(reg);
		for(String s : arr){
//			System.out.println(s);
		}
 
		//String replaceAll(regex,str)方法;  使用给定的 replacement 替换此字符串所有匹配给定的正则表达式的子字符串。ps：如果regex中有定义组，可以在第二参数中通过$符号获取正则表达式中的已有的组。
		String str1 = "wer1389980000ty1234564uiod234345675f";//将字符串中的数字替换成#。
		str1 = str1.replaceAll("\\d{5,}","#");
//		System.out.println(str1);
 
		//过滤特殊字符 ../
		String strr = "/AppData/XXZF/unionpay/guma_qrcode";
		String str2 = "../../../../../../../../../../../../../../etc/passwd";
		String str3 = "E:./../servers/confirmLitter/.././usr/mpsp/nfsmnt/mistake/upload";
//		str3 = str3.replace("../", "/");
		String str4 = "E:usr/mpsp/nfsmnt/mistake/upload.doc";
	/*	
		String filterStr_path;
		String str5;
		
		boolean status = str3.contains(".");
	   if(status){
		   String fixName = str3.substring(str3.lastIndexOf("."));
		   str5= str3.substring(0,str3.indexOf(fixName));
		   String filterStr_path2 = RegexUtils.filterStr_2(str5)+fixName;
		   System.out.println(filterStr_path2);
	   }else{
		   filterStr_path = RegexUtils.filterStr_2(str3);
		   System.out.println(filterStr_path);
	   }*/
		
	  /*String  filterStr_path = RegexUtils.filterStr_2(str4);
	   System.out.println(filterStr_path);*/
	   
		/*String fixName = str3.substring(str3.lastIndexOf("."));
		//System.out.println(fixName);
		if(StringUtil.isEmpty(fixName)){
			 filterStr_path = RegexUtils.filterStr_2(str3);
		}else{
			str5= str3.substring(0,str3.indexOf(fixName));
			System.out.println(str5);
			filterStr_path = RegexUtils.filterStr_2(str3)+fixName;
			String filterStr_path2 = RegexUtils.filterStr_2(str5)+fixName;
			System.out.println(filterStr_path2);
		}*/
		
	   String filterStr_path = RegexUtils.filterStr("/AppData/spEnterprise/static/uecp/files/doc_query");
		
		//sql注入正则测试
		String sql = "https://pos.umpay.com/spEnterprise/transact!trand0d1List.action";
		Boolean sql_inject = RegexUtils.sqlInjValid(null); //返回true 通过
		System.out.println(sql_inject);
		
		
		System.out.println(RegexUtils.isDate("20180514"));
	}
 
}
