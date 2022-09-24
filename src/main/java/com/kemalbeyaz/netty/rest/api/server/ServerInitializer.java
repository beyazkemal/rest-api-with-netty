package com.kemalbeyaz.netty.rest.api.server;

import com.kemalbeyaz.netty.rest.api.todo.ToDoService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * Initializes the channels with Http Server Codec and Http Server Handler.
 *
 * @author Kemal Beyaz
 */
public class ServerInitializer extends ChannelInitializer<Channel> {

    private final ToDoService toDoService;

    public ServerInitializer(final ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                .addLast(new HttpServerCodec())
                .addLast(new HttpObjectAggregator(Integer.MAX_VALUE))
                .addLast(new HttpServerHandler(toDoService));
    }
}
