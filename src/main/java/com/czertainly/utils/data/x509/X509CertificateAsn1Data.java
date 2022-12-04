package com.czertainly.utils.data.x509;

import com.czertainly.utils.data.CertificateData;
import io.swagger.v3.oas.annotations.media.Schema;

public class X509CertificateAsn1Data extends CertificateData {

    @Schema(
            description = "ASN.1 dump the X.509 certificate",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private String asn1dump;

    public X509CertificateAsn1Data(String asn1dump) {
        super();
        this.asn1dump = asn1dump;
    }

    public String getAsn1dump() {
        return asn1dump;
    }

    public void setAsn1dump(String asn1dump) {
        this.asn1dump = asn1dump;
    }
}