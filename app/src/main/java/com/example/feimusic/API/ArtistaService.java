package com.example.feimusic.API;

import com.example.feimusic.Request.ArtistaRequest;
import com.example.feimusic.Request.ConsumidorRegisterRequest;
import com.example.feimusic.Response.ArtistaResponse;
import com.example.feimusic.Response.ConsumidorRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ArtistaService {

    @POST("Artista")
    Call<ArtistaResponse> artistaRegister(@Body ArtistaRequest artistaRequestRequest);
}
