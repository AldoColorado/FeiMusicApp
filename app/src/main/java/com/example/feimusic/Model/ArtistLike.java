package com.example.feimusic.Model;

import com.example.feimusic.Response.ArtistaResponse;
import com.google.gson.annotations.SerializedName;

public class ArtistLike {

    private int idArtista;

    @SerializedName("Artistum")
    private Artista artista;

    public ArtistLike(int idArtista, Artista artista) {
        this.idArtista = idArtista;
        this.artista = artista;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }
}
