package com.example.http;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView tvresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvresult = findViewById(R.id.tvShow);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MainActivity.this.startActivity(new Intent((Context)MainActivity.this, bai1.class));
                MainActivity.this.finish();
            }
        },5000);

    }
    private void getPosts(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {

                if(!response.isSuccessful()){
                    tvresult.setText("Codigo: "+response.code());
                    return;
                }

                List<Post> postsList = response.body();

                for(Post post: postsList){

                    String content = "";
                    content += "userId:"+ post.getUserId() + "\n";
                    content += "id:"+ post.getId() + "\n";
                    content += "title:"+ post.getTitle() + "\n";
                    content += "completed:"+ post.getCompleted() + "\n\n";

                    tvresult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                tvresult.setText(t.getMessage());

            }

        });

    }
}
