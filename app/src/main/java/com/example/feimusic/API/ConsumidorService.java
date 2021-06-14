package com.example.feimusic.API;

import com.example.feimusic.Request.ConsumidorRegisterRequest;
import com.example.feimusic.Request.LoginRequest;
import com.example.feimusic.Response.ConsumidorRegisterResponse;
import com.example.feimusic.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ConsumidorService {
    @POST("/Consumidor")
    Call<ConsumidorRegisterResponse> consumidorRegister(@Body ConsumidorRegisterRequest consumidorRegisterRequest);
}
