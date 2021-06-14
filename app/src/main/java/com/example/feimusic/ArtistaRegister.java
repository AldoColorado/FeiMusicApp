package com.example.feimusic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.feimusic.API.ApiClient;
import com.example.feimusic.Request.ArtistaRequest;
import com.example.feimusic.Request.CuentaRequest;
import com.example.feimusic.Response.ArtistaResponse;
import com.example.feimusic.Response.ConsumidorRegisterResponse;
import com.example.feimusic.Response.CuentaReponse;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtistaRegister extends AppCompatActivity {
    Button btnElegirImagen, btnRegistrar;
    ImageView imageView;
    TextInputEditText username, correo, nombreArtista,
    descripcion, password;
    String imagenArtista = "";
    ArtistaRequest artistaRequest = new ArtistaRequest();

    int PICK_IMAGE_REQUEST = 1;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artista_register);

        username = findViewById(R.id.userNameTxt);
        correo = findViewById(R.id.correoTxt);
        nombreArtista = findViewById(R.id.nombreArtistatxt);
        descripcion = findViewById(R.id.descripcionTxt);
        password = findViewById(R.id.passwordTxt);
        btnElegirImagen = findViewById(R.id.btnElegirImagen);
        btnRegistrar = findViewById(R.id.btnRegistrarse);
        imageView = findViewById(R.id.imagenArtista);

        btnElegirImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cuentaRegister();
            }
        });

    }

    public void cuentaRegister() {
        CuentaRequest cuentaRequest = new CuentaRequest();
        cuentaRequest.setUsername(username.getText().toString());
        cuentaRequest.setCorreo(correo.getText().toString());
        cuentaRequest.setPassword(password.getText().toString());

        Call<CuentaReponse> cuentaReponseCall = ApiClient.getCuentaService()
                .createCuenta(cuentaRequest);
        cuentaReponseCall.enqueue(new Callback<CuentaReponse>() {
            @Override
            public void onResponse(Call<CuentaReponse> call, Response<CuentaReponse> response) {
                if(response.body().getUsername() !=null){
                    artistaRequest.setUsername(response.body().getUsername());
                    artistaRegister();
                }
            }

            @Override
            public void onFailure(Call<CuentaReponse> call, Throwable t) {
                Toast.makeText(ArtistaRegister.this, "Fallo al crear la cuenta", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void artistaRegister() {
        artistaRequest.setIdArtista("0");
        artistaRequest.setNombreArtista(nombreArtista.getText().toString());
        artistaRequest.setDescripcion(descripcion.getText().toString());
        artistaRequest.setImagenArtista(imagenArtista);
        artistaRequest.setIdGenero("1");

        Call<ArtistaResponse> artistaResponseCall = ApiClient.getArtistaService()
                .artistaRegister(artistaRequest);
        artistaResponseCall.enqueue(new Callback<ArtistaResponse>() {

            @Override
            public void onResponse(Call<ArtistaResponse> call, Response<ArtistaResponse> response) {
                if (response.body().getNombreArtista() != null) {
                    Toast.makeText(ArtistaRegister.this, response.body().getNombreArtista(), Toast.LENGTH_LONG)
                            .show();
                    Intent intent = new Intent(ArtistaRegister.this, MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ArtistaResponse> call, Throwable t) {
                Toast.makeText(ArtistaRegister.this, "fallo al crear el consumidor", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public String getStringImagen(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void showFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Seleciona imagen"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                // Obtener el mapa de bits de la Galería
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                // Configuración del mapa de bits en ImageView

                imageView.setImageBitmap(bitmap);
                imagenArtista = getStringImagen(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}