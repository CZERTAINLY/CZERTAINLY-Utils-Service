package com.czertainly.utils.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorResponseDto {

    /**
     * Timestamp in epoch / unix format
     */
    @Schema(
            description = "Timestamp of the error in epoch / unix format",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "1670146708406"
    )
    private long timestamp;

    @Schema(
            description = "Error code",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "81"
    )
    private int code;

    @Schema(
            description = "Http status code",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "400"
    )
    private HttpStatus status;

    @Schema(
            description = "Error message",
            requiredMode = Schema.RequiredMode.REQUIRED,
            example = "Arguments not valid"
    )
    private String message;

    @Schema(
            description = "Detailed list of errors",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    private List<ErrorMessageDto> errors;

    public ApiErrorResponseDto() {
        super();
    }

    public ApiErrorResponseDto(final int code, final HttpStatus status, final String message, final List<ErrorMessageDto> errors) {
        super();
        this.code = code;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiErrorResponseDto(final int code, final HttpStatus status, final String message, final ErrorMessageDto error) {
        super();
        this.code = code;
        this.status = status;
        this.message = message;
        this.errors = Collections.singletonList(error);
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorMessageDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorMessageDto> errors) {
        this.errors = errors;
    }
}