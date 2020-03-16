package com.criss.perritoapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.criss.perritoapp.MainActivity;
import com.criss.perritoapp.R;
import com.criss.perritoapp.adapters.BreedListAdapter;
import com.criss.perritoapp.api.ApiDog;
import com.criss.perritoapp.api.RetrofitClient;
import com.criss.perritoapp.model.BreedImageListResponse;
import com.criss.perritoapp.model.BreedListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedListFragment extends Fragment implements BreedListAdapter.ItemClick {
    private static final String ARG_PARAM1 = "param1";
    private ArrayList<String> mParam1;


    private BreedListAdapter adapter;
    private RecyclerView recyclerView;

    List<String> perritos = new ArrayList<>();
    public BreedListFragment() {
        // Required empty public constructor
    }

    public static BreedListFragment newInstance(List<String> param1) {
        BreedListFragment fragment = new BreedListFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, (ArrayList<String>) param1);

        Log.e("BREEDLISTFRAGMENT", String.valueOf(param1));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), "ESTOY EN EL FRAGMENTO", Toast.LENGTH_SHORT).show();

        ApiDog service = RetrofitClient.getRetrofitInstance().create(ApiDog.class);
        Call<BreedListResponse> call = service.getBreedList();

        if (getArguments() != null) {
            mParam1 = getArguments().getStringArrayList(ARG_PARAM1);
            perritos = mParam1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_breed_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewBreedList);
        Log.e("ALGO OCUrre", String.valueOf(perritos.size()));
        adapter = new BreedListAdapter(perritos, getContext(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onClick(String dog) {
        Toast.makeText(getContext(), dog, Toast.LENGTH_SHORT).show();
        whoLetTheDogsOut(dog);
    }



    private void whoLetTheDogsOut(String dogName) {
        ApiDog service = RetrofitClient.getRetrofitInstance().create(ApiDog.class);
        Call<BreedImageListResponse> callImages = service.getBreedImageList(dogName);
        callImages.enqueue(new Callback<BreedImageListResponse>() {
            @Override
            public void onResponse(Call<BreedImageListResponse> call, Response<BreedImageListResponse> response) {
                List<String> imagesURL = response.body().getImageURL();
                Log.e("IMAGESDOGS", String.valueOf(imagesURL));
            }
            @Override
            public void onFailure(Call<BreedImageListResponse> call, Throwable t) {
                Log.e("FALLO", String.valueOf(t));
            }
        });
    }
}
