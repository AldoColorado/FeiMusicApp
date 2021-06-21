package com.example.feimusic.Model;

import com.google.gson.annotations.SerializedName;

public class SongLike {
    private int idCancion;
    private int idConsumidor;

    @SerializedName("Cancion")
    private Cancion cancion;

    public SongLike(int idCancion, int idConsumidor, Cancion cancion) {
        this.idCancion = idCancion;
        this.idConsumidor = idConsumidor;
        this.cancion = cancion;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public int getIdConsumidor() {
        return idConsumidor;
    }

    public void setIdConsumidor(int idConsumidor) {
        this.idConsumidor = idConsumidor;
    }

    public Cancion getCancion() {
        return cancion;
    }

    public void setCancion(Cancion cancion) {
        this.cancion = cancion;
    }
}
