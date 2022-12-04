package com.czertainly.utils.dto;

import com.czertainly.utils.data.CertificateData;
import com.czertainly.utils.data.x509.X509CertificateAsn1Data;
import com.czertainly.utils.data.x509.X509CertificateBasicData;
import com.czertainly.utils.data.x509.X509CertificateExtendedData;
import com.czertainly.utils.enums.CertificateType;
import io.swagger.v3.oas.annotations.media.Schema;

public class ParseCertificateResponseDto {

    @Schema(
            description = "Type of the certificate parsed",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    CertificateType type;

    @Schema(
            description = "Certificate data",
            requiredMode = Schema.RequiredMode.REQUIRED,
            anyOf = { X509CertificateBasicData.class, X509CertificateExtendedData.class, X509CertificateAsn1Data.class }
    )
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
