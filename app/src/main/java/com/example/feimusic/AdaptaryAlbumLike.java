package com.example.feimusic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.feimusic.Model.AlbumLike;

import java.util.List;

public class AdaptaryAlbumLike extends RecyclerView.Adapter<AdaptaryAlbumLike.MyViewHolder>{
    private Context mContext;
    private List<AlbumLike> albumLikeList;

    public AdaptaryAlbumLike(Context context, List<AlbumLike> albumLikeList) {
        this.mContext = context;
        this.albumLikeList = albumLikeList;
    }

    public void setAlbumLikeList(List<AlbumLike> albumLikeList) { this.albumLikeList = albumLikeList; }

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
        holder.albumName.setText(albumLikeList.get(position).getAlbum().getNombreAlbum());
    }

    @Override
    public int getItemCount() {
        return albumLikeList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView albumName;
        ImageView imgViewSongLike;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            albumName = itemView.findViewById(R.id.txtsongName);
            //imgViewSongLike = itemView.findViewById(R.id.imgViewSongLike);
        }
    }
}
