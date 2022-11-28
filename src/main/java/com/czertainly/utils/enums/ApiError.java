package com.czertainly.utils.enums;

public enum ApiError {
    // 100 codes
    CERTIFICATE_PARSING_ISSUE(101, "Error parsing certificate data"),
    CERTIFICATE_ASN1_DUMP_ERROR(102, "Error dumping ASN1 from certificate data");

    private final int code;
    private final String error;

    private ApiError(int code, String error) {
        this.code = code;
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + error;
    }
}