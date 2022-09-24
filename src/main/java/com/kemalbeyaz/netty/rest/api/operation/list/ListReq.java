package com.kemalbeyaz.netty.rest.api.operation.list;

import com.kemalbeyaz.netty.rest.api.operation.OperationRequest;

public class ListReq implements OperationRequest {

    private final int pageSize;
    private final int page;

    public ListReq(int page, int pageSize) {
        this.pageSize = pageSize;
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPage() {
        return page;
    }
}
