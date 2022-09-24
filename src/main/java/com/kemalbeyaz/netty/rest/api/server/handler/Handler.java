package com.kemalbeyaz.netty.rest.api.server.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kemalbeyaz.netty.rest.api.JsonUtil;
import com.kemalbeyaz.netty.rest.api.operation.OperationRequest;
import com.kemalbeyaz.netty.rest.api.operation.OperationResponse;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Handler<Q extends OperationRequest, S extends OperationResponse> {

    String getPath();

    Class<Q> getRequestClass();

    Class<S> getResponseClass();

    Q createDefaultRequest();

    S handle(Q operationRequest) throws Exception;

    default Map<String, List<String>> getParameters(final String uri) {
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(uri);
        return queryStringDecoder.parameters();
    }

    default Q createOperationRequestFromJSON(byte[] reqContent) throws IOException {
        if (reqContent.length == 0) {
            return createDefaultRequest();
        }

        return JsonUtil.readJson(getRequestClass(), reqContent);
    }

    default byte[] convertOperationResponseToJSON(S response) throws JsonProcessingException {
        return JsonUtil.writeAsByte(getResponseClass(), response);
    }
}
