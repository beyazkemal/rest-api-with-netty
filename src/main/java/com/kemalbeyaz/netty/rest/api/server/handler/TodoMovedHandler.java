package com.kemalbeyaz.netty.rest.api.server.handler;

import com.kemalbeyaz.netty.rest.api.operation.DefaultReq;
import com.kemalbeyaz.netty.rest.api.operation.DefaultRes;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpResponseStatus;

public class TodoMovedHandler implements Handler<DefaultReq, DefaultRes> {

    @Override
    public String getPath() {
        return "/todo";
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
    public DefaultRes handle(DefaultReq operationRequest) throws Exception {
        var defaultRes = new DefaultRes(HttpResponseStatus.MOVED_PERMANENTLY);
        defaultRes.getHeaders().set(HttpHeaderNames.LOCATION, "/todo/list");
        return defaultRes;
    }
}
