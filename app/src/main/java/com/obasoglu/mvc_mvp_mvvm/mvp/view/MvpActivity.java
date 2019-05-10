package com.obasoglu.mvc_mvp_mvvm.mvp.view;

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
import com.obasoglu.mvc_mvp_mvvm.mvp.presenter.CountryPresenter;
import java.util.ArrayList;
import java.util.List;

public class MvpActivity extends AppCompatActivity implements ActivityCountryView {

    private List<String> countryList = new ArrayList<>();
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private Button buttonRetry;
    private ProgressBar progressBar;

    private CountryPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        setTitle("MVP");

        initializeViews();

        adapter = new ArrayAdapter<>(this, R.layout.row_list_countries, R.id.text_countryName, countryList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MvpActivity.this, "Clicked: " + countryList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        // Application start event occurred.
        presenter = new CountryPresenter(this);
        presenter.onstart();
    }


    @Override
    public void setValues(List<String> countries) {
        countryList.clear();
        countryList.addAll(countries);
        progressBar.setVisibility(View.GONE);
        buttonRetry.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError() {
        Toast.makeText(MvpActivity.this, R.string.ErrorOnFetch, Toast.LENGTH_SHORT).show();
        listView.setVisibility(View.GONE);
        buttonRetry.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    public void onRetry(View view) {
        // Retry event occurred
        presenter.retry();
        progressBar.setVisibility(View.VISIBLE);
        buttonRetry.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
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
        return new Intent(context, MvpActivity.class);
    }
}
