package com.czertainly.utils.dto;

import com.czertainly.utils.enums.CertificateParseType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ParseCertificateRequestDto {

    @NotEmpty(message = "Certificate must not be empty")
    byte[] certificate;

    @NotNull(message = "Parse type must not be null")
    CertificateParseType parseType;

    public byte[] getCertificate() {
        return certificate;
    }

    public void setCertificate(byte[] certificate) {
        this.certificate = certificate;
    }

    public CertificateParseType getParseType() {
        return parseType;
    }

    public void setParseType(CertificateParseType parseType) {
        this.parseType = parseType;
    }
}
