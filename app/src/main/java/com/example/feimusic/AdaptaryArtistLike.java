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

import com.example.feimusic.Model.ArtistLike;
import com.example.feimusic.Model.SongLike;
import com.example.feimusic.Response.ArtistaResponse;

import java.util.List;

public class AdaptaryArtistLike extends RecyclerView.Adapter<AdaptaryArtistLike.MyViewHolder> {
    private Context mContext;
    private List<ArtistLike> artistLikeList;

    public void setArtistLikeList(List<ArtistLike> artistLikeListt) {
        this.artistLikeList = artistLikeListt;
    }

    public AdaptaryArtistLike(Context mContext, List<ArtistLike> artistLikeListt) {
        this.mContext = mContext;
        this.artistLikeList = artistLikeListt;
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
        holder.artistName.setText(artistLikeList.get(position).getArtista().getNombreArtista());
        //Bitmap bitmapImg = StringToBitMap(artistLikeListt.get(position).getCancion().getImagenCancion());
        //holder.imgViewSongLike.setImageBitmap(bitmapImg);
    }

    @Override
    public int getItemCount() {
        return artistLikeList.size();
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView artistName;
        ImageView imgViewSongLike;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            artistName = itemView.findViewById(R.id.txtsongName);
            //imgViewSongLike = itemView.findViewById(R.id.imgViewSongLike);
        }
    }

}