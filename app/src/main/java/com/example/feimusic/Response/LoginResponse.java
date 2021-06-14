package com.example.feimusic.Response;

public class LoginResponse {

    private String username;
    private String correo;
    private String authToken;
    private String status;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correy) {
        this.correo = correo;
    }

    public String getToken() {
        return authToken;
    }

    public void setToken(String authToken) {
        this.authToken = authToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
