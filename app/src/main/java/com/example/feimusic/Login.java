package com.example.feimusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.feimusic.API.ApiClient;
import com.example.feimusic.Request.ArtistaRequest;
import com.example.feimusic.Request.LoginRequest;
import com.example.feimusic.Response.ArtistaResponse;
import com.example.feimusic.Response.ConsumidorRegisterResponse;
import com.example.feimusic.Response.LoginResponse;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private Session session;

    TextInputEditText username, password;
    Button btnLogin, btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.userNameTxt);
        password = findViewById(R.id.passwordTxt);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnIrFormularioRegistro);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(Login.this,"Username / Password Required", Toast.LENGTH_LONG).show();
                }else{
                    //proceed to login
                    login();
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ConsumidorRegister.class);
                startActivity(intent);
            }
        });
        
    }

    public void login(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username.getText().toString());
        loginRequest.setPassword(password.getText().toString());

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.body().getStatus().equals("Success")){
                    session = new Session(Login.this);
                    session.setToken(response.body().getToken());
                    Toast.makeText(Login.this,"Login success", Toast.LENGTH_LONG).show();
                    buscarConsumidor(response.body().getUsername());
                    buscarArtista(response.body().getUsername());
                }else {
                    Toast.makeText(Login.this,"Login Failed", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void abrirMenuPrincipalArtista() {
        Intent intent = new Intent(Login.this, MenuPrincipalArtista.class);
        startActivity(intent);
    }

    public void abrirMenuPrincipalConsumidor(){
        Intent intent = new Intent(Login.this, MenuPrincipalConsumidor.class);
        startActivity(intent);
    }

    public void buscarConsumidor(String username){
        Call<ConsumidorRegisterResponse> getConsumidorResponse = ApiClient.getConsumidorService().buscarConsumidor(username);
        getConsumidorResponse.enqueue(new Callback<ConsumidorRegisterResponse>() {
            @Override
            public void onResponse(Call<ConsumidorRegisterResponse> call, Response<ConsumidorRegisterResponse> response) {
                if(response.body().getStatus().equals("Success")){
                   //abrirMenuPrincipalConsumidor();
                    Intent intent = new Intent(Login.this, MainMenu.class);
                    startActivity(intent);

                    /*Intent intent = new Intent(Login.this, Reproductor.class);
                    startActivity(intent);*/
                }
            }
            @Override
            public void onFailure(Call<ConsumidorRegisterResponse> call, Throwable t) {
            }
        });
    }

    public void buscarArtista(String username){

        Call<ArtistaResponse> getConsumidorResponse = ApiClient.getArtistaService().buscarArtista(username);
        getConsumidorResponse.enqueue(new Callback<ArtistaResponse>() {
            @Override
            public void onResponse(Call<ArtistaResponse> call, Response<ArtistaResponse> response) {
                if(response.body().getStatus().equals("Success")){
                    abrirMenuPrincipalArtista();
                }
            }

            @Override
            public void onFailure(Call<ArtistaResponse> call, Throwable t) {

            }
        });
    }

}