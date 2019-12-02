package com.example.myweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindUI();
    }

    @Override
    protected void onStart() {
        super.onStart();

        RequestQueue queue = Volley.newRequestQueue(this);
        //String url = "http://www.mocky.io/v2/5de4587c30000062009f7944";
        String url = "http://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=4c3a46a01c1d5ca1be88017ca3c91283";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText("Response is: " + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("That didn't work!");
                Log.e("Error: ", error.getMessage());
            }
        });

        queue.add(stringRequest);
    }

    public void bindUI() {
        textView = findViewById(R.id.tv1);
    }
}

/*
* Dudas:
* ¿Es correcto implementar el código para hacer el Request dentro del método onStart()?
* Tengo un dispositivo con Android 7.0 y otro con Android 9, en Android 7.0 funciona bien (solo no me deja usar los Log) y en el de Android 9 recibo la siguiente excepción: Cleartext HTTP traffic to api.openweathermap.org not permitted
* Para resolverlo tuve que agregar la siguiente validación al Manifest: android:usesCleartextTraffic="true" (voy a investigar más sobre esto)
* */