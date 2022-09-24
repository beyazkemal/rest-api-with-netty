package com.kemalbeyaz.netty.rest.api.server.handler;

import com.kemalbeyaz.netty.rest.api.operation.read.ToDoReadReq;
import com.kemalbeyaz.netty.rest.api.operation.read.ToDoReadRes;
import com.kemalbeyaz.netty.rest.api.server.HttpServerException;
import com.kemalbeyaz.netty.rest.api.todo.ToDoException;
import com.kemalbeyaz.netty.rest.api.todo.ToDoService;
import io.netty.handler.codec.http.HttpResponseStatus;

import java.io.IOException;

public class ToDoReadHandler implements Handler<ToDoReadReq, ToDoReadRes> {

    private final ToDoService toDoService;

    public ToDoReadHandler(final ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public String getBasePath() {
        return "/todo";
    }

    @Override
    public boolean canHandle(final String uri) {
        if (uri.startsWith(getBasePath())) {
            try {
                readIdFromUri(uri);
                return true;
            } catch (Exception ignore) {
            }
        }
        return false;
    }

    @Override
    public Class<ToDoReadReq> getRequestClass() {
        return ToDoReadReq.class;
    }

    @Override
    public Class<ToDoReadRes> getResponseClass() {
        return ToDoReadRes.class;
    }

    @Override
    public ToDoReadReq createDefaultRequest() {
        return new ToDoReadReq(-1);
    }

    @Override
    public ToDoReadReq createOperationRequestFromJSON(String uri, byte[] reqContent) throws IOException {
        return new ToDoReadReq(readIdFromUri(uri));
    }

    @Override
    public ToDoReadRes handle(final ToDoReadReq operationRequest) throws HttpServerException {
        try {
            return new ToDoReadRes(HttpResponseStatus.OK, toDoService.read(operationRequest.getId()));
        } catch (ToDoException e) {
            throw new HttpServerException(HttpResponseStatus.NOT_FOUND, e.getMessage());
        }
    }

    private int readIdFromUri(String uri) {
        String[] splitUri = uri.split("/");
        if (splitUri.length == 3) {
            String pathVariable = splitUri[2];
            return Integer.parseInt(pathVariable);
        }
        throw new IllegalArgumentException("Bu uygun bir path parametresi değil.");
    }
}
