package com.kemalbeyaz.netty.rest.api.operation.list;

import com.kemalbeyaz.netty.rest.api.operation.OperationResponse;
import com.kemalbeyaz.netty.rest.api.todo.ToDo;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.util.List;

public class ToDoListResp implements OperationResponse {

    private final HttpResponseStatus status;
    private final List<ToDo> todos;

    public ToDoListResp(HttpResponseStatus status, List<ToDo> todos) {
        this.status = status;
        this.todos = todos;
    }

    public List<ToDo> getTodos() {
        return todos;
    }

    @Override
    public HttpResponseStatus getStatus() {
        return status;
    }
}
