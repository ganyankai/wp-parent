package com.catt.common.util.lang;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.lang3.ObjectUtils;

import java.io.*;

public class ObjectUtil extends ObjectUtils {
    /**
     * 根据xpath规范，从对象中提取值
     *
     * @param source 数据源
     * @param xpath  xpath表达式,如：results[1]/value/features，注意如果是数组，下标从1开始
     * @param <T>    返回类型
     * @return 找不到则为空，否则为指定的类型
     */
    public static <T> T getValByXpath(Object source, String xpath) {
        T t = null;
        try {
            JXPathContext context = JXPathContext.newContext(source);
            t = (T) context.getValue(xpath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 深度克隆对象
     *
     * @param source 克隆源对象
     * @param <T> 对象类型
     * @return T t 结果
     */
    public static <T> T deepClone(T source) {
        assert source instanceof Serializable : source.getClass().getName() + " not serializable!";

        T t = (T) deserialize(serialize(source));

        return t;
    }

    /***
     * java 对象 序列化为字节数组
     *
     * @param source java对象（确保对象实现了序列化接口）
     * @return byte[] 字节数组
     */
    public static byte[] serialize(Object source) {
        byte[] result = null;

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(source);
            result = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    /***
     * 字节数组反序列化为对象
     * @param bytes 对象序列化字节数组
     * @return Object 反序列化为对象
     */
    public static Object deserialize(byte[] bytes) {
        Object result = null;
        try (ByteArrayInputStream ibuf = new ByteArrayInputStream(bytes);
             ObjectInputStream is = new ObjectInputStream(ibuf)) {
            result = is.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
