package com.example.feimusic.API;

import com.example.feimusic.Request.CuentaRequest;
import com.example.feimusic.Request.LoginRequest;
import com.example.feimusic.Response.CuentaReponse;
import com.example.feimusic.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CuentaService {
    @POST("Cuenta")
    Call<CuentaReponse> createCuenta(@Body CuentaRequest cuentaRequest);
}
