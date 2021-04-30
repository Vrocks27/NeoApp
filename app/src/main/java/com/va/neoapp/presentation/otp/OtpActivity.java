package com.va.neoapp.presentation.otp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.va.neoapp.R;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.util.GlobalMethods;

public class OtpActivity extends BaseActivity {

    AppCompatTextView text_resend_otp, login_otp_resend_timer;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_otp;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        login_otp_resend_timer = findViewById(R.id.login_otp_resend_timer);
        text_resend_otp = findViewById(R.id.text_resend_otp);
        readBundle();
        getTimerCount();
    }

    private void readBundle() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("emailMobile")) {
                String emailMob = bundle.getString("emailMobile");
                if (GlobalMethods.isNull(emailMob)) {
                    ((AppCompatTextView) findViewById(R.id.text_selected_email_mobile)).setText(emailMob);
                } else {
                    ((AppCompatTextView) findViewById(R.id.text_selected_email_mobile)).setText("");
                }
            }
        }
    }

    @Override
    protected void initData() {

        findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMethods.callFinishForBackWordActivity(OtpActivity.this, false);
            }
        });

        text_resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //timer should be call after response from api now its just testing
                getTimerCount();
            }
        });

    }

    private void getTimerCount() {
        text_resend_otp.setVisibility(View.GONE);
        login_otp_resend_timer.setVisibility(View.VISIBLE);
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                login_otp_resend_timer.setText("Wait " + millisUntilFinished / 1000 + " seconds");
            }

            public void onFinish() {
                login_otp_resend_timer.setVisibility(View.GONE);
                text_resend_otp.setVisibility(View.VISIBLE);
            }

        }.start();
    }
}
