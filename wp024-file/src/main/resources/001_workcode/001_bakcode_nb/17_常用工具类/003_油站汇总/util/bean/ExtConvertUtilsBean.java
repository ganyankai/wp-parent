package com.catt.common.util.bean;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;

/**
 * 覆盖类型查找方法，使之支持枚举
 *
 * @author Zhang zhongtao
 * @version Ver 1.0
 * @since JDK V1.8
 */
public class ExtConvertUtilsBean extends ConvertUtilsBean {
    @Override
    public Converter lookup(Class clazz) {
        Converter converter = super.lookup(clazz);

        if (converter == null && clazz.isEnum()) {
            converter = super.lookup(Enum.class);
        }

        return converter;
    }

}
