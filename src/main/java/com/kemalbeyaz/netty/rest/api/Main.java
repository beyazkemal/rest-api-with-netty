package com.kemalbeyaz.netty.rest.api;

import com.kemalbeyaz.netty.rest.api.server.Server;
import com.kemalbeyaz.netty.rest.api.todo.ToDoServiceImpl;

public class Main {

    private static final int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws InterruptedException {
        int port = args.length == 0 ? DEFAULT_PORT : Integer.parseInt(args[0]);

        var toDoService = new ToDoServiceImpl();
        Server server = new Server(toDoService);
        server.run(port);
    }
}
