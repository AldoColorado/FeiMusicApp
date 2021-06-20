package com.example.feimusic.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.0.8:4000/feimusic/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public static UserService getUserService(){
        UserService userService = getRetrofit().create(UserService.class);

        return userService;
    }

    public static ConsumidorService getConsumidorService(){
        ConsumidorService consumidorService = getRetrofit().create(ConsumidorService.class);

        return consumidorService;
    }

    public static CuentaService getCuentaService(){
        CuentaService cuentaService = getRetrofit().create(CuentaService.class);

        return cuentaService;
    }

    public static ArtistaService getArtistaService(){
        ArtistaService artistaService = getRetrofit().create(ArtistaService.class);

        return artistaService;

    }

    public static CancionService getCancionService(){
        CancionService cancionService = getRetrofit().create(CancionService.class);

        return cancionService;
    }
}
