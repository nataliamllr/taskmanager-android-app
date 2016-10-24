package com.example.millen12.taskmanager.models;

import java.util.Date;
import java.util.UUID;

/**
 * Created by millen12 on 13/10/2016.
 */

public class Task {

    private String title;
    private UUID id;
    private boolean taskStatus;
    private Date taskDate;

    public Task() {
        id = UUID.randomUUID();
        taskDate = new Date();
    }

    public Task(String title, UUID id, boolean taskStatus, Date date) {

        this.title = title;
        this.id = id;
        this.taskStatus = taskStatus;
        this.taskDate = date;
    }

    public String getTitle() {
        return title;
    }

    public UUID getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }
}
