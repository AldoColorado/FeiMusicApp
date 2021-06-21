package com.example.feimusic;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;

import android.widget.Toast;


import com.example.feimusic.API.ApiClient;
import com.example.feimusic.Response.CancionResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listView;

    private SearchView searchView;
    ArrayList<CancionResponse> canciones = new ArrayList<CancionResponse>();
    cancionAdapter adapter;

    private List<CancionResponse> cancionResponseList = new ArrayList<>();
    AdapterBusquedaCancion adaptador;

    RecyclerView recyclerView;
    SearchView busqueda;


    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View root = inflater.inflate(R.layout.fragment_search, container, false);


        searchView = root.findViewById(R.id.search);

        recyclerView = root.findViewById(R.id.ListaBusqueda);


        LinearLayoutManager manager = new LinearLayoutManager(this.getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adaptador = new AdapterBusquedaCancion( this.getActivity(), cancionResponseList);
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reproductor();
            }
        });
        recyclerView.setAdapter(adaptador);


        //buscar("Bichota");
        busqueda = root.findViewById(R.id.search);

        initListener();


        return root;
    }

    public void buscar(String cancionABuscar){

        Call<List<CancionResponse>> listCall = ApiClient.getCancionService().getCancion(cancionABuscar);
        listCall.enqueue(new Callback<List<CancionResponse>>() {
            @Override
            public void onResponse(Call<List<CancionResponse>> call, Response<List<CancionResponse>> response) {

                List<CancionResponse> canciones = response.body();
                if(response.code() != 200){
                    Toast.makeText(getActivity(), "No trae nada", Toast.LENGTH_LONG).show();
                }else{
                    cancionResponseList.addAll(canciones);

                    SendDataToAdapter(cancionResponseList);
                }
            }

            @Override
            public void onFailure(Call<List<CancionResponse>> call, Throwable t) {

            }
        });
    }

    public void SendDataToAdapter(List<CancionResponse> listaCanciones){
        //AdapterBusquedaCancion adaptador = new AdapterBusquedaCancion( this.getActivity(), listaCanciones);
        LinearLayoutManager manager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        //AdapterBusquedaCancion adaptador = new AdapterBusquedaCancion( this.getActivity(), listaCanciones);
        adaptador.setCancionesResponseList(listaCanciones);
        adaptador.notifyDataSetChanged();

        recyclerView.setAdapter(adaptador);
    }

    public void Reproductor(){
        Intent intent = new Intent(getActivity(), Reproductor.class);
        startActivity(intent);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        cancionResponseList.clear();
        buscar(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }

    public void initListener(){
        busqueda.setOnQueryTextListener(this);
    }


}