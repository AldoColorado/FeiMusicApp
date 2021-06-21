package com.example.feimusic.Model;

public class Cancion {
    private int idCancion;
    private String nombreCancion;
    private String imagenCancion;

    public Cancion(int idCancion, String nombreCancion, String imagenCancion) {
        this.idCancion = idCancion;
        this.nombreCancion = nombreCancion;
        this.imagenCancion = imagenCancion;
    }

    public int getIdCancion() {
        return idCancion;
    }

    public void setIdCancion(int idCancion) {
        this.idCancion = idCancion;
    }

    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public String getImagenCancion() {
        return imagenCancion;
    }

    public void setImagenCancion(String imagenCancion) {
        this.imagenCancion = imagenCancion;
    }
}
