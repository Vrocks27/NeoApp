package com.va.neoapp.presentation.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;


import com.va.neoapp.R;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.presentation.login.LoginActivity;
import com.va.neoapp.presentation.otp.OtpActivity;
import com.va.neoapp.presentation.registration.RegistrationActivity;
import com.va.neoapp.util.Constants;
import com.va.neoapp.util.GlobalMethods;
import com.va.neoapp.webaccess.VersionControl;
import com.va.neoapp.webaccess.WebConstants;

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
                    // if login the go to home
//                    if (GlobalMethods.getIsLogin(SplashActivity.this)) {
//                       // startActivity(new Intent(SplashActivity.this, xyz.class));
//                        finish();
//                    } else {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
//                        finish();
//                    }
                  //  GlobalMethods.callForWordActivity(SplashActivity.this, LoginActivity.class, null, true, true);


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
