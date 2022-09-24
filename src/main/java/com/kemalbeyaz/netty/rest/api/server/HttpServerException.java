package com.kemalbeyaz.netty.rest.api.server;

import io.netty.handler.codec.http.HttpResponseStatus;

public class HttpServerException extends Exception {

    private final HttpResponseStatus status;
    private final String message;

    public HttpServerException(HttpResponseStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpResponseStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
