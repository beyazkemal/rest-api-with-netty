package com.kemalbeyaz.netty.rest.api.server.handler;

import com.kemalbeyaz.netty.rest.api.operation.hello.HelloReq;
import com.kemalbeyaz.netty.rest.api.operation.hello.HelloRes;
import com.kemalbeyaz.netty.rest.api.server.HttpServerException;

public class HelloHandler implements Handler<HelloReq, HelloRes> {

    @Override
    public String getBasePath() {
        return "/";
    }

    @Override
    public boolean canHandle(String uri) {
        return uri.equals(getBasePath());
    }

    @Override
    public Class<HelloReq> getRequestClass() {
        return HelloReq.class;
    }

    @Override
    public Class<HelloRes> getResponseClass() {
        return HelloRes.class;
    }

    @Override
    public HelloReq createDefaultRequest() {
        return new HelloReq();
    }

    @Override
    public HelloRes handle(final HelloReq helloReq) throws HttpServerException {
        return new HelloRes("Hello!");
    }
}
