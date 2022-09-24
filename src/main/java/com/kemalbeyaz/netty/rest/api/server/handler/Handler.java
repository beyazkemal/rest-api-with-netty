package com.kemalbeyaz.netty.rest.api.server.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kemalbeyaz.netty.rest.api.JsonUtil;
import com.kemalbeyaz.netty.rest.api.operation.OperationRequest;
import com.kemalbeyaz.netty.rest.api.operation.OperationResponse;
import com.kemalbeyaz.netty.rest.api.server.HttpServerException;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Handler<Q extends OperationRequest, S extends OperationResponse> {

    String getBasePath();

    boolean canHandle(String uri);

    Class<Q> getRequestClass();

    Class<S> getResponseClass();

    Q createDefaultRequest() throws HttpServerException;

    S handle(Q operationRequest) throws HttpServerException;

    default Map<String, List<String>> getParameters(final String uri) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);
        return queryStringDecoder.parameters();
    }

    default Q createOperationRequestFromJSON(String uri, byte[] reqContent) throws IOException, HttpServerException {
        if (reqContent.length == 0) {
            return createDefaultRequest();
        }

        return JsonUtil.readJson(getRequestClass(), reqContent);
    }

    default byte[] convertOperationResponseToJSON(S response) throws JsonProcessingException {
        return JsonUtil.writeAsByte(getResponseClass(), response);
    }
}
