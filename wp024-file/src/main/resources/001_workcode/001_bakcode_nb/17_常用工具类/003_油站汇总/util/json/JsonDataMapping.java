package com.catt.common.util.json;

import com.catt.common.util.collections.CollectionUtil;
import com.catt.common.util.json.serializer.BigIntegerToStringSerializer;
import com.catt.common.util.json.serializer.LongToStringSerializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;

import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 处理返回json格式时，空串和日期的格式化
 */
public class JsonDataMapping extends ObjectMapper {

    /**
     *
     */
    private static final long serialVersionUID = 5605424237698352497L;

    /**
     * 是否针对hibernte的懒加载特性进行处理
     */
    private Boolean hibernateLazyHandler = false;

    private List<Module> registerModules;

    public List<Module> getRegisterModules() {
        return registerModules;
    }

    public void setRegisterModules(List<Module> registerModules) {
        this.registerModules = registerModules;
    }

    public Boolean getHibernateLazyHandler() {
        return hibernateLazyHandler;
    }

    public void setHibernateLazyHandler(Boolean hibernateLazyHandler) {
        this.hibernateLazyHandler = hibernateLazyHandler;
    }

    public JsonDataMapping() {
        this(false);
    }

    public JsonDataMapping(Boolean hibernateLazyHandler) {
        this(hibernateLazyHandler, null);
    }

    public JsonDataMapping(Boolean hibernateLazyHandler, List<Module> registerModules) {
        this.hibernateLazyHandler = hibernateLazyHandler;
        this.registerModules = registerModules;

        this.hibernateLazyHandler = hibernateLazyHandler;
        configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 枚举类型返回ordinal，而不是名称
        configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true);
        // 遇到未知属性不报错
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //空对象不要抛出异常
        configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
        //JsonParseException: Illegal unquoted character ((CTRL-CHAR, code 9))
        //"{\"subscribe\":1,"\"city\":\"g\u001D\u00963\",\"province\":\"S\u0017N¬\"}";
        configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

        setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        if (getHibernateLazyHandler()) {
            //针对hibernate实体中的懒加载特性进行设置，json序列化不会导致懒加载查询库表
            Hibernate5Module hibernate5Module = new Hibernate5Module(new ExtMapping());
            hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, false);
            //针对 @Transient 注解，不影响序列化
            hibernate5Module.configure(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION, false);
            hibernate5Module.configure(Hibernate5Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS, true);
            registerModule(hibernate5Module);
        }

        //Long类型转换为字符串
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, LongToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, LongToStringSerializer.instance);

        simpleModule.addSerializer(BigInteger.class, BigIntegerToStringSerializer.instance);
       // simpleModule.addSerializer(BigInteger.TYPE, LongToStringSerializer.instance);


        registerModule(simpleModule);

        if (CollectionUtil.isNotEmpty(registerModules)) {
            registerModules.forEach(this::registerModule);
        }

        this.getSerializerProvider().setNullValueSerializer(
                new JsonSerializer<Object>() {
                    @Override
                    public void serialize(Object value, JsonGenerator jg,
                                          SerializerProvider sp) throws IOException {
                        jg.writeString("");
                    }
                });
    }

}
