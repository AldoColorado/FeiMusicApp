package com.example.feimusic.API;

import com.example.feimusic.ConsumidorRegister;
import com.example.feimusic.Request.ConsumidorRegisterRequest;
import com.example.feimusic.Request.LoginRequest;
import com.example.feimusic.Response.ConsumidorRegisterResponse;
import com.example.feimusic.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConsumidorService {
    @POST("Consumidor")
    Call<ConsumidorRegisterResponse> consumidorRegister(@Body ConsumidorRegisterRequest consumidorRegisterRequest);

    @GET("Consumidor/{username}")
    Call<ConsumidorRegisterResponse> buscarConsumidor(@Path("username") String username);
}
