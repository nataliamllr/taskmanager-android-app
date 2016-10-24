package com.example.millen12.taskmanager.services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.millen12.taskmanager.models.Task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static android.database.sqlite.SQLiteDatabase.openOrCreateDatabase;

/**
 * Created by millen12 on 19/10/2016.
 */

public class DBService {

    static SQLiteDatabase database;

    public static void create(Context context) {
        database = context.openOrCreateDatabase("TaskTestDB", context.MODE_PRIVATE, null);
        database.execSQL("CREATE TABLE IF NOT EXISTS Task(id VARCHAR, task_name VARCHAR,date VARCHAR,completed VARCHAR);");
    }

    public static void addTask(Task task) {
        database.execSQL("INSERT INTO Task VALUES('"+task.getId().toString()+"','"+task.getTitle()+"','"+task.getTaskDate().toString()+"','"+Boolean.toString(task.isTaskStatus())+"');");
    }

    public static List<Task> retrieveTasks() throws ParseException {
        ArrayList<Task> savedTasks = new ArrayList<Task>();
        Cursor cursor = database.rawQuery("SELECT * FROM Task", null);
        while(cursor.moveToNext()) {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(cursor.getString(2));
            Task task = new Task(cursor.getString(1), UUID.fromString(cursor.getString(0)), Boolean.getBoolean(cursor.getString(3)), date);
            savedTasks.add(task);
        }
        return savedTasks;
    }
}
