package com.czertainly.utils.exception;

import com.czertainly.utils.enums.ApiError;
import org.springframework.http.HttpStatus;

public class CertificateParsingException extends Exception {

    private HttpStatus httpStatus;
    private int code;
    private String error;
    private String detail;

    public CertificateParsingException(HttpStatus httpStatus, ApiError apiError, Throwable cause) {
        super(apiError.getError(), cause);
        this.httpStatus = httpStatus;
        this.error = apiError.getError();
        this.code = apiError.getCode();
        this.detail = cause.getMessage();
    }

    public CertificateParsingException(HttpStatus httpStatus, ApiError apiError, String details) {
        super(apiError.getError());
        this.httpStatus = httpStatus;
        this.error = apiError.getError();
        this.code = apiError.getCode();
        this.detail = details;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
