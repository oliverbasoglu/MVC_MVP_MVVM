package com.obasoglu.mvc_mvp_mvvm.mvp.view;

import java.util.List;

public interface ActivityCountryView {

    public void setValues(List<String> countryNames);

    public void onError();
}
