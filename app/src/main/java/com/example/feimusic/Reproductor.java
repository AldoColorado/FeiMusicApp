package com.example.feimusic;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

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

    MediaPlayer mediaPlayer = new MediaPlayer();
    ImageView imagenPortada;
    ProgressBar progressBar;
    public String track;
    CancionResponse cancion;
    int repetir = 2, posicion = 0;
    MediaPlayer vectorMediaPlayer [];   // Para guardar las canciones en un vector


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        imagenPortada = findViewById(R.id.portada);
        progressBar = findViewById(R.id.progressBar);

        //Call<CancionResponse> response = ApiClient.getCancionService().getCancion("1");

        new Task1().execute();
        //obtenerCancion();
    }

    public void ReproducirCancion(View view){
        //obtenerCancion();
        //imagenPortada.setImageBitmap();
        convertirAMp3(cancion.getTrack().toString());
        String song = track;
        int i = 1;
    }

    public void obtenerCancion(String idCancion){

        Call<CancionResponse> cancionResponseCall = ApiClient.getCancionService().getCancion(idCancion);

        try{
            Response<CancionResponse> response = cancionResponseCall.execute();
            cancion = response.body();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }



    public void convertirAMp3(String bytesCadena){
        try{
            byte[] bytesArreglo = bytesCadena.getBytes("UTF_8");
            File archivo = File.createTempFile("mobile", "mp3", getCacheDir());
            archivo.deleteOnExit();
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


    class Task1 extends AsyncTask<Void, Void, Void>{
        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.INVISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            obtenerCancion("1");
            return null;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }


    }



}