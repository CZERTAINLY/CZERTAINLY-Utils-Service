package com.czertainly.utils.enums;

public enum RequestType {
    PKCS10(10, "pkcs10");

    private final int code;
    private final String type;

    private RequestType(int code, String type) {
        this.code = code;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code + ": " + type;
    }

}
