package com.czertainly.utils;

import com.czertainly.utils.dto.ApiErrorResponseDto;
import com.czertainly.utils.dto.ErrorMessageDto;
import com.czertainly.utils.exception.CertificateUtilsException;
import com.czertainly.utils.exception.RequestUtilsException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlingAdvice {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlingAdvice.class);

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorMessageDto> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            ErrorMessageDto errorMessage = new ErrorMessageDto(error.getField(), ex.getClass().getSimpleName(), error.getDefaultMessage());
            if (log.isDebugEnabled()) {
                errorMessage.setStacktrace(ExceptionUtils.getStackTrace(ex));
            }
            errors.add(errorMessage);
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            ErrorMessageDto errorMessage = new ErrorMessageDto(error.getObjectName(), ex.getClass().getSimpleName(), error.getDefaultMessage());
            if (log.isDebugEnabled()) {
                errorMessage.setStacktrace(ExceptionUtils.getStackTrace(ex));
            }
            errors.add(errorMessage);
        }
        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(80, HttpStatus.BAD_REQUEST, "Arguments not valid", errors);
        return new ResponseEntity<>(
                apiErrorResponseDto, new HttpHeaders(), apiErrorResponseDto.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ErrorMessageDto errorMessage = new ErrorMessageDto(ex.getName(), ex.getClass().getSimpleName(), ex.getValue().toString());
        if (log.isDebugEnabled()) {
            errorMessage.setStacktrace(ExceptionUtils.getStackTrace(ex));
        }
        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(81, HttpStatus.BAD_REQUEST, "Arguments not valid", errorMessage);
        apiErrorResponseDto.setTimestamp(Instant.now().toEpochMilli());
        log.error(apiErrorResponseDto.getMessage() + ": " + ExceptionUtils.getStackTrace(ex));
        return new ResponseEntity<>(
                apiErrorResponseDto, new HttpHeaders(), apiErrorResponseDto.getStatus());
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class })
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorMessageDto errorMessage;
        final Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException) {
            InvalidFormatException exCause = (InvalidFormatException) ex.getCause();
            errorMessage = new ErrorMessageDto(exCause.getPath().get(0).getFieldName(), exCause.getClass().getSimpleName(), exCause.getValue().toString());
            if (log.isDebugEnabled()) {
                errorMessage.setStacktrace(ExceptionUtils.getStackTrace(exCause));
            }
        } else {
            errorMessage = new ErrorMessageDto(ex.getRootCause().getMessage(), ex.getClass().getSimpleName(), ex.getMessage());
            if (log.isDebugEnabled()) {
                errorMessage.setStacktrace(ExceptionUtils.getStackTrace(ex));
            }
        }
        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(81, HttpStatus.BAD_REQUEST, "Arguments not valid", errorMessage);
        apiErrorResponseDto.setTimestamp(Instant.now().toEpochMilli());
        log.error(apiErrorResponseDto.getMessage() + ": " + ExceptionUtils.getStackTrace(ex));
        return new ResponseEntity<>(
                apiErrorResponseDto, new HttpHeaders(), apiErrorResponseDto.getStatus());
    }

    @ExceptionHandler({ CertificateUtilsException.class })
    public ResponseEntity<Object> handleUtilsServiceException(CertificateUtilsException ex) {
        ErrorMessageDto errorMessage = new ErrorMessageDto(ex.getError(), ex.getClass().getSimpleName(), ex.getDetail());
        if (log.isDebugEnabled()) {
            errorMessage.setStacktrace(ExceptionUtils.getStackTrace(ex));
        }
        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(ex.getCode(), ex.getHttpStatus(), "There is an issue with certificate", errorMessage);
        apiErrorResponseDto.setTimestamp(Instant.now().toEpochMilli());
        log.error(apiErrorResponseDto.getMessage() + ": " + ExceptionUtils.getStackTrace(ex));
        return new ResponseEntity<>(
                apiErrorResponseDto, new HttpHeaders(), apiErrorResponseDto.getStatus());
    }

    @ExceptionHandler({ RequestUtilsException.class })
    public ResponseEntity<Object> handleUtilsServiceException(RequestUtilsException ex) {
        ErrorMessageDto errorMessage = new ErrorMessageDto(ex.getError(), ex.getClass().getSimpleName(), ex.getDetail());
        if (log.isDebugEnabled()) {
            errorMessage.setStacktrace(ExceptionUtils.getStackTrace(ex));
        }
        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(ex.getCode(), ex.getHttpStatus(), "There is an issue with certification request", errorMessage);
        apiErrorResponseDto.setTimestamp(Instant.now().toEpochMilli());
        log.error(apiErrorResponseDto.getMessage() + ": " + ExceptionUtils.getStackTrace(ex));
        return new ResponseEntity<>(
                apiErrorResponseDto, new HttpHeaders(), apiErrorResponseDto.getStatus());
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<Object> handleAll(Exception ex) {
        ErrorMessageDto errorMessage = new ErrorMessageDto("Unexpected error", ex.getClass().getSimpleName(), ex.getMessage());
        if (log.isDebugEnabled()) {
            errorMessage.setStacktrace(ExceptionUtils.getStackTrace(ex));
        }
        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(99, HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected exception occurred", errorMessage);
        apiErrorResponseDto.setTimestamp(Instant.now().toEpochMilli());
        log.error("Unexpected exception occurred: " + ex.getMessage());
        return new ResponseEntity<>(
                apiErrorResponseDto, new HttpHeaders(), apiErrorResponseDto.getStatus());
    }

}
