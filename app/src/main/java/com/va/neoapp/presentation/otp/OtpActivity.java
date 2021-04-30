package com.va.neoapp.presentation.otp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.va.neoapp.R;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.util.GlobalMethods;

public class OtpActivity extends BaseActivity {

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_otp;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        readBundle();
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

    }
}
