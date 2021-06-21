package com.example.feimusic.API;

import com.example.feimusic.Model.ArtistLike;
import com.example.feimusic.Model.SongLike;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArtistLikeService {

    @GET("Artistlike/{idConsumidor}")
    Call<List<ArtistLike>> getArtistLike(@Path("idConsumidor") String idConsumidor);

}
