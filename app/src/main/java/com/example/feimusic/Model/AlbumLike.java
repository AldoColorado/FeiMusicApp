package com.example.feimusic.Model;

import com.google.gson.annotations.SerializedName;

public class AlbumLike {
    private int idAlbum;
    private int idConsumidor;

    @SerializedName("Album")
    private Album album;

    public AlbumLike(int idAlbum, int idConsumidor, Album album) {
        this.idAlbum = idAlbum;
        this.idConsumidor = idConsumidor;
        this.album = album;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public int getIdConsumidor() {
        return idConsumidor;
    }

    public void setIdConsumidor(int idConsumidor) {
        this.idConsumidor = idConsumidor;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
