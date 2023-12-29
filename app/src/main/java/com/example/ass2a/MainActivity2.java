package com.example.ass2a;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity2 extends AppCompatActivity {
    private RequestQueue queue;
    private ListView lstTodos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        queue = Volley.newRequestQueue(this);
        lstTodos = findViewById(R.id.lstTodos);

    }

    public void btn_OnClick(View view) {

        String url = "https://jsonplaceholder.typicode.com/comments";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, this::onResponse, error -> Log.d("volley_error", error.toString()));

        queue.add(request);


    }

    private void onResponse(JSONArray response) {
        ArrayList<String> todos = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            try {
                JSONObject obj = response.getJSONObject(i);
                todos.add(obj.getString("body"));
            } catch (JSONException exception) {
                Log.d("volley_error", exception.toString());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity2.this,
                android.R.layout.simple_list_item_1, todos);
        lstTodos.setAdapter(adapter);
    }
}