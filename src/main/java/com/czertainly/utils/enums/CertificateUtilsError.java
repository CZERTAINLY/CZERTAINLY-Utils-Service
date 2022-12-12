package com.czertainly.utils.enums;

public enum CertificateUtilsError {
    // 100 codes
    CERTIFICATE_PARSING_ERROR(101, "Error parsing certificate data"),
    CERTIFICATE_ASN1_DUMP_ERROR(102, "Error dumping ASN1 from certificate data"),
    CERTIFICATE_TYPE_UNSUPPORTED(303, "Unsupported certificate type"),
    CERTIFICATE_PARSE_TYPE_UNSUPPORTED(304, "Unsupported parse type for certificate"),
    CERTIFICATE_RANDOM_KEYPAIR_ALG_UNSUPPORTED(501, "Unsupported algorithm to generate random keys"),
    CERTIFICATE_RANDOM_GEN_ERROR(502, "Error when generating random certificate"),
    CERTIFICATE_IDENTIFY_UNKNOWN(701, "Unable to identify certificate type");

    private final int code;
    private final String error;

    private CertificateUtilsError(int code, String error) {
        this.code = code;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public int getCode() {
        return code;
    }

    //@Override
    //public String toString() {
    //    return code + ": " + error;
    //}
}