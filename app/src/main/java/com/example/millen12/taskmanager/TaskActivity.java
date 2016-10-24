package com.example.millen12.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

import static com.example.millen12.taskmanager.TaskFragment.EXTRA_TASK_ID;

public class TaskActivity extends SimpleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        UUID taskId = (UUID) getIntent().getSerializableExtra(EXTRA_TASK_ID);
        return TaskFragment.newInstance(taskId);
    }

    public static Intent newIntent(Context context, UUID taskId) {
        Intent intent = new Intent(context, TaskActivity.class);
        intent.putExtra(EXTRA_TASK_ID, taskId);
        return intent;
    }
}
