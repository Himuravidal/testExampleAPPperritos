package com.criss.perritoapp.presenter.breedListPresenter;

import android.util.Log;
import android.widget.Toast;

import com.criss.perritoapp.MainActivity;
import com.criss.perritoapp.model.BreedImageListResponse;
import com.criss.perritoapp.model.BreedListResponse;
import com.criss.perritoapp.model.api.ApiDog;
import com.criss.perritoapp.model.api.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PresenterBreedList implements IPresenterListBreeds {

    IViewListBreeds view;
    ApiDog service = RetrofitClient.getRetrofitInstance().create(ApiDog.class);
    Call<BreedListResponse> call = service.getBreedList();

    public PresenterBreedList(IViewListBreeds view) {
        this.view = view;
    }

    @Override
    public void loadBreedList() {
        call.enqueue(new Callback<BreedListResponse>() {
            @Override
            public void onResponse(Call<BreedListResponse> call, Response<BreedListResponse> response) {
                if (response.body() != null) {
                    List<String> perritos = response.body().getBreedList();
                    Log.e("PERRITOS", String.valueOf(perritos));
                    view.showBreedList(perritos);
                } else {
                    Log.e("PERRITOS", "ES NULO");
                }
            }
            @Override
            public void onFailure(Call<BreedListResponse> call, Throwable t) {
                view.showMessageInView(t.toString());
            }
        });
    }

    @Override
    public void loadImagesBreed(String dogBreed) {
        Call<BreedImageListResponse> callImages = service.getBreedImageList(dogBreed);
        callImages.enqueue(new Callback<BreedImageListResponse>() {
            @Override
            public void onResponse(Call<BreedImageListResponse> call, Response<BreedImageListResponse> response) {
                List<String> imagesURL = response.body().getImageURL();
                Log.e("IMAGESDOGS", String.valueOf(imagesURL));
                view.showImagesUrlList(imagesURL);

            }
            @Override
            public void onFailure(Call<BreedImageListResponse> call, Throwable t) {
                view.showMessageInView(t.toString());
            }
        });
    }
}
