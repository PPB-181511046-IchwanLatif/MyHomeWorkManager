package com.example.HWM;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {
    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView Taskname, Taskdesc,Taskdeadline;
        private CheckBox Taskstatus;
        private TaskViewHolder(View itemView) {
            super(itemView);
            Taskname = itemView.findViewById(R.id.name);
            Taskdesc = itemView.findViewById(R.id.description);
            Taskdeadline = itemView.findViewById(R.id.deadline);
            Taskstatus = itemView.findViewById(R.id.status);
        }
    }


    private final LayoutInflater mInflater;
    private List<Task> mTasks;
    private TaskViewModel vm;


    TaskListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        vm = ViewModelProviders.of((MainActivity)context).get(TaskViewModel.class);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        if (mTasks != null) {
            Task current = mTasks.get(position);
            holder.Taskname.setText(current.getName());
            holder.Taskdeadline.setText(current.getDeadline());
            holder.Taskdesc.setText(current.getDescription());
            holder.Taskstatus.setChecked(current.isMstatus() ? true: false);


        } else {
            holder.Taskname.setText("No Task");
        }


        holder.Taskstatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTasks.get(position).setMstatus(isChecked);
                vm.update(mTasks.get(position));
            }
        });
    }

    void setWords(List<Task> tasks) {
        mTasks = tasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTasks != null) {
            return mTasks.size();
        } else {
            return 0;
        }
    }

}