package com.kemalbeyaz.netty.rest.api.server.handler;

import com.kemalbeyaz.netty.rest.api.operation.add.ToDoAddReq;
import com.kemalbeyaz.netty.rest.api.operation.add.ToDoAddRes;
import com.kemalbeyaz.netty.rest.api.server.HttpServerException;
import com.kemalbeyaz.netty.rest.api.todo.ToDo;
import com.kemalbeyaz.netty.rest.api.todo.ToDoException;
import com.kemalbeyaz.netty.rest.api.todo.ToDoService;
import io.netty.handler.codec.http.HttpResponseStatus;

public class ToDoAddHandler implements Handler<ToDoAddReq, ToDoAddRes> {

    private final ToDoService toDoService;

    public ToDoAddHandler(final ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    public String getBasePath() {
        return "/todo";
    }

    @Override
    public boolean canHandle(final String uri) {
        return uri.equals(getBasePath()) || uri.equals(getBasePath() + "/");
    }

    @Override
    public Class<ToDoAddReq> getRequestClass() {
        return ToDoAddReq.class;
    }

    @Override
    public Class<ToDoAddRes> getResponseClass() {
        return ToDoAddRes.class;
    }

    @Override
    public ToDoAddReq createDefaultRequest() throws HttpServerException {
        throw new HttpServerException(HttpResponseStatus.BAD_REQUEST, "Ekleme isteği başarısız! Http body boş olamaz!");
    }

    @Override
    public ToDoAddRes handle(final ToDoAddReq operationRequest) throws HttpServerException {
        try {
            return new ToDoAddRes(HttpResponseStatus.OK, toDoService.add(new ToDo(operationRequest.getTodo())));
        } catch (ToDoException e) {
            throw new HttpServerException(HttpResponseStatus.UNPROCESSABLE_ENTITY, e.getMessage());
        }
    }
}
