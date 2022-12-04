package com.czertainly.utils.enums;

public enum CertificateType {
    X509(10, "x509"),
    SSH(20, "ssh");

    private final int code;
    private final String type;

    private CertificateType(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    //@Override
    //public String toString() {
    //    return code + ": " + type;
    //}

}
