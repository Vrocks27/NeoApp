package com.va.neoapp.presentation.boarding.fragments;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.va.neoapp.R;
import com.va.neoapp.adapters.TermsNConditionsAdapter;
import com.va.neoapp.presentation.boarding.activities.UniversityBoardingAct;
import com.va.neoapp.presentation.termsnconditions.activities.TermNConditionsAct;
import com.va.neoapp.util.GlobalMethods;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class PersonalInfoFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private static FloatingActionButton fab_next;
    private String selectedPermanentState;
    private String selectedPermanentCity;


    public PersonalInfoFragment(FloatingActionButton fab_next) {
        PersonalInfoFragment.fab_next = fab_next;
        // Required empty public constructor
    }


    private Context mContext;
    private CountryCodePicker select_code_spinner;
    private AppCompatSpinner spinner_gender, spinner_country_origin, spinner_states, spinner_cities, country_state, country_city;
    private int position;
    private String selectedUom;
    private AppCompatEditText et_last_name, et_firstname, et_mobile_number, et_emailid, et_permanent_address, et_permanent_pincode,
            et_current_address,et_current_pincode;
    private String startDate;
    private AppCompatEditText datepick;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private RadioGroup radiogroup_permanent_address;
    private LinearLayout layout_current_address;
    private AppCompatTextView tv_same_address;


    public static PersonalInfoFragment newInstance(String param1, String param2) {
        PersonalInfoFragment fragment = new PersonalInfoFragment(fab_next);
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
        mContext = getActivity();
        fab_next.setVisibility(View.VISIBLE);
        initGUI(view);
        actionEvents(view);
        setDataToSpinner();

        return view;
    }

    private void initGUI(View view) {
        select_code_spinner = view.findViewById(R.id.select_code_spinner);
        spinner_gender = view.findViewById(R.id.spinner_gender);
        spinner_country_origin = view.findViewById(R.id.spinner_country_origin);
        spinner_states = view.findViewById(R.id.spinner_states);
        spinner_cities = view.findViewById(R.id.spinner_cities);
        country_state = view.findViewById(R.id.current_state);
        country_city = view.findViewById(R.id.current_city);
        datepick = view.findViewById(R.id.date);
        et_firstname = view.findViewById(R.id.et_firstname);
        et_last_name = view.findViewById(R.id.et_last_name);
        et_emailid = view.findViewById(R.id.et_emailid);
        et_mobile_number = view.findViewById(R.id.et_mobile_number);
        et_permanent_address = view.findViewById(R.id.et_permanent_address);
        et_permanent_pincode = view.findViewById(R.id.et_permanent_pincode);
        radiogroup_permanent_address = view.findViewById(R.id.radiogroup_permanent_address);
        layout_current_address = view.findViewById(R.id.layout_current_address);
        tv_same_address = view.findViewById(R.id.tv_same_address);
        et_current_address = view.findViewById(R.id.et_current_address);
        et_current_pincode = view.findViewById(R.id.et_current_pincode);

        validateFields();

    }

    private void validateFields() {
        disableFabButton();

        et_firstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    disableFabButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_last_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    disableFabButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_emailid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (GlobalMethods.isValidEmailAddress(s.toString())) {
                    disableFabButton();
                } else {
                    disableFabButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_mobile_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (GlobalMethods.isValidInternationalMobileNumber(s.toString())) {
                    disableFabButton();
                } else {
                    disableFabButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        datepick.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    disableFabButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_permanent_address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    disableFabButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_permanent_pincode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s)) {
                    disableFabButton();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


    //spinners
    private void setDataToSpinner() {
        List<String> gender = new ArrayList<>();
        gender.add("Gender");
        gender.add("Male");
        gender.add("Female");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, R.layout.activity_spinner_dialogue, gender);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_gender.setAdapter(arrayAdapter);
        spinner_gender.setSelection(position);
        arrayAdapter.notifyDataSetChanged();

        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = gender.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> countries = new ArrayList<>();
        countries.add("America");
        countries.add("Australia");
        countries.add("India");
        ArrayAdapter<String>  arrayAdapter1 = new ArrayAdapter<>(mContext, R.layout.activity_spinner_dialogue, countries);
        arrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_country_origin.setAdapter(arrayAdapter1);
        spinner_country_origin.setSelection(position);
        arrayAdapter1.notifyDataSetChanged();

        spinner_country_origin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = countries.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> states = new ArrayList<>();
        states.add("State");
        states.add("Andhra Pradesh");
        states.add("Karnataka");
        states.add("Telangana");
        ArrayAdapter<String>  stateAdapter = new ArrayAdapter<>(mContext, R.layout.activity_spinner_dialogue, states);
        stateAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_states.setAdapter(stateAdapter);
        spinner_states.setSelection(position);
        stateAdapter.notifyDataSetChanged();

        ArrayAdapter<String>  countrystateAdapter = new ArrayAdapter<>(mContext, R.layout.activity_spinner_dialogue, states);
        countrystateAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        country_state.setAdapter(countrystateAdapter);
        country_state.setSelection(position);
        countrystateAdapter.notifyDataSetChanged();

        spinner_states.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPermanentState = states.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        country_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = states.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<String> city = new ArrayList<>();
        city.add("City");
        city.add("Hyderabad");
        city.add("Bangalore");

        ArrayAdapter<String>  cityAdapter = new ArrayAdapter<>(mContext, R.layout.activity_spinner_dialogue, city);
        cityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_cities.setAdapter(cityAdapter);
        spinner_cities.setSelection(position);
        cityAdapter.notifyDataSetChanged();

        ArrayAdapter<String>  currentcityAdapter = new ArrayAdapter<>(mContext, R.layout.activity_spinner_dialogue, city);
        currentcityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        country_city.setAdapter(currentcityAdapter);
        country_city.setSelection(position);
        currentcityAdapter.notifyDataSetChanged();

        spinner_cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPermanentCity = city.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        country_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = city.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void actionEvents(View view) {
        view.findViewById(R.id.date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    calendar = Calendar.getInstance();
                    DatePickerDialog datePickerDialog = new DatePickerDialog(mContext);
                    datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            calendar.set(Calendar.YEAR, year);
                            calendar.set(Calendar.MONTH, month);
                            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            updateLabel();
                        }
                    });
                    datePickerDialog.show();
                }
            }
        });

        radiogroup_permanent_address.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                if (index == 0) {
                    layout_current_address.setVisibility(View.GONE);
                    tv_same_address.setVisibility(View.VISIBLE);
                    String permanentAddress = et_permanent_address.getText().toString();
                    String state = selectedPermanentState;
                    String city = selectedPermanentCity;
                    String postalcode = et_permanent_pincode.getText().toString();

                    if (permanentAddress == null || permanentAddress.isEmpty()
                            || state == null || state.isEmpty() || city == null || city.isEmpty() || postalcode == null || postalcode.isEmpty()) {
                        tv_same_address.setVisibility(View.GONE);
                        disableFabButton();
                    } else {
                        tv_same_address.setText(permanentAddress + "," + state + "," + city + "," + postalcode);
                        tv_same_address.setVisibility(View.VISIBLE);
                        enableFabButton();
                    }

                } else {
                    disableFabButton();
                    layout_current_address.setVisibility(View.VISIBLE);
                    tv_same_address.setVisibility(View.GONE);

                    /*If current address not same as permanent restrict button enabling*/

                    et_current_address.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (TextUtils.isEmpty(s)){
                                disableFabButton();
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });

                    et_current_pincode.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            if (TextUtils.isEmpty(s)){
                                disableFabButton();
                            }else{
                                enableFabButton();
                            }
                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                }

            }
        });

    }

    private void updateLabel() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        startDate = dateFormat.format(calendar.getTime());
        datepick.setText(startDate);
    }

    public void enableFabButton() {
        GlobalMethods.enableFabNext(mContext, fab_next);
    }

    public void disableFabButton() {
        GlobalMethods.disableFabNext(mContext, fab_next);
    }

    private void handleTextError(boolean isValid, String textError) {
        if (isValid) {
            GlobalMethods.showNormalToast((Activity) mContext, textError, 0);
        } else {
            GlobalMethods.showNormalToast((Activity) mContext, textError, 0);
        }
    }


}