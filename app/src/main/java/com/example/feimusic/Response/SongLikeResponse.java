package com.example.feimusic.Response;

import com.example.feimusic.Model.SongLike;

public class SongLikeResponse {
    private SongLike[] canciones;

    public SongLike[] getCanciones() {
        return canciones;
    }

    public void setCanciones(SongLike[] canciones) {
        this.canciones = canciones;
    }
}
