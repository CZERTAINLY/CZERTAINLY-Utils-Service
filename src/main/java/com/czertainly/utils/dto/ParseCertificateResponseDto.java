package com.czertainly.utils.dto;

import com.czertainly.utils.data.CertificateData;
import com.czertainly.utils.enums.CertificateType;

public class ParseCertificateResponseDto {

    CertificateType type;
    CertificateData data;

    public CertificateType getType() {
        return type;
    }

    public void setType(CertificateType type) {
        this.type = type;
    }

    public CertificateData getData() {
        return data;
    }

    public void setData(CertificateData data) {
        this.data = data;
    }
}
