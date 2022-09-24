package com.kemalbeyaz.netty.rest.api.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ToDoServiceImpl implements ToDoService {

    private final List<ToDo> toDos = new ArrayList<>();

    public ToDoServiceImpl() {
        ToDo toDo = new ToDo("Netty ile REST API Örneği Hazırlanacak");
        toDo.setDone(true);
        toDo.setFinished(new Date());
        toDos.add(toDo);

        toDos.add(new ToDo("Proje Git'e Pushlanacak"));
        toDos.add(new ToDo("Sunum Yapılacak"));
    }

    @Override
    public List<ToDo> list(final int page, int pageSize) {
        return toDos.stream()
                .skip((long) page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public ToDo read(final int id) throws ToDoException {
        var optionalToDo = toDos.stream()
                .filter(todo -> todo.getId() == id)
                .findAny();

        if (optionalToDo.isEmpty()) {
            throw new ToDoException(id + " id'li todo bulunamadı!");
        }

        return optionalToDo.get();
    }

    @Override
    public ToDo add(final ToDo toDo) throws ToDoException {
        toDos.add(toDo);
        return toDo;
    }

    @Override
    public ToDo checkAsDone(final int id) throws ToDoException {
        var toDoOptional = toDos.stream()
                .filter(toDo -> toDo.getId() == id)
                .findAny();

        if (toDoOptional.isPresent()) {
            ToDo toDo = toDoOptional.get();
            toDo.setDone(true);
            toDo.setFinished(new Date());
            return toDo;
        }

        throw new ToDoException(id + " id'li todo bulunamadı!");
    }

    @Override
    public boolean remove(final int id) {
        return toDos.removeIf(toDo -> toDo.getId() == id);
    }
}
