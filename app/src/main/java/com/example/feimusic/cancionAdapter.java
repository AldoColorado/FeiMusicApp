package com.example.feimusic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.feimusic.Model.SongLike;
import com.example.feimusic.Response.CancionResponse;

import java.util.ArrayList;
import java.util.List;

public class cancionAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SongLike> songLikeList;

    public cancionAdapter(Context context, ArrayList<SongLike> songLikeList) {
        this.context = context;
        this.songLikeList = songLikeList;
    }

    @Override
    public int getCount() {
        return songLikeList.size();
    }

    @Override
    public Object getItem(int position) {
        return songLikeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SongLike song = (SongLike) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.cancion_list_item, null);
        //ImageView imgViewSongLike = convertView.findViewById(R.id.imgViewSongLike);
        TextView txtSongName = convertView.findViewById(R.id.txtsongName);
        //Bitmap imagen = StringToBitMap(song.getCancion().getImagenCancion());

        //imgViewSongLike.setImageBitmap(imagen);
        txtSongName.setText(song.getCancion().getNombreCancion());

        return convertView;
    }

    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }
        catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
