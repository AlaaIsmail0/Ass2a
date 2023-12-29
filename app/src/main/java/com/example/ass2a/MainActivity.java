package com.example.ass2a;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RequestQueue queue;
    private ListView lstTodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);
        lstTodos = findViewById(R.id.lstTodos);
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(v -> openMainActivity2());
    }

    public void openMainActivity2() {
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        startActivity(intent);
    }

    public void btn_OnClick(View view) {
        String url = "https://jsonplaceholder.typicode.com/photos";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url,
                null, response -> {
                    ArrayList<Photo> photoList = new ArrayList<>();
                    for (int i = 0; i < response.length(); i++) {
                        try {
                            JSONObject obj = response.getJSONObject(i);

                            // Extract data from JSON and create a Photo object
                            Photo photo = new Photo();
                            photo.setAlbumId(obj.getInt("albumId"));
                            photo.setId(obj.getInt("id"));
                            photo.setTitle(obj.getString("title"));
                            photo.setUrl(obj.getString("url"));
                            photo.setThumbnailUrl(obj.getString("thumbnailUrl"));

                            // Add the Photo object to the list
                            photoList.add(photo);
                        } catch (JSONException exception) {
                            Log.d("volley_error", exception.toString());
                        }
                    }

                    // Create an adapter for your custom object
                    PhotoAdapter adapter = new PhotoAdapter(MainActivity.this, photoList);
                    lstTodos.setAdapter(adapter);
                }, error -> Log.d("volley_error", error.toString()));

        queue.add(request);
    }
}

