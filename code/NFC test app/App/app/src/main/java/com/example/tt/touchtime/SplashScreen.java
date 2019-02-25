package com.example.tt.touchtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public void studentView(View view) {
        startActivity(new Intent(this, StudentDetails.class));
//        Intent intent = new Intent(this, StudentDetails.class);
//        startActivity(intent);
    }

    public void lecturerView(View View) {
        Intent intent = new Intent(this, LecturerDetails.class);
        startActivity(intent);
    }

}
