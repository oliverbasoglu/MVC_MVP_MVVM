package com.obasoglu.mvc_mvp_mvvm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.obasoglu.mvc_mvp_mvvm.mvc.view.MvcActivity;
import com.obasoglu.mvc_mvp_mvvm.mvp.view.MvpActivity;
import com.obasoglu.mvc_mvp_mvvm.mvvm.view.MvvmActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void gotoMVCActivity(View view) {
        startActivity(MvcActivity.getIntent(this));
    }

    public void gotoMVPActivity(View view) {
        startActivity(MvpActivity.getIntent(this));
    }

    public void gotoMVVMActivity(View view) {
        startActivity(MvvmActivity.getIntent(this));
    }


}
