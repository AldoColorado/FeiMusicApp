package com.example.feimusic.API;

import com.example.feimusic.Request.ArtistaRequest;
import com.example.feimusic.Request.CancionRequest;
import com.example.feimusic.Response.ArtistaResponse;
import com.example.feimusic.Response.CancionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CancionService {

    @POST("Cancion")
    Call<CancionResponse> cancionRegister(@Body CancionRequest cancionRequest);

    @GET("Cancion/{idCancion}")
    Call<CancionResponse> getCancion(@Path("idCancion") String idCancion);

}
