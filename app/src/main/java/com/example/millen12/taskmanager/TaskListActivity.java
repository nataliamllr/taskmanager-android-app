package com.example.millen12.taskmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by millen12 on 13/10/2016.
 */

public class TaskListActivity extends SimpleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new TaskListFragment();
    }
}
