package com.example.lado.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MessageAPI {
    @GET("employees/")
    Call<List<Message>> messages();

    @GET("employees/{id}")
    Call<Message> getUserById(@Path("id") Integer id);
}
