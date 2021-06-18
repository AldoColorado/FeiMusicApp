package com.example.feimusic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuPrincipalArtista extends AppCompatActivity {
    private Session session;
    Button btnSubirCancion, btnSubirAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_artista);
        session = new Session(MenuPrincipalArtista.this);
        btnSubirCancion = findViewById(R.id.btnSubirCancion);
        btnSubirAlbum = findViewById(R.id.btnSubirAlbum);


        btnSubirCancion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalArtista.this, SubirCancion.class);
                startActivity(intent);
            }
        });

        btnSubirAlbum.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPrincipalArtista.this, SubirAlbum.class);
                startActivity(intent);
            }
        }));



    }
}