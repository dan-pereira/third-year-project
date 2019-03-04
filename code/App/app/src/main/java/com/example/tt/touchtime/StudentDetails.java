package com.example.tt.touchtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StudentDetails extends AppCompatActivity {

//    TODO text box for time was removed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
    }


    public void scanView(View view) {
        Bundle extras = new Bundle();
        extras.putString("USER_TYPE", "student");
        Intent intent = new Intent(this, ScanScreen.class);
        intent.putExtras(extras);
        startActivity(intent);
    }

    public void timetableView(View view) {

        Bundle extras = new Bundle();

        EditText editText1 = findViewById(R.id.editText);
//        EditText editText2 = findViewById(R.id.editText2);

        String message1 = editText1.getText().toString();
//        String message2 = editText2.getText().toString();

        extras.putString("ROOMID", message1);
//        extras.putString("TIME", message2);
        extras.putString("USER_TYPE", "");


        Intent intent = new Intent(this, TimetableView.class);

        intent.putExtras(extras);
        startActivity(intent);
    }
}