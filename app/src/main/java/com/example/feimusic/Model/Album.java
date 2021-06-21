package com.example.feimusic.Model;

import java.util.Date;

public class Album {
    private int idAlbum;
    private String nombreAlbum;
    private Date anioLanzamiento;
    private String imagenAlbum;
    private int idArtista;

    public Album(int idAlbum, String nombreAlbum, Date anioLanzamiento, String imagenAlbum, int idArtista) {
        this.idAlbum = idAlbum;
        this.nombreAlbum = nombreAlbum;
        this.anioLanzamiento = anioLanzamiento;
        this.imagenAlbum = imagenAlbum;
        this.idArtista = idArtista;
    }

    public int getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(int idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    public Date getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(Date anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public String getImagenAlbum() {
        return imagenAlbum;
    }

    public void setImagenAlbum(String imagenAlbum) {
        this.imagenAlbum = imagenAlbum;
    }

    public int getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(int idArtista) {
        this.idArtista = idArtista;
    }
}
