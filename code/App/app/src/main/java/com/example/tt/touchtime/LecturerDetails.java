package com.example.tt.touchtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LecturerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_details);
    }

    public void scanView(View view) {
        Bundle extras = new Bundle();
        extras.putString("USER_TYPE", "");
        Intent intent = new Intent(this, ScanScreen.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void timetableView(View view) {
        Bundle extras = new Bundle();

        EditText editText1 = findViewById(R.id.editText3);
//        EditText editText2 = findViewById(R.id.editText4);

        String message1 = editText1.getText().toString();
//        String message2 = editText2.getText().toString();

        extras.putString("ROOMID", message1);
        extras.putString("USER_TYPE", "");


        Intent intent = new Intent(this, TimetableView.class);

        intent.putExtras(extras);
        startActivity(intent);
    }


    public void attendanceView(View view) {
        Bundle extras = new Bundle();

        EditText editText1 = findViewById(R.id.editText3);
//        EditText editText2 = findViewById(R.id.editText4);

        String message1 = editText1.getText().toString();
//        String message2 = editText2.getText().toString();

        extras.putString("ROOMID", message1);
//        extras.putString("TIME", message2);
        extras.putString("USER_TYPE", "lecturer");


        Intent intent = new Intent(this, TimetableView.class);

        intent.putExtras(extras);
        startActivity(intent);
    }
}