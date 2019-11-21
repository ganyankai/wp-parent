package com.catt.common.util.collections;

import org.apache.commons.collections.MapUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Map工具类
 * 
 */
public class MapUtil extends MapUtils{
    /**
     * 级联获取嵌套map的值
     *
     * @param map
     *            嵌套的map对象
     * @param keyPath
     *            键路径，以.分隔
     * @return 键路径指向的值，没有则返回null
     */
    @SuppressWarnings("unchecked")
    public static Object getCascadeValue(Map<String, Object> map, String keyPath) {
        Object value = null;

        if (map != null && keyPath != null) {
            String[] keyList = keyPath.split("\\.");

            for (int i = 0; i < keyList.length; i++) {
                if (map == null) {
                    break;
                }

                String key = keyList[i];
                value = map.get(key);

                if (value instanceof Map) {
                    map = (Map<String, Object>) value;
                } else {
                    map = null;
                }
            }
        }

        return value;
    }

    /**
     * 获取map的值为List对象
     *
     * @param map
     *            map对象
     * @param key
     *            键
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<Object> getValueAsList(Map<String, Object> map,
                                              String key) {
        if (map == null) {
            return null;
        }

        List<Object> result = new ArrayList<Object>();

        if (map.containsKey(key)) {
            Object value = map.get(key);
            if (value instanceof List) {
                result = (List<Object>) value;
            } else {
                result.add(value);
            }
        }

        return result;
    }

    /**
     * 将一个 Map 对象转化为一个 JavaBean
     * @param type 要转化的类型
     * @param map 包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @throws IntrospectionException
     *             如果分析类属性失败
     * @throws IllegalAccessException
     *             如果实例化 JavaBean 失败
     * @throws InstantiationException
     *             如果实例化 JavaBean 失败
     * @throws InvocationTargetException
     *             如果调用属性的 setter 方法失败
     */
    public static Object convertMap(Class type, Map map)
            throws IntrospectionException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
        Object obj = type.newInstance(); // 创建 JavaBean 对象

        // 给 JavaBean 对象的属性赋值
        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(obj, args);
            }
        }
        return obj;
    }

    /**
     * 将一个 JavaBean 对象转化为一个  Map
     * @param bean 要转化的JavaBean 对象
     * @return 转化出来的  Map 对象
     * @throws IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
     */
    public static Map convertBean(Object bean)
            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();
        for (int i = 0; i< propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, com.catt.common.util.lang.StringUtil.toString(result));
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    /**
     * 从
     * @param map
     * @param key
     * @param defaultValue
     * @param clazz
     * @return
     */
    public static Object getObject(Map map, Object key, Object defaultValue,Class clazz){

        if(clazz == Double.class){
            return getDouble(map,key,(Double)defaultValue);
        }else  if(clazz == Integer.class){
            return getInteger(map, key, (Integer) defaultValue);
        }else  if(clazz == Long.class){
            return getLong(map, key, (Long) defaultValue);
        }else  if(clazz == Float.class){
            return getFloat(map, key, (Float) defaultValue);
        }else  if(clazz == Short.class){
            return getShort(map, key, (Short) defaultValue);
        }else  if(clazz == String.class){
            return getString(map, key, (String)defaultValue);
        }else  if(clazz == Byte.class){
            return getByte(map, key, (Byte) defaultValue);
        }else if(clazz == Map.class){
            return getMap(map, key, (Map) defaultValue);
        }else if(clazz == Boolean.class){
            return getBoolean(map, key, (Boolean) defaultValue);
        }else if(clazz == Number.class){
            return getNumber(map, key, (Number) defaultValue);
        }

        return getObject(map,key,defaultValue);
    }

    /**
     * 从Map中获取指定参数，返回仅包含指定key的Map <br>
     *
     * @param paramMap
     *            参数对象
     * @param properties
     *            包含要获取Key的Map(使用该Map作为返回对象)
     * @return
     */
    public static Map getDefineAttr(Map paramMap, Map properties) {
        Set keySet = properties.keySet();
        for (Object key : keySet) {
            properties.put(key, paramMap.get(key));
        }
        return properties;
    }

    /**
     * 从Map中获取指定参数，返回仅包含指定key的Map <br>
     *
     * @param paramMap
     *            参数对象
     * @param properties
     *            包含要获取Key的数组
     * @return
     */
    public static Map getDefineAttr(Map paramMap, String[] properties) {
        Class clazz = paramMap.getClass();

        Object o;
        try {
            o = clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Map m = (Map)o;

        for (String key : properties) {
            m.put(key, paramMap.get(key));
        }
        return m;
    }

    /**
     * 从Map中获取指定参数，返回仅包含指定key的Map <br>
     *
     * @param listData
     *            参数对象
     * @param properties
     *            包含要获取Key的数组
     * @return
     */
    public static List<Map> getDefineAttr(List<Map> listData, String[] properties) {
        Class listClazz = listData.getClass();

        Object list;
        try {
            list = listClazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        List l = (List)list;
        for (Map paramMap : listData) {
            Class mapClazz = paramMap.getClass();

            Object map;
            try {
                map = mapClazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Map m = (Map)map;

            for (String key : properties) {
                m.put(key, paramMap.get(key));
            }
            l.add(m) ;
        }
        return l;
    }

    /**
     * 判断来自Request的参数集合中对应key值是否为true
     * @param paramMap 参数集合
     * @param paramName key
     * @return 是否为true
     */
    public static boolean isTrueFromRequestParam (Map paramMap, String paramName) {
        String value = com.catt.common.util.collections.MapUtil.getString(paramMap, paramName);
        return value != null &&
                (value.equalsIgnoreCase("true") ||
                        value.equalsIgnoreCase("t") ||
                        value.equalsIgnoreCase("1") ||
                        value.equalsIgnoreCase("enabled") ||
                        value.equalsIgnoreCase("y") ||
                        value.equalsIgnoreCase("yes") ||
                        value.equalsIgnoreCase("on"));
    }

}
