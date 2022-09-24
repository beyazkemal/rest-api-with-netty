package com.kemalbeyaz.netty.rest.api.server.handler;

import com.kemalbeyaz.netty.rest.api.operation.DefaultReq;
import com.kemalbeyaz.netty.rest.api.operation.DefaultRes;
import com.kemalbeyaz.netty.rest.api.server.HttpServerException;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;

public class MovedTodoListHandler implements Handler<DefaultReq, DefaultRes> {

    @Override
    public String getBasePath() {
        return "/todo/list";
    }

    @Override
    public boolean canHandle(String uri) {
        return uri.equals(getBasePath());
    }

    @Override
    public Class<DefaultReq> getRequestClass() {
        return DefaultReq.class;
    }

    @Override
    public Class<DefaultRes> getResponseClass() {
        return DefaultRes.class;
    }

    @Override
    public DefaultReq createDefaultRequest() {
        return new DefaultReq();
    }

    @Override
    public DefaultRes handle(DefaultReq operationRequest) throws HttpServerException {
        var defaultRes = new DefaultRes(HttpResponseStatus.MOVED_PERMANENTLY);
        defaultRes.getHeaders().set(HttpHeaderNames.LOCATION, "/todo");
        return defaultRes;
    }
}
