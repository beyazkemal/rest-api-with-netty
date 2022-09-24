package com.kemalbeyaz.netty.rest.api.operation.add;

import com.kemalbeyaz.netty.rest.api.operation.OperationResponse;
import com.kemalbeyaz.netty.rest.api.todo.ToDo;
import io.netty.handler.codec.http.HttpResponseStatus;

public class ToDoAddRes implements OperationResponse {

    private final HttpResponseStatus status;
    private final ToDo toDo;

    public ToDoAddRes(HttpResponseStatus status, ToDo toDo) {
        this.status = status;
        this.toDo = toDo;
    }

    public ToDo getToDo() {
        return toDo;
    }

    @Override
    public HttpResponseStatus getStatus() {
        return status;
    }
}
