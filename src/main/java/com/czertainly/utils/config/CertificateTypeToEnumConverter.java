package com.czertainly.utils.config;

import com.czertainly.utils.enums.CertificateType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CertificateTypeToEnumConverter implements Converter<String, CertificateType> {

    @Override
    public CertificateType convert(String source) {
        return CertificateType.valueOf(source.toUpperCase());
    }

}
