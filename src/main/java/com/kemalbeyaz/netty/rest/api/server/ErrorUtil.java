package com.kemalbeyaz.netty.rest.api.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kemalbeyaz.netty.rest.api.JsonUtil;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;

import java.nio.charset.Charset;

public class ErrorUtil {

    private ErrorUtil() {
    }

    public static FullHttpResponse createErrorResponse(final HttpResponseStatus status, final String message) throws JsonProcessingException {
        var content = Unpooled.copiedBuffer(JsonUtil.writeAsJson(String.class, message), Charset.defaultCharset());
        var response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, content);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
        return response;
    }
}
