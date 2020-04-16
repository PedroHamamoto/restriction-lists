package br.com.hamamoto.infrastructure.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public enum DomainExceptionMessage {

    RESTRICTED_DOCUMENT_NOT_FOUND("Restricted document not found", HttpResponseStatus.NOT_FOUND, 404001),
    DOCUMENT_ALREADY_RESTRICTED("Document already restricted", HttpResponseStatus.CONFLICT, 409001);

    private String message;
    private HttpResponseStatus httpResponseStatus;
    private int code;

    DomainExceptionMessage(String message, HttpResponseStatus httpResponseStatus, int code) {
        this.message = message;
        this.httpResponseStatus = httpResponseStatus;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public HttpResponseStatus getHttpResponseStatus() {
        return httpResponseStatus;
    }

    public int getCode() {
        return code;
    }
}
