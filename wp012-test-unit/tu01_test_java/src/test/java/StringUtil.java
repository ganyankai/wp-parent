import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtil: TODO请填写类描述
 */
public class StringUtil extends StringUtils {

    /**
     * 字符串首字母大写
     *
     * @param str
     * @return
     */
    public static String toUpperCaseFirst(String str) {
        if (str == null || "".equals(str.trim()))
            return "";
        String firstChar = str.substring(0, 1).toUpperCase();
        String lastStr = str.substring(1);
        return firstChar + lastStr;
    }

    /**
     * 字符串首字母小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirst(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 判断字符串不为空
     * @param str
     * @return
     */
    public static boolean checkStr(String str) {
        return isNotBlank(str);
    }

    /**
     * 判断对象不为空
     * @param obj
     * @return
     */
    public static boolean checkObj(Object obj) {
        boolean bool = true;
        if (obj == null || "".equals(obj.toString().trim()))
            bool = false;
        return bool;
    }

    /**
     * 对象转数值
     * String s = "20"; toInteger(s); // 20;
     * Man man = new Man();
     * man.toString(); // "15"
     * toInteger(man); // 15
     * @param obj
     * @return
     */
    public static Integer toInteger(Object obj) {
        return obj != null ? Integer.parseInt(obj.toString()) : 0;
    }

    /**
     * 字符串转数值，如果字符串为空，则返回-1；
     * String s = "";
     * toInt(s); // -1
     * @param str
     * @return
     */
    public static int toInt(String str) {
        return "".equals(str) ? -1 : Integer.parseInt(str);
    }

    /**
     * 返回对象不为空的toString方法
     * 使用场景？
     * @param obj
     * @return
     */
    public static String toString(Object obj) {
        return obj != null ? obj.toString().trim() : "";
    }

