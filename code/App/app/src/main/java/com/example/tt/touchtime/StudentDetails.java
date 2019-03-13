package com.example.tt.touchtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StudentDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
    }

    //    Send user type to scan screen
    public void scanView(View view) {
        Bundle extras = new Bundle();
        extras.putString("USER_TYPE", "student");
        Intent intent = new Intent(this, ScanScreen.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    //    Send user type to timetable view
    public void timetableView(View view) {
        Bundle extras = new Bundle();
        EditText editText1 = findViewById(R.id.editText);
        String message1 = editText1.getText().toString().toUpperCase();
        extras.putString("ROOMID", message1);
        extras.putString("USER_TYPE", "");
        Intent intent = new Intent(this, TimetableView.class);
        intent.putExtras(extras);
        startActivity(intent);
    }
}