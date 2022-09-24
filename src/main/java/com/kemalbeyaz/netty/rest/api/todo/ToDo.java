package com.kemalbeyaz.netty.rest.api.todo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class ToDo {

    private static final AtomicInteger ID_SEQUENCE = new AtomicInteger(0);

    private final int id;
    private final String todo;
    private boolean done;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private final Date created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date finished;

    public ToDo(final String todo) {
        this.id = ID_SEQUENCE.getAndIncrement();
        this.todo = todo;
        this.created = new Date();
    }

    public int getId() {
        return id;
    }

    public String getTodo() {
        return todo;
    }

    public Date getCreated() {
        return created;
    }

    public boolean isDone() {
        return done;
    }

    public Date getFinished() {
        return finished;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }
}
