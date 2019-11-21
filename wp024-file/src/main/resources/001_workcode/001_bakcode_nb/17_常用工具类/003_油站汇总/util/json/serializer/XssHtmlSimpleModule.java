package com.catt.common.util.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.IOException;

/**
 * <pre>
 * 对象中序列化为json字符串时，对象中的字符串类型值会进行xss html 转义，避免前端引发xss漏洞
 * @author : Zhang zhongtao
 * @version : Ver 1.0
 * </pre>
 */
public class XssHtmlSimpleModule extends SimpleModule {
    public XssHtmlSimpleModule() {
        this.addSerializer(new JsonHtmlXssSerializer(String.class));
    }

    /**
     * 字符串进行html4转义
     */
    class JsonHtmlXssSerializer extends JsonSerializer<String> {
        public JsonHtmlXssSerializer(Class<String> string) {
            super();
        }

        public Class<String> handledType() {
            return String.class;
        }

        public void serialize(String value, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException,
                JsonProcessingException {

            if (com.catt.common.util.lang.StringUtil.isNotBlank(value)) {
                value = StringEscapeUtils.escapeHtml4(value.toString());
            }

            jsonGenerator.writeString(value);
        }
    }
}
