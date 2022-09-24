package com.kemalbeyaz.netty.rest.api.server.handler;

import com.kemalbeyaz.netty.rest.api.operation.list.ListReq;
import com.kemalbeyaz.netty.rest.api.operation.list.ListResp;
import com.kemalbeyaz.netty.rest.api.todo.ToDoService;
import io.netty.handler.codec.http.HttpResponseStatus;

public class ListHandler implements Handler<ListReq, ListResp> {

    private final ToDoService toDoService;

    public ListHandler(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public String getPath() {
        return "/todo/list";
    }

    @Override
    public Class<ListReq> getRequestClass() {
        return ListReq.class;
    }

    @Override
    public Class<ListResp> getResponseClass() {
        return ListResp.class;
    }

    @Override
    public ListReq createDefaultRequest() {
        return new ListReq(0, 10);
    }

    @Override
    public ListResp handle(final ListReq operationRequest) throws Exception {
        return new ListResp(HttpResponseStatus.OK,
                toDoService.list(operationRequest.getPage(), operationRequest.getPageSize()));
    }
}
