package com.va.neoapp.presentation.registration;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.va.neoapp.R;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.presentation.otp.OtpActivity;
import com.va.neoapp.util.GlobalMethods;

public class RegistrationActivity extends BaseActivity {
    private AppCompatEditText edit_text_email,edit_text_mobile ;
    private CountryCodePicker countryMobCodePicker;
    @Override
    protected int setLayoutResource() {
        return R.layout.activity_registration;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        edit_text_email =findViewById(R.id.edit_text_email);
        edit_text_mobile =  findViewById(R.id.edit_text_mobile);
        countryMobCodePicker = findViewById(R.id.code_mob_spinner);
        disableBottomNext();
        showEmailError(false);
        showMobileError(false);

        edit_text_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Log.e("beforeText: ", "" + s + "start: " + start + " count " + count + " after " + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Log.e("TextChange: ", "" + s + "start: " + start + " before " + before + " count " + count);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edit_text_mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Log.e("beforeText: ", "" + s + "start: " + start + " count " + count + " after " + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Log.e("TextChange: ", "" + s + "start: " + start + " before " + before + " count " + count);
                if (TextUtils.isDigitsOnly(s.toString())) {
                    if (GlobalMethods.isValidInternationalMobileNumber(s.toString())) {

                    }else{
                        
                    }
                }else{

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void showEmailError(boolean showError){
        if (showError){
            findViewById(R.id.text_email_error).setVisibility(View.VISIBLE);
        }else {
            findViewById(R.id.text_email_error).setVisibility(View.GONE);
        }
    }
    private void showMobileError(boolean showError){
        if (showError){
            findViewById(R.id.text_mobile_error).setVisibility(View.VISIBLE);
        }else {
            findViewById(R.id.text_mobile_error).setVisibility(View.GONE);
        }

    }
    @Override
    protected void initData() {
        findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMethods.callFinishForBackWordActivity(RegistrationActivity.this, false);
            }
        });
        findViewById(R.id.bottom_fab_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
//                if (isMobileNumber) {
//                    bundle.putString("emailMobile", getMobileNumber());
//                } else {
                    bundle.putString("emailMobile", edit_text_email.getText().toString().trim());
                //}
                GlobalMethods.callForWordActivity(RegistrationActivity.this, OtpActivity.class, bundle, false, true);
            }
        });
    }

    private void disableBottomNext() {
        GlobalMethods.disableBottomNext(getBaseContext(),
                ((FloatingActionButton) findViewById(R.id.bottom_fab_button)),
                ((AppCompatTextView) findViewById(R.id.text_next)) );
    }
    private void enableBottomNext() {
        GlobalMethods.enableBottomNext(getBaseContext(),
                ((FloatingActionButton) findViewById(R.id.bottom_fab_button)),
                ((AppCompatTextView) findViewById(R.id.text_next)) );
    }
}
