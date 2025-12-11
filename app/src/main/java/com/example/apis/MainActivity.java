package com.example.apis;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

//    private static final String OPEN_WEATHER_MAP_API_KEY = "a9eb93a39b87685a601aaee360dc58f1";
    private static final String API = "a9eb93a39b87685a601aaee360dc58f1";
    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.temp_txt);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = String.format("https://api.openweathermap.org/data/2.5/weather?q=belfast,uk&units=metric&APPID=a9eb93a39b87685a601aaee360dc58f1");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject main = response.getJSONObject("main");
                            String temp = String.valueOf(main.getDouble("temp"));
                            textView.setText("Temp: " + temp + "C");
                            Toast.makeText(MainActivity.this, main.toString(), Toast.LENGTH_SHORT).show();
                        } catch (JSONException e){
                            throw new RuntimeException(e);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(request);
    }
}