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


    public void scanView(View view) {
        startActivity(new Intent(this, ScanScreen.class));

    }

    public void timetableView(View view) {

        Bundle extras = new Bundle();

        EditText editText1 = findViewById(R.id.editText);
        EditText editText2 = findViewById(R.id.editText2);

        String message1 = editText1.getText().toString();
        String message2 = editText2.getText().toString();

        extras.putString("ROOMID", message1);
        extras.putString("TIME", message2);


        Intent intent = new Intent(this, TimetableView.class);

        intent.putExtras(extras);
        startActivity(intent);
    }
}