package com.kemalbeyaz.netty.rest.api.operation.add;

import com.kemalbeyaz.netty.rest.api.operation.OperationRequest;

public class ToDoAddReq implements OperationRequest {

    private String todo;

    public ToDoAddReq() {
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getTodo() {
        return todo;
    }
}
