package com.obasoglu.mvc_mvp_mvvm.mvvm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.obasoglu.mvc_mvp_mvvm.R;
import com.obasoglu.mvc_mvp_mvvm.mvp.MvpActivity;

public class MvvmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvvm);
        setTitle("MVVM");

    }

    public static Intent getIntent(Context context)
    {
        return new Intent(context, MvvmActivity.class);
    }

}
