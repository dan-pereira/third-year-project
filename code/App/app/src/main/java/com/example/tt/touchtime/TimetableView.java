package com.example.tt.touchtime;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class TimetableView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable_view);
        //Default layout for screen
        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        assert extras != null;
        String id = extras.getString("ROOMID").replaceAll("\\s+", "");
        String time = extras.getString("TIME");
        String user_id = extras.getString("USER_TYPE");

        getJSONObjectFromURL("http://192.168.43.224:5000/timetables/locations/" + (id) + "/" + (user_id)); // call method with return in method



        // init info passed from last page
//        String message = id;
        // Show last page info passed
//        TextView textView = findViewById(R.id.textView);
//        textView.setText(message);
    }

    private void getJSONObjectFromURL(String urlString) throws java.lang.IllegalStateException {
        Context ctx = TimetableView.this.getApplicationContext();
        RequestQueue queue = Volley.newRequestQueue(ctx); // ctx is the context
        //Volley requests
        StringRequest stringRequest = new StringRequest(Request.Method.GET, (urlString), new Response.Listener<String>() {
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                System.out.println("Response is: " + response);                 // stack trace checker
                TextView textView = findViewById(R.id.textView);               // init new text view
                textView.setText(response);                                    // fill text field with info
                createTimetable(response);
            }
        }, new Response.ErrorListener() {                                   // error handling auto generated
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
                TextView textView = findViewById(R.id.textView);               // init new text view
                textView.setText("Response Error");
            }
        });
        queue.add(stringRequest);                                                   // add to thread
    }

    public void createTimetable(String mess){
        TextView textView = findViewById(R.id.textView);

        try {
            JSONObject data = new JSONObject(mess);

            String [] days = {"Mon","Tue", "Wed", "Thu", "Fri"};
            for(String d:days){

                String day = data.getString(d);// makes a day a string

                JSONArray jsonArray = new JSONArray(day);
                int count = jsonArray.length();
                for(int i= 0 ; i<count ; i += 1){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String m = jsonObject.getString("module");
                    String n= jsonObject.getString("name");
                    String l = jsonObject.getString("lec");
                    JSONArray ja = jsonObject.getJSONArray("hours");
                    String t = ja.getString(0);
                    int len = ja.length();

                    textView.setText("");

                    addToTable(d,m,n,l,t,len);
                }
            }

        } catch (Throwable tx) {
            Log.i("----__-_","here",tx);
            Log.e("My App", "Could not parse malformed JSON: \"",tx);
        }
    }

    private void addToTable(String day, String module, String name, String lec, String begin, int duration){

        String baseIdentifier = "";
        baseIdentifier += Character.toLowerCase(day.charAt(0));
        baseIdentifier += day.charAt(1);

        for(int i = 0 ; i< duration; i+=2){

            String [] hourMin = begin.split(":");
            String hour = hourMin[0];
            int time = Integer.parseInt(hour);
            time = time + i/2;

            String identifier = baseIdentifier + time;

            int id = getResources().getIdentifier(identifier,"id",getPackageName());
            TextView slot = findViewById(id);
            slot.setText(module+"\n"+name+"\n"+lec);

        }
    }








//    public void onButton(View view) throws NullPointerException {
////        String message1 = "hello world test";
//        Log.i("p----------","request being made");
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        String id = extras.getString("ROOMID").replaceAll("\\s+", "");
//        String time = extras.getString("TIME");
//        String user_id = extras.getString("USER_TYPE");
//        getJSONObjectFromURL("http://192.168.43.132:5000/timetables/locations/" + (id) + "/" + (user_id)); // call method with return in method
//    }
}

