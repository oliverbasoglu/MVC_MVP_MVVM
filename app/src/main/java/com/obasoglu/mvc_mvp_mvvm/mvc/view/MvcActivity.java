package com.obasoglu.mvc_mvp_mvvm.mvc.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.obasoglu.mvc_mvp_mvvm.R;
import com.obasoglu.mvc_mvp_mvvm.mvc.controller.CountryController;

import java.util.ArrayList;
import java.util.List;

public class MvcActivity extends AppCompatActivity {

    private List<String> countryList = new ArrayList<>();
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private Button buttonRetry;
    private ProgressBar progressBar;

    private CountryController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        setTitle("MVC");

        initializeViews();

        adapter = new ArrayAdapter<>(this, R.layout.row_list_countries, R.id.text_countryName, countryList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MvcActivity.this, "Clicked: " + countryList.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        // Application start event occurred.
        controller = new CountryController(this);
        controller.onstart();

    }

    private void initializeViews()
    {
        listView = findViewById(R.id.list_countries);
        buttonRetry = findViewById(R.id.button_retry);
        progressBar = findViewById(R.id.progress_circular);
        progressBar.setVisibility(View.VISIBLE);
        buttonRetry.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
    }

    public void setValues(List<String> values) {
        countryList.clear();
        countryList.addAll(values);
        progressBar.setVisibility(View.GONE);
        buttonRetry.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
        adapter.notifyDataSetChanged();
    }

    public void onError()
    {
        Toast.makeText(MvcActivity.this,R.string.ErrorOnFetch,Toast.LENGTH_SHORT).show();
        listView.setVisibility(View.GONE);
        buttonRetry.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    public void onRetry(View view)
    {
        // Retry event occurred
        controller.retry();
        progressBar.setVisibility(View.VISIBLE);
        buttonRetry.setVisibility(View.GONE);
        listView.setVisibility(View.GONE);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, MvcActivity.class);
    }

}
