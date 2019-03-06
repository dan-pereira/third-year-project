package com.example.tt.touchtime;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Authentication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
    }

    private String encrypt(String base){

        StringBuilder bases = new StringBuilder("1");
        StringBuilder acbase = new StringBuilder("1");
        for (int i = 0 ; bases.length() < 10 ; i++){
            bases.append(base);
        }
        for (int i = bases.length()-1 ; i >= 0; i--){
            int ascii = (int) bases.charAt(i);
            String a = Integer.toHexString(ascii+5);
            acbase.append(a);
        }
//        String hx = Integer.toHexString(Integer.parseInt(bases.toString()));
        String hxr = new StringBuffer(acbase).reverse().toString();
        return hxr;
    }

    public void check_authentication (View view) {

        EditText userTxt = findViewById(R.id.editText5);
        EditText paswordTxt = findViewById(R.id.editText6);
        String user = userTxt.getText().toString();
        String password = paswordTxt.getText().toString();

        String pasword = encrypt(password);


        if (user.equals("") || password.equals("")){
            TextView textView = findViewById(R.id.textView7);
            textView.setVisibility(View.VISIBLE);
            textView.setText(R.string.invalid_auth);
        }
        else {
            getJSONObjectFromURL("http://209.97.184.103/authentication/" + (encrypt(user)) + "/" + (pasword)); // call method with return in method
        }
    }

    private void change_activity () {

        Intent intent = new Intent(this, LecturerDetails.class);
        startActivity(intent);
    }


    private void getJSONObjectFromURL(String urlString) throws java.lang.IllegalStateException {
        Context ctx = Authentication.this.getApplicationContext();
        RequestQueue queue = Volley.newRequestQueue(ctx); // ctx is the context
        //Volley requests
        StringRequest stringRequest = new StringRequest(Request.Method.GET, (urlString), new Response.Listener<String>() {
            public void onResponse(String response) {
                // Display the first 500 characters of the response string.
                System.out.println("Response is: " + response);                 // stack trace checker
                TextView textView = findViewById(R.id.textView7);
                if (response.equals("1")) {
                    change_activity();
                }
                else if (response.equals("0")){
                    textView.setVisibility(View.VISIBLE);
                    textView.setText(R.string.invalid_auth);
                }
                else {
                    textView.setText(response);                                    // fill text field with info
                }
            }
        }, new Response.ErrorListener() {                                   // error handling auto generated
            public void onErrorResponse(VolleyError error) {
                System.out.println("That didn't work!");
                TextView textView = findViewById(R.id.textView7);               // init new text view
                textView.setVisibility(View.VISIBLE);
                textView.setText(R.string.invalid_response);
            }
        });
        queue.add(stringRequest);                                                   // add to thread
    }
}
