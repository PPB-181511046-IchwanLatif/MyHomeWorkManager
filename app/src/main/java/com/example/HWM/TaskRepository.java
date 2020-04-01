package com.example.HWM;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTask;

    TaskRepository(Application application){
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTask = mTaskDao.getAlphabetizedWords();
    }

    LiveData<List<Task>> getmAllTasks(){
        return mAllTask;
    }

    void insert(final Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(() -> {
            mTaskDao.insert(task);
        });
    }

    void update(final Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(() ->{
            mTaskDao.update(task);
        });
    }
}
