package com.czertainly.utils.dto;

import com.czertainly.utils.enums.ParseType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ParseCertificateRequestDto {

    @NotEmpty(message = "Certificate must not be empty")
    byte[] certificate;

    @NotNull(message = "Parse type must not be null")
    ParseType parseType;

    public byte[] getCertificate() {
        return certificate;
    }

    public void setCertificate(byte[] certificate) {
        this.certificate = certificate;
    }

    public ParseType getParseType() {
        return parseType;
    }

    public void setParseType(ParseType parseType) {
        this.parseType = parseType;
    }
}
