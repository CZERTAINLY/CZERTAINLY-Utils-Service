package com.czertainly.utils.data.x509;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Collection;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class X509CertificateExtendedData extends X509CertificateBasicData {

    private int version;
    private String signatureAlgorithm;
    private String algorithm;
    private byte[] publicKey;
    private Collection<List<?>> sans;

    public X509CertificateExtendedData(String subject, String issuer, long validFrom, long validTo, String serialNumber, int version, String signatureAlgorithm, String algorithm, byte[] publicKey, Collection<List<?>> sans) {
        super(subject, issuer, validFrom, validTo, serialNumber);
        this.version = version;
        this.signatureAlgorithm = signatureAlgorithm;
        this.algorithm = algorithm;
        this.publicKey = publicKey;
        this.sans = sans;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public Collection<List<?>> getSans() {
        return sans;
    }

    public void setSans(Collection<List<?>> sans) {
        this.sans = sans;
    }
}
