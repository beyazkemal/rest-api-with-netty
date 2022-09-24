package com.kemalbeyaz.netty.rest.api.server;

import com.kemalbeyaz.netty.rest.api.todo.ToDoService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Initializes http server with netty.
 *
 * @author Kemal Beyaz
 */
public class Server {

    private final ToDoService toDoService;

    public Server(final ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    public void run(final int port) throws InterruptedException {

        final var bossGroup = new NioEventLoopGroup();
        final var workerGroup = new NioEventLoopGroup();

        final var serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerInitializer(toDoService))
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        try {
            var httpChannelFuture = serverBootstrap.bind(port).sync();
            httpChannelFuture.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
