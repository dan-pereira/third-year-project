package com.example.tt.touchtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TimetableView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_view);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        String id = extras.getString("ROOMID");
        String time = extras.getString("TIME");

        String message = id + time;

        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }
}