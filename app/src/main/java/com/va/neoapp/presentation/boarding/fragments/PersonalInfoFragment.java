package com.va.neoapp.presentation.boarding.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.va.neoapp.R;

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

    public PersonalInfoFragment() {
        // Required empty public constructor
    }


    private Context mContext;
    private CountryCodePicker select_code_spinner;
    private AppCompatSpinner spinner_gender, spinner_country_origin, spinner_states, spinner_cities, country_state, country_city;
    private int position;
    private String selectedUom;
    private String startDate;
    private AppCompatEditText datepick;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;


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
        mContext = getActivity();
        initGUI(view);
        actionEvents(view);
        setDataToSpinner();

        return view;
    }

    private void setDataToSpinner() {
        List<String> gender = new ArrayList<>();
        gender.add("Gender");
        gender.add("Male");
        gender.add("Female");

        ArrayAdapter arrayAdapter = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, gender);
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
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, countries);
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
        ArrayAdapter stateAdapter = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, states);
        stateAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_states.setAdapter(stateAdapter);
        spinner_states.setSelection(position);
        stateAdapter.notifyDataSetChanged();

        ArrayAdapter countrystateAdapter = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, states);
        countrystateAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        country_state.setAdapter(countrystateAdapter);
        country_state.setSelection(position);
        countrystateAdapter.notifyDataSetChanged();

        spinner_states.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = states.get(position);
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

        ArrayAdapter cityAdapter = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, city);
        cityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_cities.setAdapter(cityAdapter);
        spinner_cities.setSelection(position);
        cityAdapter.notifyDataSetChanged();

        ArrayAdapter currentcityAdapter = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, city);
        currentcityAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        country_city.setAdapter(currentcityAdapter);
        country_city.setSelection(position);
        currentcityAdapter.notifyDataSetChanged();
        ;

        spinner_cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = city.get(position);
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

        datepick.setOnClickListener(new View.OnClickListener() {
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

    }

    private void updateLabel() {
        dateFormat = new SimpleDateFormat("dd/MM/yyyy",Locale.getDefault());
        startDate = dateFormat.format(calendar.getTime());
        datepick.setText(startDate);
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
    }
}