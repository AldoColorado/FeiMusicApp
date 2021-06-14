package com.example.feimusic.API;

import com.example.feimusic.Request.LoginRequest;
import com.example.feimusic.Response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("Login")
    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
