package com.example.lado.retrofitexample;

import com.google.gson.JsonElement;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MessageAPI {
    @GET("employees/")
    Call<List<Message>> messages();

    @GET("employees/{id}")
    Call<Message> getUserById(@Path("id") Integer id);

    @GET("employees/")
    Call<Message> message();

    @GET("employees/")
    Call<JsonElement> json();

    @POST("employees/")
    Call<Message> setNewUser(@Body Message m);
}
