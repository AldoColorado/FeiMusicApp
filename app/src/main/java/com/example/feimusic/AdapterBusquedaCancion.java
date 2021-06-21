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

import com.example.feimusic.Response.CancionResponse;

import java.util.List;

public class AdapterBusquedaCancion extends RecyclerView.Adapter<AdapterBusquedaCancion.MyViewHolder>{

    private Context mContext;
    private List<CancionResponse> cancionesResponseList;

    public void setCancionesResponseList(List<CancionResponse> cancionesResponseList) {
        this.cancionesResponseList = cancionesResponseList;
    }

    public AdapterBusquedaCancion(Context mContext, List<com.example.feimusic.Response.CancionResponse> cancionResponse) {
        this.mContext = mContext;
        this.cancionesResponseList = cancionResponse;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.songlike_list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.nombreCancion.setText(cancionesResponseList.get(position).getNombreCancion());
        //holder.img.setImageBitmap(StringToBitMap(cancionesResponseList.get(position).getImagenCancion()));

    }

    @Override
    public int getItemCount() {
        return cancionesResponseList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nombreCancion;
        ImageView img;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);

            nombreCancion = itemView.findViewById(R.id.txtsongName);
            img = itemView.findViewById(R.id.imgViewSongLike);
        }
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
