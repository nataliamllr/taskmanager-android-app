package com.example.millen12.taskmanager.models;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by millen12 on 13/10/2016.
 */

public class TaskList {
    private static TaskList taskList;
    private static List<Task> tasks;

    private TaskList(Context context) {
        tasks = new ArrayList<>();
    }

    public static TaskList get(Context context) {
        if(taskList == null) {
            taskList = new TaskList(context);
        }
        return taskList;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> listOfTasks) {
        tasks = listOfTasks;
    }

    public Task getTask(UUID id) {
        for (Task task : tasks) {
            if(task.getId().equals(id)) {
                return task;
            }
        }
        return null;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
