package com.catt.common.util.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.IOException;

/**
 * 针对超长整型转换为字符串，防止在前端js中失精
 *
 * @author Zhang zhongtao
 * @version 1.0.0.20180506
 * @since JDK V1.8
 */
@JacksonStdImpl
public class LongToStringSerializer extends ToStringSerializer {
    /**
     * JS最大整数
     */
    private static final double MAX_VAL = Math.pow(2, 53);
    /**
     * Singleton instance to use.
     */
    public final static com.catt.common.util.json.serializer.LongToStringSerializer instance = new com.catt.common.util.json.serializer.LongToStringSerializer();

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if ((Long) value > MAX_VAL) {
            gen.writeString(value.toString());
        } else {
            gen.writeNumber((Long) value);
        }
    }
}
