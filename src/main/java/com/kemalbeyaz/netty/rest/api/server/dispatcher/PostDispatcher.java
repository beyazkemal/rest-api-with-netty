package com.kemalbeyaz.netty.rest.api.server.dispatcher;

import com.kemalbeyaz.netty.rest.api.todo.ToDoService;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;

public class PostDispatcher implements Dispatcher {

    public PostDispatcher(final ToDoService toDoService) {
    }

    @Override
    public boolean isSupport(final HttpMethod method) {
        return method.name().equals(HttpMethod.POST.name());
    }

    @Override
    public FullHttpResponse dispatch(final FullHttpRequest fullHttpRequest) throws Exception {
        return null;
    }
}
