package com.kemalbeyaz.netty.rest.api.server.dispatcher;

import com.kemalbeyaz.netty.rest.api.operation.DefaultRes;
import com.kemalbeyaz.netty.rest.api.operation.OperationRequest;
import com.kemalbeyaz.netty.rest.api.operation.OperationResponse;
import com.kemalbeyaz.netty.rest.api.server.HttpServerException;
import com.kemalbeyaz.netty.rest.api.server.handler.Handler;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;

import java.util.List;

public abstract class AbstractDispatcher<Q extends OperationRequest, S extends OperationResponse> implements Dispatcher {

    public abstract List<Handler<Q, S>> getHandlers();

    @Override
    public FullHttpResponse dispatch(final FullHttpRequest fullHttpRequest) throws Exception {
        String uri = fullHttpRequest.uri();

        var optionalHandler = getHandlers().stream()
                .filter(handler -> handler.canHandle(getRequestPath(uri)))
                .findAny();

        if (optionalHandler.isEmpty()) {
            throw new HttpServerException(HttpResponseStatus.NOT_FOUND, "No handler for this URI!");
        }

        var handler = optionalHandler.get();
        var operationRequest = handler.createOperationRequestFromJSON(uri, ByteBufUtil.getBytes(fullHttpRequest.content()));
        var response = handler.handle(operationRequest);
        byte[] bytes = handler.convertOperationResponseToJSON(response);
        return createHttpResponse(response, bytes);
    }

    private FullHttpResponse createHttpResponse(S operationResponse, byte[] data) {
        var content = Unpooled.copiedBuffer(data);
        var response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, operationResponse.getStatus(), content);
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON);
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

        if (operationResponse instanceof DefaultRes defaultRes) {
            defaultRes.getHeaders().forEach(h -> response.headers().set(h.getKey(), h.getValue()));
        }

        return response;
    }
}
