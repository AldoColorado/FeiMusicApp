package com.example.feimusic.API;

import com.example.feimusic.Response.GeneroResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface GeneroService {
    @GET("Genero")
    Call<List<GeneroResponse>> getGenero(@Body GeneroResponse generoResponse);
}
