package com.example.feimusic.Response;

import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;

public class CancionResponse {

    private String idCancion;
    private String nombreCancion;
    private String letra;
    private JSONObject imagenCancion;
    private JSONObject track;


    public String getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(String idCancion) {
        this.idCancion = idCancion;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public JSONObject getImagenCancion() {
        return imagenCancion;
    }

    public void setImagenCancion(JSONObject imagenCancion) {
        this.imagenCancion = imagenCancion;
    }

    public JSONObject getTrack() {
        return track;
    }

    public void setTrack(JSONObject track) {
        this.track = track;
    }
}
