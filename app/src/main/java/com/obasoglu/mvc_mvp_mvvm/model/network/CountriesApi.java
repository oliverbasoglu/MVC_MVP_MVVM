package com.obasoglu.mvc_mvp_mvvm.model.network;

import com.obasoglu.mvc_mvp_mvvm.model.Country;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface CountriesApi {

    @GET("all")
    Single<List<Country>> getCountries();
}
