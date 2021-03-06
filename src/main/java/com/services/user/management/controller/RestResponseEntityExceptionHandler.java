package com.services.user.management.controller;

import com.services.user.management.exception.*;
import com.services.user.management.exception.ErrorDetail;
import com.services.user.management.exception.GenericErrorCode;
import com.services.user.management.exception.SpecificErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    private static final String DEFAULT = "DEFAULT";


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<SpecificErrorDetails> specificErrorDetails = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            ErrorCode errorCode = ErrorCode.findByValidationKey(getValidationKey(error));
            String specificErrorMessage = getMessage(error, errorCode);

            specificErrorDetails.add(new SpecificErrorDetails(errorCode, specificErrorMessage));
        });

        ErrorDetail errorDetail = new ErrorDetail(GenericErrorCode.InvalidRequest, specificErrorDetails);
        log.warn(errorDetail.toString(), ex);
        return new ResponseEntity(errorDetail, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private String getMessage(ObjectError error, ErrorCode errorCode) {
        return (StringUtils.isBlank(errorCode.getMessage()))
                ? error.getDefaultMessage() : errorCode.getMessage();
    }

    private String getValidationKey(ObjectError error) {
        return new StringBuilder(error.getCode()).append(".").append(error.getObjectName()).append(".")
                .append(((FieldError) error).getField()).toString();
    }
}