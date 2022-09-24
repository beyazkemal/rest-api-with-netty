package com.kemalbeyaz.netty.rest.api.operation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.netty.handler.codec.http.HttpResponseStatus;

public interface OperationResponse {

    @JsonIgnore
    HttpResponseStatus getStatus();
}
