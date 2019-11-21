package com.catt.common.util.json;

import org.hibernate.MappingException;
import org.hibernate.id.factory.IdentifierGeneratorFactory;
import org.hibernate.type.Type;

/**
 * Created by Zhang zhongtao on 2015/7/13.
 */
public class ExtMapping implements org.hibernate.engine.spi.Mapping {
    @Override
    public IdentifierGeneratorFactory getIdentifierGeneratorFactory() {
        return null;
    }

    @Override
    public Type getIdentifierType(String className) throws MappingException {
        return null;
    }

    @Override
    public String getIdentifierPropertyName(String className) throws MappingException {
        return "id";
    }

    @Override
    public Type getReferencedPropertyType(String className, String propertyName) throws MappingException {
        return null;
    }
}
