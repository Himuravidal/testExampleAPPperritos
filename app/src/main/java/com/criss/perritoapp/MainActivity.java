package com.criss.perritoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.criss.perritoapp.model.api.ApiDog;
import com.criss.perritoapp.model.api.RetrofitClient;
import com.criss.perritoapp.model.BreedListResponse;
import com.criss.perritoapp.vista.BreedListFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeFragmentBreed();
    }

    private void initializeFragmentBreed() {
        BreedListFragment breedListFragment = BreedListFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayout01, breedListFragment, breedListFragment.getClass().getSimpleName())
                .commit();
    }
}
