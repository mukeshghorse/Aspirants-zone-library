package com.library.payloads;

import org.springframework.http.HttpStatus;

import java.util.Map;

public class ApiResponseMessage {

    private HttpStatus status;
    private Integer code;
    private String message;
    private Object data;
    private Map<String, String> errorDetails;
    private PaginationMetadata paginationMetadata;  // Added pagination metadata

    public ApiResponseMessage() {
    }

    public ApiResponseMessage(HttpStatus status, Integer code, String message, Object data, Map<String, String> errorDetails, PaginationMetadata paginationMetadata) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
        this.errorDetails = errorDetails;
        this.paginationMetadata = paginationMetadata;  // Initialize pagination metadata
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, String> getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(Map<String, String> errorDetails) {
        this.errorDetails = errorDetails;
    }

    public PaginationMetadata getPaginationMetadata() {
        return paginationMetadata;
    }

    public void setPaginationMetadata(PaginationMetadata paginationMetadata) {
        this.paginationMetadata = paginationMetadata;
    }
}