package com.va.neoapp.presentation.otp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.va.neoapp.R;
import com.va.neoapp.custom.pinentry.PinEntryView;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.presentation.home.activities.HomeActivity;
import com.va.neoapp.presentation.onboarding.OnBoardingAct;
import com.va.neoapp.presentation.termsnconditions.activities.TermNConditionsAct;
import com.va.neoapp.util.GlobalMethods;

public class OtpActivity extends BaseActivity {

    AppCompatTextView text_resend_otp, login_otp_resend_timer;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_otp;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        readBundle();
        login_otp_resend_timer = findViewById(R.id.login_otp_resend_timer);
        text_resend_otp = findViewById(R.id.text_resend_otp);
        disableBottomNext();
        ((PinEntryView) findViewById(R.id.pin_entry_view)).addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4) {
                    enableBottomNext();
                } else {
                    disableBottomNext();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        findViewById(R.id.bottom_fab_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check the otp validation from server and navigate to next screen
                String send_otp = ((PinEntryView) findViewById(R.id.pin_entry_view)).getText().toString().trim();


                Bundle bundle = new Bundle();
//                if (isMobileNumber) {
//                    bundle.putString("emailMobile", getMobileNumber());
//                } else {
               // bundle.putString("emailMobile", edit_text_email.getText().toString().trim() +" or "+getMobileNumber());
                //}
               // GlobalMethods.callForWordActivity(OtpActivity.this, HomeActivity.class, bundle, false, true);
                GlobalMethods.callForWordActivity(OtpActivity.this, TermNConditionsAct.class, bundle, false, true);
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

    private void disableBottomNext() {
        GlobalMethods.disableBottomNext(getBaseContext(),
                ((FloatingActionButton) findViewById(R.id.bottom_fab_button)),
                ((AppCompatTextView) findViewById(R.id.text_next)));
    }

    private void enableBottomNext() {
        GlobalMethods.enableBottomNext(getBaseContext(),
                ((FloatingActionButton) findViewById(R.id.bottom_fab_button)),
                ((AppCompatTextView) findViewById(R.id.text_next)));
    }
}
