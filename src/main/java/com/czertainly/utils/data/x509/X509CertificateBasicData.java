package com.czertainly.utils.data.x509;

import com.czertainly.utils.data.CertificateData;
import io.swagger.v3.oas.annotations.media.Schema;

public class X509CertificateBasicData extends CertificateData {

    @Schema(
            description = "Subject of the X.509 certificate",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "CN=test"
    )
    private String subject;

    @Schema(
            description = "Issuer of the X.509 certificate",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "CN=test"
    )
    private String issuer;

    @Schema(
            description = "notBefore in epoch / unix format",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1670146708406"
    )
    private long validFrom;

    @Schema(
            description = "notAfter in epoch / unix format",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1670146708406"
    )
    private long validTo;

    @Schema(
            description = "Serial number of the certificate in HEX",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "43ca8470bb21029542121d7ec2bb331815bd9871"
    )
    private String serialNumber;

    public X509CertificateBasicData(String subject, String issuer, long validFrom, long validTo, String serialNumber) {
        this.subject = subject;
        this.issuer = issuer;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.serialNumber = serialNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public long getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(long validFrom) {
        this.validFrom = validFrom;
    }

    public long getValidTo() {
        return validTo;
    }

    public void setValidTo(long validTo) {
        this.validTo = validTo;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
