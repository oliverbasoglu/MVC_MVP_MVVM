package com.obasoglu.mvc_mvp_mvvm.model.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Eager Initialized Singleton for network operation.
 * Retrofit client is being created in NetworkManager's constructor.
 */
public class NetworkManager {

    private static String BASE_URL = "https://restcountries.eu/rest/v2/";
    private static final NetworkManager instance = new NetworkManager();
    private Retrofit retrofit;

    private NetworkManager() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())     //Parsing data with Gson
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //RxJava2 Adapter Factory
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static NetworkManager getInstance() {
        return instance;
    }




}
