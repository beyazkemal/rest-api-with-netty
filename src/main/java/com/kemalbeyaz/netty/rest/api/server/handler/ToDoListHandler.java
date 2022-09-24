package com.kemalbeyaz.netty.rest.api.server.handler;

import com.kemalbeyaz.netty.rest.api.operation.list.ToDoListReq;
import com.kemalbeyaz.netty.rest.api.operation.list.ToDoListResp;
import com.kemalbeyaz.netty.rest.api.server.HttpServerException;
import com.kemalbeyaz.netty.rest.api.todo.ToDoService;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ToDoListHandler implements Handler<ToDoListReq, ToDoListResp> {

    private final ToDoService toDoService;

    public ToDoListHandler(final ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public String getBasePath() {
        return "/todo";
    }

    @Override
    public boolean canHandle(String uri) {
        return uri.equals(getBasePath()) || uri.equals(getBasePath() + "/");
    }

    @Override
    public Class<ToDoListReq> getRequestClass() {
        return ToDoListReq.class;
    }

    @Override
    public Class<ToDoListResp> getResponseClass() {
        return ToDoListResp.class;
    }

    @Override
    public ToDoListReq createDefaultRequest() {
        return new ToDoListReq(0, 10);
    }

    @Override
    public ToDoListReq createOperationRequestFromJSON(String uri, byte[] reqContent) throws IOException, HttpServerException {
        Map<String, List<String>> parameters = getParameters(uri);
        List<String> page = parameters.get("p");
        List<String> pageSize = parameters.get("ps");
        if (page == null || pageSize == null || page.isEmpty() || pageSize.isEmpty()) {
            return createDefaultRequest();
        }

        return new ToDoListReq(Integer.parseInt(page.get(0)), Integer.parseInt(pageSize.get(0)));
    }

    @Override
    public ToDoListResp handle(final ToDoListReq operationRequest) throws HttpServerException {
        return new ToDoListResp(HttpResponseStatus.OK,
                toDoService.list(operationRequest.getPage(), operationRequest.getPageSize()));
    }
}
