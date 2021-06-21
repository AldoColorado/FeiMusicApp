package com.example.feimusic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feimusic.Model.Cancion;
import com.example.feimusic.Model.SongLike;

import java.util.List;
import java.util.zip.Inflater;

public class AdaptarySongLike extends RecyclerView.Adapter<AdaptarySongLike.MyViewHolder> {
    private Context mContext;
    private List<SongLike> songLikeList;

    public void setSongLikeList(List<SongLike> songLikeList) {
        this.songLikeList = songLikeList;
    }

    public AdaptarySongLike(Context mContext, List<SongLike> songLikeList){
        this.mContext = mContext;
        this.songLikeList = songLikeList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.cancion_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.songName.setText(songLikeList.get(position).getCancion().getNombreCancion());
        Bitmap bitmapImg = StringToBitMap(songLikeList.get(position).getCancion().getImagenCancion());
        holder.imgViewSongLike.setImageBitmap(bitmapImg);
    }

    @Override
    public int getItemCount() {
        return songLikeList.size();
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

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView songName;
        ImageView imgViewSongLike;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            songName = itemView.findViewById(R.id.txtsongName);
            imgViewSongLike = itemView.findViewById(R.id.imgViewSongLike);
        }
    }
}
