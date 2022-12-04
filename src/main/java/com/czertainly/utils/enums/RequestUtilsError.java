package com.czertainly.utils.enums;

public enum RequestUtilsError {
    // 300 codes
    REQUEST_PARSING_ERROR(301, "Error parsing certification request"),
    REQUEST_ASN1_DUMP_ERROR(302, "Error dumping ASN1 from certification request data"),
    REQUEST_TYPE_UNSUPPORTED(303, "Unsupported certification request type"),
    REQUEST_PARSE_TYPE_UNSUPPORTED(304, "Unsupported parse type for certification request"),
    REQUEST_INVALID_KEY(305, "Request contains invalid key"),
    REQUEST_INVALID_ALGORITHM(306, "Request contains invalid algorithm");

    private final int code;
    private final String error;

    private RequestUtilsError(int code, String error) {
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