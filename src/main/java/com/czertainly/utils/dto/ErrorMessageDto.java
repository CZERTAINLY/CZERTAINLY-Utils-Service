package com.czertainly.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessageDto {

    @Schema(
            description = "Specific error message",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Cannot parse field certificate"
    )
    private String error;

    @Schema(
            description = "Type of the error",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "InvalidFormatException"
    )
    private String type;

    @Schema(
            description = "Details about the error",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Invalid field parseType value"
    )
    private String detail;

    @Schema(
            description = "Optional stacktrace and other debug information when debug is enabled",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String stacktrace;

    public ErrorMessageDto() {
        super();
    }

    public ErrorMessageDto(String error, String type, String detail) {
        this.error = error;
        this.type = type;
        this.detail = detail;
    }

    public ErrorMessageDto(String error, String type, String detail, String stacktrace) {
        this.error = error;
        this.type = type;
        this.detail = detail;
        this.stacktrace = stacktrace;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public void setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
    }
}
