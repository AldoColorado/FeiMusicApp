package com.example.feimusic;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setToken(String token) {
        prefs.edit().putString("Token", token).commit();
    }

    public String getToken() {
        String token = prefs.getString("Token","");
        return token;
    }
}

