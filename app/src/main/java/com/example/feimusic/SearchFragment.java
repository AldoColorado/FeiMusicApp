package com.example.feimusic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.feimusic.API.ApiClient;
import com.example.feimusic.Response.CancionResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

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
        listView = root.findViewById(R.id.ListaBusqueda);
        searchView = root.findViewById(R.id.search);
        //adapter = new cancionAdapter(this, )

        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    public void obtenerBusqueda(String busqueda){
        Call<CancionResponse> cancionResponseCall = ApiClient.getCancionService().getCancion("1"); {
            cancionResponseCall.enqueue(new Callback<CancionResponse>() {
                @Override
                public void onResponse(Call<CancionResponse> call, Response<CancionResponse> response) {

                }

                @Override
                public void onFailure(Call<CancionResponse> call, Throwable t) {

                }
            });
        }
    }
}