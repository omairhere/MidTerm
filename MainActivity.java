package com.example.midterm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private News news ;
    private ArrayList<News> newsmodel;
    ListView listView;
    String news_id;
    String img_url;
    String desc;
    String head;


    String url = "https://alasartothepoint.alasartechnologies.com/listItem.php?id=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.news_view);
        newsmodel = new ArrayList<>();

        try {
            run();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String img ="https://reqres.in/img/faces/2-image.jpg";
        String head=this.head;


    }

    void run() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                final String myResponse = response.body().string();

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {


                        try {

                            JSONObject jobj = new JSONObject(myResponse);
                            JSONArray jsonArray = jobj.getJSONArray("data");
                            for(int i=0;i<jsonArray.length();i++){
                                JSONObject jObject = jsonArray.getJSONObject(i);
                                news_id= jObject.getString("id");
                                img_url = jObject.getString("url");
                                head = jObject.getString("heading");
                                desc = jObject.getString("description");
                                String ref = jObject.getString("reference");
                                news = new News(Integer.parseInt(news_id),img_url,desc,head,ref);
                                newsmodel.add(news);

                            }
                            NewsAdapter adapter = new NewsAdapter(newsmodel,getApplicationContext());
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

            }
        });
    }
}