package com.example.HWM;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mRepository;
    private LiveData<List<Task>> mAllTasks;

    public TaskViewModel(Application application){
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getmAllTasks();
    }

    LiveData<List<Task>> getmAllTasks(){
        return mAllTasks;
    }

    public void insert(Task task){
        mRepository.insert(task);
    }
    public void update(Task task){
        mRepository.update(task);
    }
}
