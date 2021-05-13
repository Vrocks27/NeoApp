package com.va.neoapp.presentation.splash;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.va.neoapp.R;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.presentation.home.activities.HomeActivity;
import com.va.neoapp.util.Constants;
import com.va.neoapp.util.GlobalMethods;

public class SplashActivity extends BaseActivity {

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    GlobalMethods.callForWordActivity(SplashActivity.this, HomeActivity.class, null, true, true);
                } catch (Exception exp) {
                    exp.printStackTrace();
                }
            }
        }, 3000);


    }

    @Override
    protected void initData() {
        // GlobalMethods.setServiceType(SplashActivity.this, Constants.POINT_DEV);
        GlobalMethods.setServiceType(SplashActivity.this, Constants.POINT_PRODUCTION);
    }
}
