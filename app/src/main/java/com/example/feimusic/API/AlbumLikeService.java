package com.example.feimusic.API;

import com.example.feimusic.Model.AlbumLike;
import com.example.feimusic.Model.ArtistLike;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AlbumLikeService {
    @GET("Albumlike/{idConsumidor}")
    Call<List<AlbumLike>> getAlbumLike(@Path("idConsumidor") String idConsumidor);
}
