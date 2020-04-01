package com.example.HWM;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "task_table")
public class Task implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "description")
    private String mDescription;

    @ColumnInfo(name = "deadline")
    private String mDeadline;

    @ColumnInfo(name = "status")
    private boolean mstatus;


    public Task(String name, String description, String deadline){

        this.mName = name;
        this.mDescription = description;
        this.mDeadline = deadline;
        this.mstatus = false;
    }

    public String getName(){
        return this.mName;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getDeadline() {
        return this.mDeadline;
    }

    public boolean isMstatus() {
        return mstatus;
    }

    public void setMstatus(boolean mstatus) {
        this.mstatus = mstatus;
    }
}
