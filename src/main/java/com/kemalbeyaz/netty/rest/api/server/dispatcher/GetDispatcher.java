package com.kemalbeyaz.netty.rest.api.server.dispatcher;

import com.kemalbeyaz.netty.rest.api.operation.DefaultRes;
import com.kemalbeyaz.netty.rest.api.operation.OperationRequest;
import com.kemalbeyaz.netty.rest.api.operation.OperationResponse;
import com.kemalbeyaz.netty.rest.api.server.HttpServerException;
import com.kemalbeyaz.netty.rest.api.server.handler.Handler;
import com.kemalbeyaz.netty.rest.api.server.handler.HelloHandler;
import com.kemalbeyaz.netty.rest.api.server.handler.ListHandler;
import com.kemalbeyaz.netty.rest.api.server.handler.TodoMovedHandler;
import com.kemalbeyaz.netty.rest.api.todo.ToDoService;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;

import java.util.HashMap;
import java.util.Map;

public class GetDispatcher<Q extends OperationRequest, S extends OperationResponse> implements Dispatcher {

    private final Map<String, Handler<Q, S>> handlerMap = new HashMap<>();

    @SuppressWarnings("unchecked")
    public GetDispatcher(final ToDoService toDoService) {
        var helloHandler = new HelloHandler();
        handlerMap.put(helloHandler.getPath(), (Handler<Q, S>) helloHandler);

        var listHandler = new ListHandler(toDoService);
        handlerMap.put(listHandler.getPath(), (Handler<Q, S>) listHandler);

        var todoMovedHandler = new TodoMovedHandler();
        handlerMap.put(todoMovedHandler.getPath(), (Handler<Q, S>) todoMovedHandler);
    }

    @Override
    public boolean isSupport(final HttpMethod method) {
        return method.name().equals(HttpMethod.GET.name());
    }

    @Override
    public FullHttpResponse dispatch(final FullHttpRequest fullHttpRequest) throws Exception {
        String uri = fullHttpRequest.uri();
        Handler<Q, S> handler = handlerMap.get(getRequestPath(uri));

        if (handler == null) {
            throw new HttpServerException(HttpResponseStatus.NOT_FOUND, "No handler for this URI!");
        }


        var operationRequest = handler.createOperationRequestFromJSON(fullHttpRequest.content().array());
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
