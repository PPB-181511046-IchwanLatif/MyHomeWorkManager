package com.example.HWM;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.HWM.REPLY";

    private EditText mEditName, mEditDesc, mEditDeadline;

    DatePickerDialog datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        mEditName = findViewById(R.id.edit_name);
        mEditDesc = findViewById(R.id.edit_desc);
        mEditDeadline = findViewById(R.id.edit_deadline);
        mEditDeadline.setInputType(EditorInfo.TYPE_NULL);
        mEditDeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePicker = new DatePickerDialog(NewTaskActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int syear, int smonth, int sdayOfMonth) {
                                smonth++;
                                mEditDeadline.setText(sdayOfMonth + "-" + smonth + "-" + syear);
                            }
                        }, day, month, year);
                datePicker.show();
            }
        });
        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mEditName.getText())){
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else{
                    String taskName = mEditName.getText().toString();
                    String taskDesc = mEditDesc.getText().toString();
                    String taskDeadline = mEditDeadline.getText().toString();
                    Task newTask = new Task(taskName, taskDesc, taskDeadline);
                    replyIntent.putExtra("newTask", newTask);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
