package com.va.neoapp.presentation.login;

import android.content.res.ColorStateList;
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

import com.va.neoapp.presentation.registration.RegistrationActivity;
import com.va.neoapp.util.GlobalMethods;

public class LoginActivity extends BaseActivity {

    private AppCompatEditText email_mobile_edit_text;
    private CountryCodePicker countryCodePicker;

    //private String selectedCountryCode = "";
    private boolean isMobileNumber = false;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        email_mobile_edit_text = findViewById(R.id.email_mobile_edit_text);
        countryCodePicker = findViewById(R.id.code_mob_spinner);
        disableBottomNext();
        email_mobile_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Log.e("beforeText: ", "" + s + "start: " + start + " count " + count + " after " + after);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Log.e("TextChange: ", "" + s + "start: " + start + " before " + before + " count " + count);
                if (s.length() >= 3) {
                    if (TextUtils.isDigitsOnly(s.toString())) {
                        //if it in mobile
                        //handleTextError(true, "");
                        countryCodePicker.setVisibility(View.VISIBLE);
                        if (GlobalMethods.isValidInternationalMobileNumber(s.toString())) {
                            handleTextError(true, "");
                            enableBottomNext();
                            isMobileNumber = true;
                        } else {
                            handleTextError(false, getString(R.string.text_invalid_mobile));
                            disableBottomNext();
                            isMobileNumber = false;
                        }

                    } else {
                        isMobileNumber = false;
                        countryCodePicker.setVisibility(View.GONE);
                        if (GlobalMethods.isValidEmailAddress(s.toString())) {
                            handleTextError(true, "");
                            enableBottomNext();
                        } else {
                            handleTextError(false, getString(R.string.text_invalid_email));
                            disableBottomNext();
                        }
                    }
                }else if (s.length() <=1){
                    // set nothing, hide error
                    countryCodePicker.setVisibility(View.GONE);
                    //disableBottomNext();
                    findViewById(R.id.text_error).setVisibility(View.GONE);
                    ((AppCompatTextView) findViewById(R.id.text_error)).setText("");
                    email_mobile_edit_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //country code selecter
//        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
//            @Override
//            public void onCountrySelected(Country country) {
//                //selectedCountryCode = countryCodePicker.getSelectedCountryCodeWithPlus();
//                // GlobalMethods.showNormalToast(LoginActivity.this, selectedcountry, 0);
//            }
//        });
    }

    private String getMobileNumber() {
        if (isMobileNumber) {
            if (GlobalMethods.isNull(countryCodePicker.getSelectedCountryCodeWithPlus())) {
                return countryCodePicker.getSelectedCountryCodeWithPlus() + email_mobile_edit_text.getText().toString().trim();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    private void handleTextError(boolean isValid, String textError) {
        if (isValid) {
            findViewById(R.id.text_error).setVisibility(View.GONE);
            ((AppCompatTextView) findViewById(R.id.text_error)).setText("");
            email_mobile_edit_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_tickmark, 0);

        } else {
            findViewById(R.id.text_error).setVisibility(View.VISIBLE);
            ((AppCompatTextView) findViewById(R.id.text_error)).setText(textError);
            email_mobile_edit_text.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_wrongmark, 0);
        }
    }

    /*private void disableBottomNext() {
        findViewById(R.id.bottom_fab_button).setEnabled(false);
        findViewById(R.id.bottom_fab_button).setClickable(false);
        ((FloatingActionButton) findViewById(R.id.bottom_fab_button)).setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.disabled_color)));
        findViewById(R.id.bottom_fab_button).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.default_disabled)));
        ((AppCompatTextView) findViewById(R.id.text_next)).setTextColor(getResources().getColor(R.color.disabled_color));
    }*/

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

   /* private void enableBottomNext() {
        findViewById(R.id.bottom_fab_button).setEnabled(true);
        findViewById(R.id.bottom_fab_button).setClickable(true);
        ((FloatingActionButton) findViewById(R.id.bottom_fab_button)).setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        findViewById(R.id.bottom_fab_button).setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.default_main_color)));

        ((AppCompatTextView) findViewById(R.id.text_next)).setTextColor(getResources().getColor(R.color.default_text_color));
    }*/

    @Override
    protected void initData() {
        findViewById(R.id.text_new_user).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMethods.callForWordActivity(LoginActivity.this, RegistrationActivity.class, null, false, true);
            }
        });

        findViewById(R.id.bottom_fab_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (isMobileNumber) {
                    bundle.putString("emailMobile", getMobileNumber());
                } else {
                    bundle.putString("emailMobile", email_mobile_edit_text.getText().toString().trim());
                }
                GlobalMethods.callForWordActivity(LoginActivity.this, OtpActivity.class, bundle, false, true);
            }
        });
    }


}
