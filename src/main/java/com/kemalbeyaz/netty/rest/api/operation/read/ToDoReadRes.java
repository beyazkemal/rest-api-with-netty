package com.kemalbeyaz.netty.rest.api.operation.read;

import com.kemalbeyaz.netty.rest.api.operation.OperationResponse;
import com.kemalbeyaz.netty.rest.api.todo.ToDo;
import io.netty.handler.codec.http.HttpResponseStatus;

public class ToDoReadRes implements OperationResponse {

    private final HttpResponseStatus httpResponseStatus;
    private final ToDo toDo;

    public ToDoReadRes(HttpResponseStatus httpResponseStatus, ToDo toDo) {
        this.httpResponseStatus = httpResponseStatus;
        this.toDo = toDo;
    }

    public ToDo getToDo() {
        return toDo;
    }

    @Override
    public HttpResponseStatus getStatus() {
        return httpResponseStatus;
    }
}
