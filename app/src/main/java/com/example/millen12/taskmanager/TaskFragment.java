package com.example.millen12.taskmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.millen12.taskmanager.models.Task;
import com.example.millen12.taskmanager.models.TaskList;
import com.example.millen12.taskmanager.services.DBService;

import org.w3c.dom.Text;

import java.util.UUID;

/**
 * Created by millen12 on 13/10/2016.
 */

public class TaskFragment extends Fragment {
    private Task task;
    TextView editText;
    CheckBox statusCheckBox;
    Button dateButton;
    Button saveButton;
    public static final String EXTRA_TASK_ID = "extra_task_id";

    public static TaskFragment newInstance(UUID taskId) {
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_TASK_ID, taskId);
        TaskFragment taskFragment = new TaskFragment();
        taskFragment.setArguments(args);
        return taskFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID taskId = (UUID) getArguments().get(EXTRA_TASK_ID);
        task = TaskList.get(getActivity()).getTask(taskId);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        editText = (TextView) view.findViewById(R.id.task_title);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                task.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editText.setText(task.getTitle());

        statusCheckBox = (CheckBox) view.findViewById(R.id.task_solved);
        statusCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                task.setTaskStatus(isChecked);
            }
        });
        statusCheckBox.setChecked(task.isTaskStatus());

        dateButton = (Button) view.findViewById(R.id.task_time);
        dateButton.setText(task.getTaskDate().toString());
        dateButton.setEnabled(false);

        saveButton = (Button) view.findViewById(R.id.add_task_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBService.addTask(task);
            }
        });


        return view;
    }
}
