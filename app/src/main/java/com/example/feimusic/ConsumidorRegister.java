package com.example.feimusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.feimusic.API.ApiClient;
import com.example.feimusic.Request.ConsumidorRegisterRequest;
import com.example.feimusic.Request.CuentaRequest;
import com.example.feimusic.Response.ConsumidorRegisterResponse;
import com.example.feimusic.Response.CuentaReponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConsumidorRegister extends AppCompatActivity {
    private Session session;//global variable
    ConsumidorRegisterRequest consumidorRegisterRequest = new ConsumidorRegisterRequest();
    TextInputEditText nombre, apellidos, username, correo, password;
    Button btnRegistrarArtista, btnRegistrarse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        session = new Session(ConsumidorRegister.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumidor_register);

        username = findViewById(R.id.userNameTxt);
        correo = findViewById(R.id.correoTxt);
        password = findViewById(R.id.passwordTxt);
        nombre = findViewById(R.id.nombretxt);
        apellidos = findViewById(R.id.apellidosTxt);
        btnRegistrarArtista = findViewById(R.id.btnIrFormularioRegistroArtista);
        btnRegistrarse = findViewById(R.id.btnRegistrarse);




        btnRegistrarArtista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsumidorRegister.this, ArtistaRegister.class);
                startActivity(intent);
            }
        });

        btnRegistrarse.setOnClickListener(new View.OnClickListener() {
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
        cuentaRequest.setTipoUsuario("Consumidor");

        Call<CuentaReponse> cuentaReponseCall = ApiClient.getCuentaService()
                .createCuenta(cuentaRequest);
        cuentaReponseCall.enqueue(new Callback<CuentaReponse>() {
            @Override
            public void onResponse(Call<CuentaReponse> call, Response<CuentaReponse> response) {
                if(response.body().getUsername() !=null){
                    consumidorRegisterRequest.setUsername(response.body().getUsername());
                    consumidorRegister();
                }
            }

            @Override
            public void onFailure(Call<CuentaReponse> call, Throwable t) {
                Toast.makeText(ConsumidorRegister.this, "Fallo al crear la cuenta", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void consumidorRegister() {
        consumidorRegisterRequest.setIdConsumidor("0");
        consumidorRegisterRequest.setNombre(nombre.getText().toString());
        consumidorRegisterRequest.setApellidos(apellidos.getText().toString());

        Call<ConsumidorRegisterResponse> consumidorRegisterResponseCall = ApiClient.getConsumidorService()
                .consumidorRegister(consumidorRegisterRequest);
        consumidorRegisterResponseCall.enqueue(new Callback<ConsumidorRegisterResponse>() {

            @Override
            public void onResponse(Call<ConsumidorRegisterResponse> call, Response<ConsumidorRegisterResponse> response) {
                if (response.body().getNombre() != null) {
                    Intent intent = new Intent(ConsumidorRegister.this, Login.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<ConsumidorRegisterResponse> call, Throwable t) {
                Toast.makeText(ConsumidorRegister.this, "fallo al crear el consumidor", Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

}