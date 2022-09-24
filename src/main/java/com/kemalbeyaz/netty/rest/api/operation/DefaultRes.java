package com.kemalbeyaz.netty.rest.api.operation;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;

public class DefaultRes implements OperationResponse {

    private final HttpResponseStatus status;
    private final HttpHeaders headers = new DefaultHttpHeaders();

    public DefaultRes(HttpResponseStatus status) {
        this.status = status;
        headers.clear();
    }

    public HttpHeaders getHeaders() {
        return headers;
    }

    @Override
    public HttpResponseStatus getStatus() {
        return status;
    }
}
