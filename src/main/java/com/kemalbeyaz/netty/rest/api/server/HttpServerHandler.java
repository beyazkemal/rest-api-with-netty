package com.kemalbeyaz.netty.rest.api.server;

import com.kemalbeyaz.netty.rest.api.server.dispatcher.Dispatcher;
import com.kemalbeyaz.netty.rest.api.server.dispatcher.GetDispatcher;
import com.kemalbeyaz.netty.rest.api.server.dispatcher.PostDispatcher;
import com.kemalbeyaz.netty.rest.api.todo.ToDoService;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Sample HTTP Server Handler
 *
 * @author Kemal Beyaz
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    private final List<Dispatcher> dispatchers = new ArrayList<>(2);

    @SuppressWarnings("rawtypes")
    public HttpServerHandler(final ToDoService toDoService) {
        dispatchers.add(new GetDispatcher(toDoService));
        dispatchers.add(new PostDispatcher(toDoService));
    }

    @Override
    protected void channelRead0(final ChannelHandlerContext channelHandlerContext, final FullHttpRequest fullHttpRequest)
            throws Exception {

        var dispatcherOptional = dispatchers.stream()
                .filter(dispatcher -> dispatcher.isSupport(fullHttpRequest.method()))
                .findAny();

        Dispatcher dispatcher;
        if (dispatcherOptional.isPresent()) {
            dispatcher = dispatcherOptional.get();
        } else {
            throw new HttpServerException(HttpResponseStatus.METHOD_NOT_ALLOWED, "This HTTP method not supported yet!");
        }

        var response = dispatcher.dispatch(fullHttpRequest);
        channelHandlerContext.writeAndFlush(response);

        System.out.println("-------------------------------------------");
        HttpHeaders headers = fullHttpRequest.headers();
        HttpMethod method = fullHttpRequest.method();
        String uri = fullHttpRequest.uri();
        HttpVersion httpVersion = fullHttpRequest.protocolVersion();
        ByteBuf content1 = fullHttpRequest.content();
        System.out.println("HEADER: " + headers);
        System.out.println("METHOD: " + method);
        System.out.println("uri: " + uri);
        System.out.println("httpVersion: " + httpVersion);
        System.out.println("content1: " + content1);
        System.out.println("-------------------------------------------");
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) throws Exception {
        if(cause instanceof HttpServerException serverException) {
            var errorResponse =
                    ErrorUtil.createErrorResponse(serverException.getStatus(), serverException.getMessage());
            ctx.writeAndFlush(errorResponse);
            return;
        }

        cause.printStackTrace();
        ctx.close();
    }
}
