package com.obasoglu.mvc_mvp_mvvm.mvvm.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.obasoglu.mvc_mvp_mvvm.R;
import com.obasoglu.mvc_mvp_mvvm.mvp.view.MvpActivity;
import com.obasoglu.mvc_mvp_mvvm.mvvm.view_model.CountriesViewModel;

import java.util.ArrayList;
import java.util.List;


public class MvvmActivity extends AppCompatActivity {

    private List<String> countryList = new ArrayList<>();
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private Button buttonRetry;
    private ProgressBar progressBar;

    private CountriesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        setTitle("MVVM");

        initializeViews();
        progressBar.setVisibility(View.VISIBLE);

        adapter = new ArrayAdapter<>(this, R.layout.row_list_countries, R.id.text_countryName, countryList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MvvmActivity.this, "Clicked: " + countryList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        viewModel = ViewModelProviders.of(this).get(CountriesViewModel.class);

        viewModel.getCountriesLiveData().observe(this, countries ->
        {
            if (countries != null) {
                countryList.clear();
                countryList.addAll(countries);
                listView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
            } else {
                listView.setVisibility(View.GONE);
            }
        });

        viewModel.getIsError().observe(this, isError -> {
            progressBar.setVisibility(View.GONE);
            if (isError != null && isError) {
                Toast.makeText(MvvmActivity.this, R.string.ErrorOnFetch, Toast.LENGTH_SHORT).show();
                buttonRetry.setVisibility(View.VISIBLE);
            } else {
                buttonRetry.setVisibility(View.GONE);
            }
        });

    }

    private void initializeViews() {
        listView = findViewById(R.id.list_countries);
        buttonRetry = findViewById(R.id.button_retry);
        progressBar = findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.VISIBLE);
        buttonRetry.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MvvmActivity.class);
    }

}
