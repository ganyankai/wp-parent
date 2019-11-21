package com.catt.common.util.json;

import com.catt.common.util.spring.SpringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Json工具类
 */
public final class JsonUtils {

    private static ObjectMapper objMapper = null;

    /**
     * 不可实例化
     */
    private JsonUtils() {
    }

    /**
     * 对象转json字符串
     *
     * @param value
     * @return
     */
    public static String toJson(Object value) {
        try {
            return getObjMapper().writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json串转对象
     *
     * @param json      json串
     * @param valueType 对象类型
     * @return
     */
    public static <T> T toObject(String json, Class<T> valueType) {
        Assert.hasText(json);
        Assert.notNull(valueType);

        try {
            return getObjMapper().readValue(json, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json串转对象
     *
     * @param json          json串
     * @param typeReference 对象类型
     * @return
     */
    public static <T> T toObject(String json, TypeReference<?> typeReference) {
        Assert.hasText(json);
        Assert.notNull(typeReference);

        try {
            return getObjMapper().readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json串转对象
     *
     * @param json     json串
     * @param javaType 对象类型
     * @return
     */
    public static <T> T toObject(String json, JavaType javaType) {
        Assert.hasText(json);
        Assert.notNull(javaType);

        try {
            return getObjMapper().readValue(json, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * json串转具有指定泛型的List对象
     *
     * @param json      json串
     * @param valueType 对象类型
     * @return
     */
    public static List toList(String json, Class<?> valueType) {
        Assert.hasText(json);
        Assert.notNull(valueType);

        JavaType javaType = getObjMapper().getTypeFactory().constructParametricType(
                ArrayList.class, valueType);
        try {
            return (List) getObjMapper().readValue(json, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将对象转换为json流
     *
     * @param writer writer
     * @param value  对象
     */
    public static void writeValue(Writer writer, Object value) {
        try {
            getObjMapper().writeValue(writer, value);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ObjectMapper getObjMapper() {

        if (objMapper == null) {
            try {
                objMapper = SpringUtils.getBean("jsonDataMapper");
            } catch (Exception e) {
//                e.printStackTrace();
            }
        }

        if (objMapper == null) {
            objMapper = new com.catt.common.util.json.JsonDataMapping();
        }

        return objMapper;
    }
}
