package com.catt.common.util.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.IOException;
import java.math.BigInteger;

/**
 * 针对超长整型转换为字符串，防止在前端js中失精
 *
 * @author Zhang zhongtao
 * @version 1.0.0.20180506
 * @since JDK V1.8
 */
@JacksonStdImpl
public class BigIntegerToStringSerializer extends ToStringSerializer {
    /**
     * JS最大整数
     */
    private static final BigInteger MAX_VAL = new BigInteger(((Long)Math.round(Math.pow(2, 53))).toString());
    /**
     * Singleton instance to use.
     */
    public final static com.catt.common.util.json.serializer.BigIntegerToStringSerializer instance = new com.catt.common.util.json.serializer.BigIntegerToStringSerializer();

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (((BigInteger) value).compareTo(MAX_VAL) > 0) {
            gen.writeString(value.toString());
        } else {
            gen.writeNumber(new BigInteger(value.toString()));
        }
    }
}
