package com.example.feimusic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.feimusic.API.ApiClient;
import com.example.feimusic.Model.AlbumLike;
import com.example.feimusic.Model.ArtistLike;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlbumsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlbumsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;

    private List<AlbumLike> albumLikeList = new ArrayList<>();
    AdaptaryAlbumLike adaptaryAlbumLike;

    public AlbumsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlbumsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlbumsFragment newInstance(String param1, String param2) {
        AlbumsFragment fragment = new AlbumsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_albums, container, false);
        recyclerView = view.findViewById(R.id.ListaAlbums);

        LinearLayoutManager manager = new LinearLayoutManager(this.getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adaptaryAlbumLike = new AdaptaryAlbumLike(this.getActivity(), albumLikeList);
        recyclerView.setAdapter(adaptaryAlbumLike);

        getAlbumLike();

        return view;
    }

    public void getAlbumLike(){
        String idConsumidor = "1";
        Call<List<AlbumLike>> listCall = ApiClient.getAlbumLikeService().getAlbumLike(idConsumidor);
        listCall.enqueue(new Callback<List<AlbumLike>>() {
            @Override
            public void onResponse(Call<List<AlbumLike>> call, Response<List<AlbumLike>> response) {
                List<AlbumLike> listaAlbumLike = response.body();

                if (response.code() != 200){
                    Toast.makeText(getActivity(), "Error de conexionr", Toast.LENGTH_LONG).show();
                }else{
                    albumLikeList.addAll(listaAlbumLike);
                }

                PutDataIntoRecyclerView(albumLikeList);
            }

            @Override
            public void onFailure(Call<List<AlbumLike>> call, Throwable t) {

            }
        });
    }

    private void PutDataIntoRecyclerView(List<AlbumLike> albumLikeList) {
        LinearLayoutManager manager = new LinearLayoutManager(this.getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adaptaryAlbumLike.setAlbumLikeList(albumLikeList);
        adaptaryAlbumLike.notifyDataSetChanged();
        recyclerView.setAdapter(adaptaryAlbumLike);
    }
}