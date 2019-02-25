package com.example.tt.touchtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LecturerDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecturer_details);
    }

    public void scanView(View view) {
        startActivity(new Intent(this, ScanScreen.class));
    }
}
