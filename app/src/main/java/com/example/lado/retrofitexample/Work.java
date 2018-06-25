package com.example.lado.retrofitexample;

import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Work {
    private static String URL = "http://YOUR_HOST:3000/";

    public void start() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MessageAPI messageAPI = retrofit.create(MessageAPI.class);

        Call<Message> messageCall = messageAPI.getUserById(1);
        messageCall.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Message post = response.body();
                if(response.isSuccessful()) {
                    Log.d("INFO", "Name " + post.getFirstName() + " " + post.getEmail());
                }
                else
                {
                    Log.d("CODE1", "CODE IS " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d("FAIL1", "Fail" + t);
            }
        });

        Call<List<Message>> call = messageAPI.messages();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if(response.isSuccessful())
                {
                    Log.d("SIZE", "SIZE IS " + response.body().size());
                }
                else
                {
                    Log.d("CODE2", "CODE IS " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                Log.d("FAIL2", "Fail" + t);
            }
        });
    }
}
