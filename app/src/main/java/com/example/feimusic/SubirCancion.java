package com.example.feimusic;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feimusic.API.ApiClient;
import com.example.feimusic.API.CancionService;
import com.example.feimusic.Request.CancionRequest;
import com.example.feimusic.Response.CancionResponse;
import com.google.android.material.textfield.TextInputEditText;


import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubirCancion extends AppCompatActivity {

    Button btnSeleccionarCancion, btnSeleccionarImagen, btnSubirCancion;
    TextView nombreCancion;
    TextInputEditText nombreCanciontxt;
    ImageView imageView;
    int PICK_SONG_REQUEST = 1;
    int PICK_IMAGE_REQUEST = 2;
    String cancionbytes;
    String imagenCancion = "";
    Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cancion);

        btnSeleccionarCancion = findViewById(R.id.btnTrackCancion);
        btnSeleccionarImagen = findViewById(R.id.btnImagenCancion);
        btnSubirCancion = findViewById(R.id.btnSubirCancion);
        nombreCancion = findViewById(R.id.nombreArchivoCancion);
        imageView = findViewById(R.id.viewImagenCancion);
        nombreCanciontxt = findViewById(R.id.nombreCancionTxt);


        btnSeleccionarCancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        btnSeleccionarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooserImagen();
            }
        });

        btnSubirCancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirCancion();
            }
        });

    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("audio/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleciona la cancion"), PICK_SONG_REQUEST);
    }

    private void showFileChooserImagen() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleciona la cancion"), PICK_IMAGE_REQUEST);
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

                    Toast.makeText(SubirCancion.this,filePath.toString(), Toast.LENGTH_LONG).show();
                    Log.d("Mytag", filePath.getPath());
                    //cancionbytes = Base64.encodeToString(bytes, 0);

                    bytearray = new byte[inputStream.available()];
                    bytearray = toByteArray(inputStream);
                    //byte[] bytesBaseB4 = Base64.encode(bytearray,Base64.NO_WRAP);
                    cancionbytes = Base64.encodeToString(bytearray,0);
                    //cancionbytes = bytesToHex(bytearray);
                    //cancionbytes = new String(bytesBaseB4, StandardCharsets.UTF_8);
                    setFileName(filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            //cancionbytes = bytesToHex(bytearray);
        }

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                // Obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // Configuración del mapa de bits en ImageView
                imagenCancion = getStringImagen(bitmap);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int read = 0;
        byte[] buffer = new byte[1024];
        while (read != -1) {
            read = in.read(buffer);
            if (read != -1)
                out.write(buffer,0,read);
        }
        out.close();
        return out.toByteArray();
    }

    public void setFileName(Uri uri) {
        String nombreArchivo = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    nombreArchivo = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (nombreArchivo == null) {
            nombreArchivo = uri.getPath();
            int cut = nombreArchivo.lastIndexOf('/');
            if (cut != -1) {
                nombreArchivo = nombreArchivo.substring(cut + 1);
            }
        }
        nombreCancion.setText(nombreArchivo);
    }

    public String getStringImagen(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void subirCancion(){
        CancionRequest cancionRequest = new CancionRequest();
        cancionRequest.setIdCancion("0");
        cancionRequest.setNombreCancion(nombreCanciontxt.getText().toString());
        cancionRequest.setImagenCancion(imagenCancion);
        cancionRequest.setTrack(cancionbytes);
        Call<CancionResponse> cancionResponseCall = ApiClient.getCancionService().
                cancionRegister(cancionRequest);
        cancionResponseCall.enqueue(new Callback<CancionResponse>() {
            @Override
            public void onResponse(Call<CancionResponse> call, Response<CancionResponse> response) {
                if(response.body().getIdCancion() != null){
                    Toast.makeText(SubirCancion.this,"Cancion subida exitosamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SubirCancion.this, MenuPrincipalArtista.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CancionResponse> call, Throwable t) {
                Toast.makeText(SubirCancion.this,"Fallo al subir la cancion", Toast.LENGTH_LONG).show();
            }
        });
    }

    public static String bytesToHex(byte[] in) {
        final StringBuilder builder = new StringBuilder();
        for(byte b : in) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
}
