package br.com.hamamoto.infrastructure.exception;

import io.netty.handler.codec.http.HttpResponseStatus;

public class DomainException extends RuntimeException {

    private int code;
    private HttpResponseStatus httpResponseStatus;

    public DomainException(DomainExceptionMessage domainExceptionMessage) {
        super(domainExceptionMessage.getMessage());

        this.code = domainExceptionMessage.getCode();
        this.httpResponseStatus = domainExceptionMessage.getHttpResponseStatus();
    }

    public int getCode() {
        return code;
    }

    public HttpResponseStatus getHttpResponseStatus() {
        return httpResponseStatus;
    }
}
