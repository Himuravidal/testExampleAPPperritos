package com.criss.perritoapp.api;

import com.criss.perritoapp.model.BreedImageListResponse;
import com.criss.perritoapp.model.BreedListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiDog {

    @GET("api/breeds/list/")
    Call<BreedListResponse> getBreedList();


    @GET("api/breed/{breed}/images/")
    Call<BreedImageListResponse> getBreedImageList(@Path("breed") String breed);

}
