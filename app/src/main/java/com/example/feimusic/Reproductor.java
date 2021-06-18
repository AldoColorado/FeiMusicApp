package com.example.feimusic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.feimusic.API.ApiClient;
import com.example.feimusic.Response.CancionResponse;
import com.example.feimusic.Response.ConsumidorRegisterResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Reproductor extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    ImageView imagenPortada;
    public String track;
    int repetir = 2, posicion = 0;
    MediaPlayer vectorMediaPlayer [];   // Para guardar las canciones en un vector


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        obtenerCancion();


    }

    public void ReproducirCancion(View view){
        convertirAMp3(track);
    }

    public void obtenerCancion(){
        Call<CancionResponse> cancionResponseCall = ApiClient.getCancionService().getCancion("1");
        cancionResponseCall.enqueue(new Callback<CancionResponse>() {
            @Override
            public void onResponse(Call<CancionResponse> call, Response<CancionResponse> response) {
                if(response.body().getIdCancion() != null){
                    track = response.body().getTrack();
                }
            }

            @Override
            public void onFailure(Call<CancionResponse> call, Throwable t) {

            }
        });

    }



    public void convertirAMp3(String bytesCadena){
        try{
            byte[] bytesArreglo = bytesCadena.getBytes("UTF_8");
            File archivo = File.createTempFile("mobile", "mp3", getCacheDir());
            //archivo.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(archivo);
            fos.write(bytesArreglo);

            mediaPlayer.reset();
            FileInputStream fis = new FileInputStream(archivo);
            mediaPlayer.setDataSource(fis.getFD());
            mediaPlayer.prepare();
            mediaPlayer.start();


        } catch (IOException exception){
            String s = exception.toString();
            exception.printStackTrace();
        }

    }
}