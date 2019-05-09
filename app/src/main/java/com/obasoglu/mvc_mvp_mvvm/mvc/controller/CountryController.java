package com.obasoglu.mvc_mvp_mvvm.mvc.controller;

import com.obasoglu.mvc_mvp_mvvm.model.Country;
import com.obasoglu.mvc_mvp_mvvm.model.CountryModel;
import com.obasoglu.mvc_mvp_mvvm.mvc.view.MvcActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountryController {


    private MvcActivity view;
    private CountryModel model;


    public CountryController(MvcActivity view) {
        this.view = view;
        model = new CountryModel();
    }

    private void fetchData() {

        model.getCountries()         // Get countries
                .subscribeOn(Schedulers.newThread())        // Work on new thread
                .observeOn(AndroidSchedulers.mainThread())  // Observe results on MainThread
                .subscribe(new DisposableSingleObserver<List<Country>>() {      //Subscribe
                    @Override
                    public void onSuccess(List<Country> countries) {
                        //Result is successful
                        //Convert data to String ArrayList
                        List<String> countryNames = new ArrayList<>();
                        for (Country country : countries) {
                            countryNames.add(country.name);
                        }

                        // set values in the view
                        view.setValues(countryNames);

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Handle the error.
                        view.onError();
                    }
                });
    }

    public void onstart()
    {
        fetchData();
    }


    public void retry() {
        fetchData();
    }
}
