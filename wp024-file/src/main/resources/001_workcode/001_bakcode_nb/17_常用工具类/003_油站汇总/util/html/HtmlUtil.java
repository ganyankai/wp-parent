package com.catt.common.util.html;


import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Zhang zhongtao
 * @version Ver 1.1
 * @since 2014-10-28 19:30
 */
public class HtmlUtil {
    /**
     * 正则表达式，匹配a href里面的内容
     */
    private static final Pattern PATTERN_HREF = Pattern.compile("(?si)<a\\s+href\\s?=\\s?(\'|\")(.*)(\'|\")>");

    /**
     * 获取href标签中的内容
     * 如：<a href='http://www.baidu.com'>334343</a> 获取出：http://www.baidu.com
     *
     * @param aHref <a href='http://www.baidu.com'>334343</a>
     * @return
     */
    public static String getHrefContent(String aHref) {
        String url = "";

        Matcher m = PATTERN_HREF.matcher(aHref);

        while (m.find()) {
            url = m.group(2);
        }

        return url;
    }

    /**
     * url中追加参数
     *
     * @param url       被追加的url
     * @param urlParams url参数
     * @return 追加后的url
     */
    public static String appendUrlParam(String url, String urlParams) {
        return url + (url.indexOf('?') == -1 ? "?" + urlParams : "&" + urlParams);
    }

    /**
     * 获取客户端浏览器信息
     *
     * @param req
     * @return
     */
    public static String getBrowserType(ServletRequest req) {
        String sBrowserType = com.catt.common.util.lang.StringUtil.toString(req.getAttribute("sBrowserType"));
        if (com.catt.common.util.lang.StringUtil.isNotBlank(sBrowserType)) {
            return sBrowserType;
        }
        return ((HttpServletRequest) req).getHeader("User-Agent");
    }

    public static String filterHtml(String s){
        if(!s.equals("")||s!=null){
            String str=s.replaceAll("<[.[^<]]*>","");
            return str;
        }else{
            return s;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        String s="<p>我是html标签</p><a></a><li></li>";
        System.out.println(com.catt.common.util.html.HtmlUtil.filterHtml(s));
    }
}
