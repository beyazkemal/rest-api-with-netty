package com.kemalbeyaz.netty.rest.api.server.dispatcher;

import com.kemalbeyaz.netty.rest.api.operation.OperationRequest;
import com.kemalbeyaz.netty.rest.api.operation.OperationResponse;
import com.kemalbeyaz.netty.rest.api.server.handler.*;
import com.kemalbeyaz.netty.rest.api.todo.ToDoService;
import io.netty.handler.codec.http.HttpMethod;

import java.util.ArrayList;
import java.util.List;

public class GetDispatcher<Q extends OperationRequest, S extends OperationResponse> extends AbstractDispatcher<Q, S> {

    private final List<Handler<Q, S>> handlers = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public GetDispatcher(final ToDoService toDoService) {
        handlers.add((Handler<Q, S>) new HelloHandler());
        handlers.add((Handler<Q, S>) new ToDoListHandler(toDoService));
        handlers.add((Handler<Q, S>) new ToDoReadHandler(toDoService));
        handlers.add((Handler<Q, S>) new MovedTodoListHandler());
    }

    @Override
    public boolean isSupport(final HttpMethod method) {
        return method.name().equals(HttpMethod.GET.name());
    }

    @Override
    public List<Handler<Q, S>> getHandlers() {
        return handlers;
    }
}
