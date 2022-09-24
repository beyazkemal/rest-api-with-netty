package com.kemalbeyaz.netty.rest.api.operation.hello;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kemalbeyaz.netty.rest.api.operation.OperationResponse;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.Date;
import java.util.Random;

public class HelloRes implements OperationResponse {

    private final String message;

    public HelloRes(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return "Kemal BEYAZ";
    }

    public int getNumber() {
        return new Random().nextInt();
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    public Date getDate() {
        return new Date();
    }

    @Override
    public HttpResponseStatus getStatus() {
        return HttpResponseStatus.OK;
    }
}
