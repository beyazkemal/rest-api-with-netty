package com.kemalbeyaz.netty.rest.api.operation.read;

import com.kemalbeyaz.netty.rest.api.operation.OperationRequest;

public class ToDoReadReq implements OperationRequest {

    private final int id;

    public ToDoReadReq(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
