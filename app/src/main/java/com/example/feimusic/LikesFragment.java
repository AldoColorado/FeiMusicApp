package com.example.feimusic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.feimusic.API.ApiClient;
import com.example.feimusic.Model.Cancion;
import com.example.feimusic.Model.SongLike;
import com.example.feimusic.Request.SongLikeRequest;
import com.example.feimusic.Response.CancionResponse;
import com.example.feimusic.Response.SongLikeResponse;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LikesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LikesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private String mParam1;
    private String mParam2;
    private ListView listViewSongLike;
    private RecyclerView recyclerView;
    private List<SongLike> songLikeList = new ArrayList<>();
    AdaptarySongLike adaptarySongLike;
    private cancionAdapter adapter;

    public LikesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LikesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LikesFragment newInstance(String param1, String param2) {
        LikesFragment fragment = new LikesFragment();
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
        View view = inflater.inflate(R.layout.fragment_likes, container, false);
        /*listViewSongLike = view.findViewById(R.id.listViewSongLike);
        songLikeList = new ArrayList<>();
        adapter = new cancionAdapter(getContext(), songLikeList);
        listViewSongLike.setAdapter(adapter);*/

        recyclerView = view.findViewById(R.id.recyclerViewSongLike);

        LinearLayoutManager manager = new LinearLayoutManager(this.getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adaptarySongLike = new AdaptarySongLike(getActivity(), songLikeList);
        recyclerView.setAdapter(adaptarySongLike);

        getSongsLike();

        return view;
    }

    public void getSongsLike(){
        String idConsumidor = "1";

        Call<List<SongLike>> songLikeResponseCall = ApiClient.getSongLikeService().getSongLike(idConsumidor);
        songLikeResponseCall.enqueue(new Callback<List<SongLike>>() {
            @Override
            public void onResponse(Call<List<SongLike>> call, Response<List<SongLike>> response) {
                List<SongLike> songLikes = response.body();
                if (response.code() != 200){
                    Toast.makeText(getActivity(), "No trae nada", Toast.LENGTH_LONG).show();
                }else{
                    songLikeList.addAll(songLikes);
                }


                /*SongLikeResponse songLikeResponse = response.body();
                    songLikeList = new ArrayList<>(Arrays.asList(songLikeResponse.getCanciones()));
                    adaptarySongLike.setSongLikeList(songLikeList);*/


                    PutDataIntoRecyclerView(songLikeList);
            }

            @Override
            public void onFailure(Call<List<SongLike>> call, Throwable t) {

            }
        });
    }

    private void PutDataIntoRecyclerView(List<SongLike> songLikeList) {
        LinearLayoutManager manager = new LinearLayoutManager(this.getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adaptarySongLike.setSongLikeList(songLikeList);
        adaptarySongLike.notifyDataSetChanged();
        recyclerView.setAdapter(adaptarySongLike);
    }
}