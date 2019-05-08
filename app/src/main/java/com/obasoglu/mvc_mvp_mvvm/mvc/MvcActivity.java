package com.obasoglu.mvc_mvp_mvvm.mvc;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.obasoglu.mvc_mvp_mvvm.R;

public class MvcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        setTitle("MVC");
    }

    public static Intent getIntent(Context context)
    {
        return new Intent(context, MvcActivity.class);
    }
}
