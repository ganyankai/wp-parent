package com.catt.common.util.bean;

import org.apache.commons.beanutils.converters.AbstractConverter;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 枚举类型转换
 *
 * @author wengsiwei
 */
public class EnumConverter extends AbstractConverter {

    @Override
    protected Object convertToType(Class type, Object value) throws Throwable {
        Object result;

        String strVal = value.toString().trim();

        //数字类型，则使用ordinal进行获取名称
        if (NumberUtils.isNumber(strVal)) {
            strVal = type.getDeclaredFields()[NumberUtils.toInt(strVal, 0)].getName();
        }

        result = Enum.valueOf(type, strVal);

        return result;
    }

    @Override
    protected Class getDefaultType() {
        return null;
    }
}
