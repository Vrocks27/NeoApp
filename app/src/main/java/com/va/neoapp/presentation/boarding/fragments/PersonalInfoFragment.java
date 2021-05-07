package com.va.neoapp.presentation.boarding.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.va.neoapp.R;

public class PersonalInfoFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public PersonalInfoFragment() {
        // Required empty public constructor
    }

    private CountryCodePicker select_code_spinner;

    public static PersonalInfoFragment newInstance(String param1, String param2) {
        PersonalInfoFragment fragment = new PersonalInfoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_info, container, false);
        initGUI(view);
        actionEvents(view);
        return view;
    }


    private void actionEvents(View view) {

    }

    private void initGUI(View view) {
        select_code_spinner =  view.findViewById(R.id.select_code_spinner);
        //select_code_spinner.setPhoneNumberInputValidityListener();

    }
}