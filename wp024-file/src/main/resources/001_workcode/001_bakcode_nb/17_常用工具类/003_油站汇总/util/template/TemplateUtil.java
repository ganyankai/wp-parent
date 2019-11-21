package com.catt.common.util.template;

import com.catt.common.util.template.beetl.EndSuffixMatcher;
import com.catt.common.util.template.beetl.ExtVarRef;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.exception.ScriptEvalError;
import org.beetl.core.resource.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * 模板工具类，支持字符串模板、文件模板，输出支持字符串、文件流
 * 模板类型划分：
 * 1. 文件模板
 *      划分为：
 *          1. classpath或maven项目的resouces下的模板，此类模板存储位置为：classpath 或 resources/template下
 *          2. webRoot或 maven项目的webapp下的模板（一般为html模板），此类模板存储位置为：webroot或webapp/template下
 * 2. 字符串模板
 * 注：使用前在当前项目的classpath下或resources下新建beetl.properties配置，
 * 具体可参考Test(V1.0\framework-parent\framework-base-util\src\test\java\com\catt\common\\util\template\TemplateUtilTest.java)程序的使用
 * @author: Zhang zhongtao
 * Version:
 * @since: Ver 1.1
 * Date: 2014-05-15 10:27
 * </pre>
 */
public class TemplateUtil {

    /**
     * html模板后缀
     */
    public static final String TPL_HTML = "html";
    /**
     * btl、ftl模板后缀
     */
    public static final String TPL_FILE = "btl,ftl";
    /**
     * 字符串模板前缀
     */
    public static final String TPL_STR = "str:";

    private static final String DEFAULT_CODE = "UTF-8";

    private static Logger logger = LoggerFactory.getLogger(com.catt.common.util.template.TemplateUtil.class);

    /**
     * 模板对象
     */
    private static GroupTemplate gt;

    // endregion
    // region 初始化模板引擎
    static {
        Configuration cfg = null;
        try {
            cfg = Configuration.defaultConfiguration();
            cfg.setCharset(DEFAULT_CODE);

            CompositeResourceLoader loader = new CompositeResourceLoader();

            //字符串模块
            StringTemplateResourceLoader resourceLoader = new StringTemplateResourceLoader();
            loader.addResourceLoader(new StartsWithMatcher(TPL_STR).withoutPrefix(), resourceLoader);

            //模板存放在classpath的template目录下
            String classPath = getClassPath();
            FileResourceLoader fileResourceLoader = new FileResourceLoader(classPath + File.separator + "template" + File.separator);
            loader.addResourceLoader(new EndSuffixMatcher(TPL_FILE), fileResourceLoader);

            //模板存放在WebRoot目录下，一般为Web项目
            WebAppResourceLoader webAppResourceLoader = new WebAppResourceLoader();
            webAppResourceLoader.setRoot(System.getProperty("webApp.root") + File.separator + "template" + File.separator);
            loader.addResourceLoader(new EndSuffixMatcher(TPL_HTML), webAppResourceLoader);

            gt = new GroupTemplate(loader, cfg);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("模板引擎初始化失败", e);
        }
    }

    private static String getClassPath() {
        String classPath = null;

        try {
            classPath = new PathMatchingResourcePatternResolver().getResources(".")[0].getFile().getPath();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("模板引擎获取模板路径异常", e);
        }

        return classPath;
    }

    /**
     * 字符串模板(只能传递字符串模板内容)转换为字符串
     *
     * @param template 字符串模板内容
     * @param params   模板参数
     * @return 字符串
     */
    public static String toStrForStrTemplate(String template, Object params) {
        return toString(TPL_STR + template, params);
    }

    /**
     * 字符串（需要添加TemplateUtil.TPL_STR前缀）、文件模板转换为字符串
     *
     * @param template 模板字符串（需要添加TemplateUtil.TPL_STR前缀）或模板文件路径
     * @param params   参数
     * @return 字符串
     */
    public static String toString(String template, Object params) {
        CallBack<String> callBack = t -> t.render();

        return toRead(template, params, callBack);
    }

    /**
     * 模板输出到文件
     *
     * @param template 模板文件，如：test.btl
     * @param stream   模板输出到文件
     * @param params   模板参数
     * @return OutputStream 文件
     */
    public static OutputStream toIO(String template, final OutputStream stream, Object params) {
        CallBack<OutputStream> callBack = t -> {
            t.renderTo(stream);
            return stream;
        };

        return toRead(template, params, callBack);
    }

    private static <T> T toRead(String template, Object params, CallBack<T> callBack) {

        Template t = gt.getTemplate(template);

        if (params != null) {
            if (params instanceof Map) {
                t.binding((Map) params);
            } else {
                t.binding(ExtVarRef.ROOT_FLAG, params);
            }
        }

        return callBack.read(t);
    }

    /**
     * 指定模板脚本
     *
     * @param template 模板字符串
     * @param params   模板参数
     * @return Map 执行结果
     */
    public static Map runScript(String template, Map params) {
        long start = System.currentTimeMillis();
        Map result = new HashMap();
        try {
            result = gt.runScript(template, params);
        } catch (ScriptEvalError scriptEvalError) {
            scriptEvalError.printStackTrace();
        }
        logger.info("模板脚本渲染耗时:" + (System.currentTimeMillis() - start) + "耗秒");

        return result;
    }

    /**
     * 回调类
     *
     * @param <T>
     */
    private interface CallBack<T> {
        /**
         * 渲染完成后回调方法
         *
         * @param template 模板对象
         * @return 模板渲染结果
         */
        T read(Template template);
    }

}
