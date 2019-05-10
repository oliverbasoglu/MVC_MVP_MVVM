package com.obasoglu.mvc_mvp_mvvm.mvvm.view_model;



import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.obasoglu.mvc_mvp_mvvm.model.Country;
import com.obasoglu.mvc_mvp_mvvm.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CountriesViewModel extends ViewModel {

    private final MutableLiveData<List<String>> countriesLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isError = new MutableLiveData<>();

    private CountryModel model;

    public CountriesViewModel() {
        model = new CountryModel();
        fetchData();
    }

    public MutableLiveData<List<String>> getCountriesLiveData() {
        return countriesLiveData;
    }

    public MutableLiveData<Boolean> getIsError() {
        return isError;
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

                        // set values to LiveData
                        countriesLiveData.setValue(countryNames);
                        isError.setValue(false);

                    }

                    @Override
                    public void onError(Throwable e) {
                        //Handle the error.
                        isError.setValue(true);
                    }
                });
    }

    public void retry() {
        fetchData();
    }
}
