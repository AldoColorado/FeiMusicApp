package com.example.feimusic.API;

import com.example.feimusic.Model.SongLike;
import com.example.feimusic.Request.CancionRequest;
import com.example.feimusic.Request.SongLikeRequest;
import com.example.feimusic.Response.CancionResponse;
import com.example.feimusic.Response.SongLikeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SonglikeService {

    @POST("Songlike")
    Call<SongLikeResponse> songLikeCreate(@Body SongLikeRequest songLikeRequest);

    @GET("Songlike/{idConsumidor}")
    Call<List<SongLike>> getSongLike(@Path("idConsumidor") String idConsumidor);

}
