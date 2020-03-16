package com.criss.perritoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.criss.perritoapp.api.ApiDog;
import com.criss.perritoapp.api.RetrofitClient;
import com.criss.perritoapp.model.BreedImageListResponse;
import com.criss.perritoapp.model.BreedListResponse;
import com.criss.perritoapp.ui.BreedListFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private String perro1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ApiDog service = RetrofitClient.getRetrofitInstance().create(ApiDog.class);
        Call<BreedListResponse> call = service.getBreedList();


        call.enqueue(new Callback<BreedListResponse>() {
            @Override
            public void onResponse(Call<BreedListResponse> call, Response<BreedListResponse> response) {

                if (response.body() != null) {
                    List<String> perritos = response.body().getBreedList();
                    Log.e("PERRITOS", String.valueOf(perritos));

                    initializeFragmentBreed(perritos);

                } else {
                    Log.e("PERRITOS", "ES NULO");
                }

            }
            @Override
            public void onFailure(Call<BreedListResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this , "FALLO, intentelo otra vez", Toast.LENGTH_SHORT).show();
                Log.e("PERRITOS", String.valueOf(t));

            }
        });

    }


    private void whoLetTheDogsOut() {
        ApiDog service = RetrofitClient.getRetrofitInstance().create(ApiDog.class);
        Call<BreedImageListResponse> callImages = service.getBreedImageList(perro1);
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

    private void initializeFragmentBreed(List<String> listadoPerritos) {
        BreedListFragment breedListFragment = BreedListFragment.newInstance(listadoPerritos);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayout01, breedListFragment, breedListFragment.getClass().getSimpleName())
                .commit();
    }

}
