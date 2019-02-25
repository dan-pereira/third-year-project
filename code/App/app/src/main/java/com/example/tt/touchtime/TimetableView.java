package com.example.tt.touchtime;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class TimetableView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_view);
        //Default layout for screen
        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        // Init page
        String id = extras.getString("ROOMID");
        String time = extras.getString("TIME");
        // init info passed from last page
        String message = id + time;
        // Show last page info passed
        TextView textView = findViewById(R.id.textView);
        textView.setText(message);
    }

    private void getJSONObjectFromURL(String urlString) {
        Context ctx = TimetableView.this.getApplicationContext();
        RequestQueue queue = Volley.newRequestQueue(ctx); // ctx is the context
        //Volley requests
        StringRequest stringRequest = new StringRequest(Request.Method.GET, (urlString), new Response.Listener<String>() {
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    System.out.println("Response is: " + response);                 // stack trace checker
                    TextView textView = findViewById(R.id.textView2);               // init new text view
                    textView.setText(response);                                     // fill text field with info
                }
                }, new Response.ErrorListener() {                                   // error handling auto generated
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
            }
        });
        queue.add(stringRequest);                                                   // add to thread
    }

    public void onButton(View view) {
//        String message1 = "hello world test";
        Log.i("p----------","request being made");
        getJSONObjectFromURL("http://136.206.255.215:5000/timetables/locations/LG27"); // call method with return in method
    }
}