    /**
     * 字符串编码从ISO8859_1转成GBK
     * @param str
     * @return
     */
    public static String getISOToGBK(String str) {
        String strName = "";
        try {
            if (str != null) {
                strName = new String(str.getBytes("ISO8859_1"), "GBK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strName;
    }

    /**
     * 字符串编码从ISO8859_1转成UTF8
     * @param str
     * @return
     */
    public static String getISOToUTF8(String str) {
        String strName = "";
        try {
            if (str != null) {
                strName = new String(str.getBytes("ISO8859_1"), "UTF8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strName;
    }

    /**
     * 按给定格式解析字符串为时间对象
     * @param strDate
     * @param pattern
     * @return Date
     * @throws ParseException
     */
    public static Date parses(String strDate, String pattern)
            throws ParseException {
        return new SimpleDateFormat(pattern).parse(strDate);
    }

    /**
     * 根据参数获取随机值的整位数
     * @param num
     * @return str
     */
    public static String getRandom(int num) {
        return (Math.random() + "").substring(2, num + 2);
    }

    /**
     * 判断字符串是否中文
     * @param str
     * @return
     */
    public static boolean isChinese(String str) {
        String regEx = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static String getStrToGbk(String str) {
        String strName = "";
        try {
            if (str != null) {
                strName = new String(str.getBytes("UTF-8"), "GBK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strName;
    }

    /**
     * 字符串首字母大写
     * @param str
     * @return
     */
    public static String toFirstUpperCase(String str) {
        if (str == null || "".equals(str.trim()))
            return "";
        String firstChar = str.substring(0, 1).toUpperCase();
        String lastStr = str.substring(1);
        return firstChar + lastStr;
    }


    /**
     * 去除字符串数组中的重复值 add by tanzhouwen
     * @param stringArray
     * @return
     */
    public static String[] filterRepeat(String[] stringArray) {
        ArrayList arrayList = new ArrayList();
        for (String str : stringArray) {
            if (!arrayList.contains(str)) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[] {});
    }


    /**
     * 获取字符串的中文个数
     * @author wengsiwei
     * @return
     */
    public static int getChineseCount(String str) {
        return str.getBytes().length - str.length();
    }

    /**
     * 截取带中文字符串的方法(中文占2个字符)
     * @author wengsiwei
     * @param str-字符串
     * @param pstart-截取起始位置
     * @param pend-截取长度
     * @return
     */
    public static String getSubString(String str, int pstart, int pend) {
        String resu = "";
        int beg = 0;
        int end = 0;
        int count1 = 0;
        char[] temp = new char[str.length()];
        str.getChars(0, str.length(), temp, 0);
        boolean[] bol = new boolean[str.length()];
        for (int i = 0; i < temp.length; i++) {
            bol[i] = false;
            if ((int) temp[i] > 255) {// 说明是中文
                count1++;
                bol[i] = true;
            }
        }

        if (pstart > str.length() + count1) {
            resu = null;
        }
        if (pstart > pend) {
            resu = null;
        }
        if (pstart < 1) {
            beg = 0;
        } else {
            beg = pstart - 1;
        }
        if (pend > str.length() + count1) {
            end = str.length() + count1;
        } else {
            end = pend;// 在substring的末尾一样
        }
        // 下面开始求应该返回的字符串
        if (resu != null) {
            if (beg == end) {
                int count = 0;
                if (beg == 0) {
                    if (bol[0] == true)
                        resu = null;
                    else
                        resu = new String(temp, 0, 1);
                } else {
                    int len = beg;// zheli
                    for (int y = 0; y < len; y++) {// 表示他前面是否有中文,不管自己
                        if (bol[y] == true)
                            count++;
                        len--;// 想明白为什么len--
                    }
                    // for循环运行完毕后，len的值就代表在正常字符串中，目标beg的上一字符的索引值
                    if (count == 0) {// 说明前面没有中文
                        if ((int) temp[beg] > 255)// 说明自己是中文
                            resu = null;// 返回空
                        else
                            resu = new String(temp, beg, 1);
                    } else {// 前面有中文，那么一个中文应与2个字符相对
                        if ((int) temp[len + 1] > 255)// 说明自己是中文
                            resu = null;// 返回空
                        else
                            resu = new String(temp, len + 1, 1);
                    }
                }
            } else {// 下面是正常情况下的比较
                int temSt = beg;
                int temEd = end - 1;// 这里减掉一
                for (int i = 0; i < temSt; i++) {
                    if (bol[i] == true)
                        temSt--;
                }// 循环完毕后temSt表示前字符的正常索引
                for (int j = 0; j < temEd; j++) {
                    if (bol[j] == true)
                        temEd--;
                }// 循环完毕后temEd-1表示最后字符的正常索引
                if (bol[temSt] == true)// 说明是字符，说明索引本身是汉字的后半部分，那么应该是不能取的
                {
                    int cont = 0;
                    for (int i = 0; i <= temSt; i++) {
                        cont++;
                        if (bol[i] == true)
                            cont++;
                    }
                    if (pstart == cont)// 是偶数不应包含,如果pstart<cont则要包含
                        temSt++;// 从下一位开始
                }
                if (bol[temEd] == true) {// 因为temEd表示substring
                    // 的最面参数，此处是一个汉字，下面要确定是否应该含这个汉字
                    int cont = 0;
                    for (int i = 0; i <= temEd; i++) {
                        cont++;
                        if (bol[i] == true)
                            cont++;
                    }
                    if (pend < cont)// 是汉字的前半部分不应包含
                        temEd--;// 所以只取到前一个
                }
                if (temSt == temEd) {
                    resu = new String(temp, temSt, 1);
                } else if (temSt > temEd) {
                    resu = null;
                } else {
                    resu = str.substring(temSt, temEd + 1);
                }
            }
        }
        return resu;// 返回结果
    }

    /**
     * 判断请求IP是否包含在ip数组中
     * @param requestIP
     * @param ips
     * @return
     */
    public static boolean checkIP(String requestIP, String[] ips) {
        for (String ip : ips) {
            if (requestIP.equals(ip)) return true;
            if ((ip.indexOf("-")) >= 0) {//x.x.x.x-x.x.x.x
                String[] ipPart = requestIP.split("\\.");
                String[] ipOpenPart = ip.split("\\-")[0].split("\\.");
                String[] ipEndPart = ip.split("\\-")[1].split("\\.");
                if (ipOpenPart.length != ipPart.length || ipEndPart.length != ipPart.length) continue;
                boolean flag = true;
                for (int i = 0; i < ipPart.length; i++) {
                    if (ipPart[i].compareTo(ipOpenPart[i]) < 0 || ipPart[i].compareTo(ipEndPart[i]) > 0) {
                        flag = false;
                    }
                }
                if (flag) return true;
            }
        }
        return false;
    }

    // 获得请求客户端的IP
    public static String getIpAddr(HttpServletRequest request) {
    	final String UNKNOWN = "unknown";
    	
    	String ip = request.getHeader("x-forwarded-for");

        if (StringUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (StringUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (StringUtil.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if (StringUtil.isNotBlank(ip)
                && (ip.contains("127.0.0.1") || ip.contains("0:0:0:0:0:0:0:1") || ip.contains("localhost"))) {
            String localIp = StringUtil.getIPAddress();
            if(StringUtil.isNotBlank(localIp)){
                ip = localIp;
            }
        }

        return ip;
    }

    /**
     * 效验字符串是否符合正则表达式格式
     * @param value
     * @param regexp
     * @return
     */
    public static boolean checkRegexp(String value, String regexp) {
        return Pattern.compile(regexp).matcher(value).matches();
    }

    /**
     * 获取文件名称
     * @param path
     * @return
     */
    public static String getBaseName(String path) {
        int pos = path.lastIndexOf("\\");
        if (!System.getProperty("os.name").contains("Win")) {
            pos = path.lastIndexOf("/");
        }
        return path.substring(pos + 1);
    }

    /**
     * 对单引号、百分号、左中括号、右中括号等执行替换处理
     * @param s
     * @return
     */
    public static String doLikeEscape(String s) {
        if (StringUtil.checkStr(s)) {
            StringBuffer result = new StringBuffer();
            char ch;
            for (int i = 0; i < s.length(); i++) {
                ch = s.charAt(i);
                if (ch == '\'') {
                    result.append("''");
                    continue;
                }
                if (ch == '%') {
                    result.append("[%]");
                    continue;
                }
                if (ch == '[') {
                    result.append("[[]");
                    continue;
                }
                if (ch == ']') {
                    result.append("[]]");
                    continue;
                }
                result.append(ch);
            }
            return result.toString();
        }

        return s;
    }

    /**
     * 获取IP地址
     * @return IP地址
     */
    public static String getIPAddress(){
        String ip = null;
        try {
            String os = System.getProperty("os.name");
            ip = os.startsWith("Windows") ? getIPOnWindows() : getIPOnLinux();
        } catch (Exception e) {
        }

        return ip;
    }

    /**
     * Windows下获取本机IP地址
     * @return IP地址
     */
    private static String getIPOnWindows() throws Exception{
        String ip = "";
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("ipconfig");
            bufferedReader = new BufferedReader(new InputStreamReader(process
                    .getInputStream()));
            String line = null;
            int index = -1;
            boolean isLocal = false;
            while ((line = bufferedReader.readLine()) != null) {
                //找到本地连接信息
                if(line.toLowerCase().indexOf("ethernet adapter 本地连接:") >= 0 ||
                        line.indexOf("以太网适配器 本地连接:") >=0 ||
                        line.indexOf("以太网适配器 以太网:") >=0){
                    isLocal = true;
                }

                //找到IP信息
                if(line.toLowerCase().indexOf("ipv4") >= 0 ||
                        line.toLowerCase().indexOf("ip address") >= 0){
                    if(isLocal){
                        index = line.indexOf(":");
                        ip = line.substring(index + 1).trim();
                        break;
                    }
                }
            }
            //没有取到本地连接中的IP信息，则通过Java API来获取
            if(!StringUtil.checkStr(ip)){
                InetAddress inet = InetAddress.getLocalHost();
                ip = inet.getHostAddress();
            }
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e1) {
            }
            bufferedReader = null;
            process = null;
        }

        return ip;
    }

    /**
     * Linux下获取本机IP地址
     * @return IP地址
     */
    private static String getIPOnLinux() throws Exception{
        String ip = "";
        try {
            Enumeration<?> e1 = (Enumeration<?>) NetworkInterface
                    .getNetworkInterfaces();
            while (e1.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) e1.nextElement();
                if (!ni.getName().equals("eth0")) {
                    continue;
                } else {
                    Enumeration<?> e2 = ni.getInetAddresses();
                    while (e2.hasMoreElements()) {
                        InetAddress ia = (InetAddress) e2.nextElement();
                        if (ia instanceof Inet6Address)
                            continue;
                        ip = ia.getHostAddress();
                    }
                    break;
                }
            }
        } catch (Exception e) {
        }

        return ip;
    }

    /**
     * 判断字符串不为空
     * @param str
     * @return
     */
    public static BigDecimal toBigDecimal(String str) {
        BigDecimal result = new BigDecimal(0);
        if (checkObj(str)&&!str.equals("null")) {
            result = new BigDecimal(str);
        }

        return result;
    }
}