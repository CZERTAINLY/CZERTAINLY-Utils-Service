package com.czertainly.utils.data.x509;

import java.util.Collection;
import java.util.List;

public class X509CertificateExtendedData extends X509CertificateBasicData {

    private Collection<List<?>> sans;

    public X509CertificateExtendedData(String subject, String issuer, long validFrom, long validTo, String serialNumber,
                                       Collection<List<?>> sans) {
        super(subject, issuer, validFrom, validTo, serialNumber);
        this.sans = sans;
    }

    public Collection<List<?>> getSans() {
        return sans;
    }

    public void setSans(Collection<List<?>> sans) {
        this.sans = sans;
    }
}
