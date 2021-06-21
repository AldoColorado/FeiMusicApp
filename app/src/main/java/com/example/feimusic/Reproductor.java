package com.example.feimusic;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Reproductor extends AppCompatActivity {

    MediaPlayer mediaPlayer = new MediaPlayer();
    ImageView imagenPortada;
    ProgressBar progressBar;
    int PICK_SONG_REQUEST = 1;
    public String track;
    CancionResponse cancion;
    int repetir = 2, posicion = 0;
    MediaPlayer mPlayer = new MediaPlayer();
    Uri uri;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        imagenPortada = findViewById(R.id.portada);
        progressBar = findViewById(R.id.progressBar);

        //Uri uri = new Uri(myUri);

        //String myUri = "content://com.android.providers.downloads.documents/document/23";
        //MediaPlayer mediaPlayer = new MediaPlayer();
        //mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        //mediaPlayer.setDataSource(getApplicationContext(), myUri);
        //mediaPlayer.prepare();
        //mediaPlayer.start();


    }

    public void botonReproducir(View v) throws IOException {
        showFileChooserImagen();
    }


    private void showFileChooserImagen() {
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleciona la cancion"), PICK_SONG_REQUEST);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_SONG_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            byte[] bytearray = new byte[0];
            try {

                InputStream inputStream =
                        getContentResolver().openInputStream(filePath);
                Log.d("myuri", filePath.getPath());
                Log.d("uri", data.getDataString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void convertirAMp3(String bytesCadena){
        try{

            byte[] bytesArreglo = bytesCadena.getBytes("UTF_8");
            File archivo = File.createTempFile("mobile", "mp3", getCacheDir());
            archivo.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(archivo);
            fos.write(bytesArreglo);




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

            return null;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }
    }



}