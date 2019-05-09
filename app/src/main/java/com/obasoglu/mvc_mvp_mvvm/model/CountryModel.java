package com.obasoglu.mvc_mvp_mvvm.model;

import com.obasoglu.mvc_mvp_mvvm.model.network.CountriesApi;
import com.obasoglu.mvc_mvp_mvvm.model.network.NetworkManager;

import java.util.List;

import io.reactivex.Single;

public class CountryModel {

    private CountriesApi countriesApi;


    public CountryModel() {
        countriesApi = NetworkManager.getInstance().getRetrofit().create(CountriesApi.class);
    }

    /**
     *   Get All countries' names
     */
    public Single<List<Country>> getCountries() {
        return countriesApi.getCountries();
    }
}
