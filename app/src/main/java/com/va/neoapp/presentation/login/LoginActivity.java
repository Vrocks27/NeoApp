package com.va.neoapp.presentation.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rilixtech.widget.countrycodepicker.Country;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.va.neoapp.R;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.presentation.registration.RegistrationActivity;
import com.va.neoapp.util.GlobalMethods;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    Context context;
    CountryCodePicker countryCodePicker;
    String selectedcountry="";
    AppCompatTextView text_new_user;
    ImageButton login_button;
    EditText email_edit_text;
    LinearLayout bottom_layout;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        context=this;

        text_new_user=findViewById(R.id.text_new_user);
        email_edit_text=findViewById(R.id.email_edit_text);
        bottom_layout=findViewById(R.id.bottom_layout);
        countryCodePicker=findViewById(R.id.code_mob_spinner);

        //country code selecter
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected(Country selectedCountry) {
                selectedcountry=countryCodePicker.getSelectedCountryCodeWithPlus();
                Toast.makeText(context,selectedcountry,Toast.LENGTH_SHORT).show();
            }
        });

        //Textwatcher for login
        email_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String emailOrPhone=s.toString();
                if (emailOrPhone.contains("@")) {
                    if (!GlobalMethods.isValidEmailAddress(emailOrPhone)){
                        email_edit_text.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_tickmark,0);
                    }else{
                        email_edit_text.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_wrongmark,0);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void initData() {
        text_new_user.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_new_user:
                startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
                break;

        }
    }

}
