package com.example.vsvll.volleyjsonparse;

import android.support.v7.app.AppCompatActivity;
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
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.parse);


    }

    public void parse(View view) {
        String url = "https://api.myjson.com/bins/afjeo";

        //since our data is an json object we will request an json Object

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                //here we will create a function to get the response
                //The we need is Json Array

                try {
                    JSONArray jsonArray = response.getJSONArray("employees");
                    //Now since the data is in array we get easily parse the data by it id starting from 0.

                    for(int i=0; i<jsonArray.length();i++) {

                        JSONObject object = jsonArray.getJSONObject(i);
                        //now here "i" contains the dat by the index

                        String Name = object.getString("firstname");
                        int Age = object.getInt("age");
                        String Mail = object.getString("mail");
                        //we have stored the data in the above fields.

                        textView.append(Name+", "+String.valueOf(Age)+", "+Mail+"\n\n");

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                //here we it will show if there is any network issue

                error.printStackTrace();
            }
        }
        
        );

        requestQueue.add(jsonObjectRequest);
    }


}
