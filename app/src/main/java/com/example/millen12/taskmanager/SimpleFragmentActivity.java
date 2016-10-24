package com.example.millen12.taskmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.millen12.taskmanager.services.DBService;

/**
 * Created by millen12 on 13/10/2016.
 */

public abstract class SimpleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment taskFragment = fragmentManager.findFragmentById(R.id.fragment_container);

        if (taskFragment == null) {
            taskFragment = createFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container, taskFragment).commit();
        }
        DBService.create(this);
    }
}
