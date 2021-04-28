package com.va.neoapp.presentation;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(setLayoutResource());
        initGUI(savedInstanceState);
        initData();
    }

    protected abstract int setLayoutResource();

    protected abstract void initGUI(Bundle savedInstanceState);

    protected abstract void initData();

}