package com.kemalbeyaz.netty.rest.api.todo;

import java.util.List;

public interface ToDoService {

    List<ToDo> list(int page, int pageSize);

    ToDo read(int id) throws ToDoException;

    ToDo add(ToDo toDo) throws ToDoException;

    ToDo checkAsDone(int id) throws ToDoException;

    boolean remove(int id);
}
