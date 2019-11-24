package com.example.casper;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {
    TextView tvResult;
    RequestQueue q;  // requesting quote from "firebase_url": "https://casper-631f2.firebaseio.com", in daily bases//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        tvResult= findViewById(R.id.textView);

            try {   // Here the quote requested from firebase will be posted to the text view//
                q = Volley.newRequestQueue(this);
                jsonParse();

            } catch (Exception e) {  // otherwise it will declare no internet in case of any disconnection//
                tvResult.setText("No Internet!");
            }

    }


    public void goToMustDo(View view){ // initializiing intent to enable the user to do navigation action
        Intent intent = new Intent(this,MustDoActivity.class);
        startActivity(intent);
    }

    public void onButtonClick(View v){ // Button (Go to the list of tasks) will enable the user to navigate to the MustDoActivity View
        Intent myIntent = new Intent(getBaseContext(),   MainActivity.class);
        startActivity(myIntent);
    }

    public void goToAdd(View view){ // intializing intent to Add button
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    void jsonParse(){
        String url = "http://quotes.rest/qod.json"; // Random quote from internet in Json format
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    JSONObject jsonObject = response.getJSONObject("contents");
                    JSONArray jsonArray = jsonObject.getJSONArray("quotes");

                        JSONObject emp = jsonArray.getJSONObject(0);
                        String fn = emp.getString("quote");  // will convert the text from json format to a string

                        tvResult.setText(fn); // will display the text in the text view in the start page
                   // }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        q.add(request);
    }
}
