package com.kemalbeyaz.netty.rest.api.server.dispatcher;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;

public interface Dispatcher {

    boolean isSupport(HttpMethod method);

    FullHttpResponse dispatch(FullHttpRequest fullHttpRequest) throws Exception;

    default String getRequestPath(final String uri) {
        if (uri.contains("?")) {
            return uri.substring(0, uri.indexOf("?"));
        }
        return uri;
    }
}
