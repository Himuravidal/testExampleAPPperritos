package com.criss.perritoapp.vista;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.criss.perritoapp.R;
import com.criss.perritoapp.presenter.breedListPresenter.IViewListBreeds;
import com.criss.perritoapp.presenter.breedListPresenter.PresenterBreedList;
import com.criss.perritoapp.vista.adapters.BreedListAdapter;
import com.criss.perritoapp.model.api.ApiDog;
import com.criss.perritoapp.model.api.RetrofitClient;
import com.criss.perritoapp.model.BreedImageListResponse;
import com.criss.perritoapp.model.BreedListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BreedListFragment extends Fragment implements BreedListAdapter.ItemClick, IViewListBreeds {

    private PresenterBreedList presenter;
    private BreedListAdapter adapter;
    private RecyclerView recyclerView;
    private List<String> perritos = new ArrayList<>();

    public BreedListFragment() {
        // Required empty public constructor
    }

    public static BreedListFragment newInstance() {
        BreedListFragment fragment = new BreedListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(), "ESTOY EN EL FRAGMENTO", Toast.LENGTH_SHORT).show();
        presenter = new PresenterBreedList(this);
        presenter.loadBreedList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_breed_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewBreedList);
        Log.e("ALGO OCUrre", String.valueOf(perritos.size()));
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }

    @Override
    public void onClick(String dog) {
        Toast.makeText(getContext(), dog, Toast.LENGTH_SHORT).show();
        presenter.loadImagesBreed(dog);
    }

    @Override
    public void showBreedList(List<String> dogBreedList) {
        adapter = new BreedListAdapter(dogBreedList, getContext(),this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showImagesUrlList(List<String> dogsUrlList) {
        Log.e("URL", dogsUrlList.toString());
        initializeFragmentBreed(dogsUrlList);
    }

    @Override
    public void showMessageInView(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }



    private void initializeFragmentBreed(List<String> urls) {
        BreedImageListFragment breedImageListFragment = BreedImageListFragment.newInstance(urls);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout01, breedImageListFragment, breedImageListFragment.getClass().getSimpleName())
                .addToBackStack("ALgo")
                .commit();
    }






}
