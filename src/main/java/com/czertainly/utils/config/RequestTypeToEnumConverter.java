package com.czertainly.utils.config;

import com.czertainly.utils.enums.RequestType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RequestTypeToEnumConverter implements Converter<String, RequestType> {

    @Override
    public RequestType convert(String source) {
        return RequestType.valueOf(source.toUpperCase());
    }

}
