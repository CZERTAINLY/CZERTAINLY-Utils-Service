package com.czertainly.utils.data.x509;

import com.czertainly.utils.data.CertificateData;

public class X509CertificateAsn1Data extends CertificateData {

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
