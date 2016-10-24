package com.example.millen12.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.millen12.taskmanager.models.Task;
import com.example.millen12.taskmanager.models.TaskList;
import com.example.millen12.taskmanager.services.DBService;

import java.text.ParseException;
import java.util.List;

/**
 * Created by millen12 on 13/10/2016.
 */

public class TaskListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_task_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.task_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        try {
            updateUI();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_task_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch(item.getItemId()) {
            case R.id.menu_item_new_task:
                Task task = new Task();
                TaskList.get(getActivity()).addTask(task);
                Intent intent = TaskActivity.newIntent(getActivity(),task.getId());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void updateUI() throws ParseException {
        TaskList taskList = TaskList.get(getActivity());
        taskList.setTasks(DBService.retrieveTasks());
        List<Task> tasks = taskList.getTasks();
        if(taskAdapter == null) {
            taskAdapter = new TaskAdapter(tasks);
            recyclerView.setAdapter(taskAdapter);
        } else {
            taskAdapter.notifyDataSetChanged();
        }
    }

    private class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titleTextView;
        public TextView dateTextView;
        public CheckBox statusCheckBox;
        private Task task;

        public TaskViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.list_item_task_title);
            dateTextView = (TextView) itemView.findViewById(R.id.list_item_date);
            statusCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_task_completed);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = TaskActivity.newIntent(getActivity(), task.getId());
            startActivity(intent);
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

        private List<Task> tasks;

        public TaskAdapter(List<Task> listOfTasks) {
            tasks = listOfTasks;
        }

        @Override
        public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_tasks, parent, false);
            return new TaskViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TaskViewHolder holder, int position) {
            holder.titleTextView.setText(tasks.get(position).getTitle());
            holder.dateTextView.setText(tasks.get(position).getTaskDate().toString());
            holder.statusCheckBox.setChecked(tasks.get(position).isTaskStatus());
            holder.task = tasks.get(position);

        }

        @Override
        public int getItemCount() {
            return tasks.size();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            updateUI();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
