package com.va.neoapp.presentation.boarding.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.va.neoapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TravelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TravelFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TravelFragment() {
        // Required empty public constructor
    }

    private Context mContext;
    private CountryCodePicker countryCodePicker;
    private AppCompatSpinner spinner_transport,spinner_departure_time,
            spinner_departure_country,spinner_departure_city,spinner_flights,
            spinner_arrival_country,spinner_arrival_city;
    private AppCompatEditText et_departure_date,et_final_departure_date;
    private int position;
    private String selectedUom;
    private String startDate;
    private AppCompatEditText datepick;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TravelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TravelFragment newInstance(String param1, String param2) {
        TravelFragment fragment = new TravelFragment();
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_travel, container, false);
        mContext=getActivity();
        initGUI(view);
        actionEvents(view);
        setDataToSpinner();
        return view;
    }

    private void setDataToSpinner() {
        List<String> mode_of_transport = new ArrayList<>();
        mode_of_transport.add("Select Transport");
        mode_of_transport.add("By Road");
        mode_of_transport.add("By Flight");

        ArrayAdapter transportArrayAdapter = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, mode_of_transport);
        transportArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_transport.setAdapter(transportArrayAdapter);
        spinner_transport.setSelection(position);
        transportArrayAdapter.notifyDataSetChanged();

        spinner_transport.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = mode_of_transport.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*spinner country*/
        List<String> countries = new ArrayList<>();
        countries.add("Select Country");
        countries.add("America");
        countries.add("Australia");
        countries.add("India");
        ArrayAdapter arrayAdapter1 = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, countries);
        arrayAdapter1.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_departure_country.setAdapter(arrayAdapter1);
        spinner_departure_country.setSelection(position);
        arrayAdapter1.notifyDataSetChanged();

        spinner_departure_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = countries.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*spinner city*/
        List<String> city = new ArrayList<>();
        city.add("Select City");
        city.add("Hyderabad");
        city.add("Bangalore");
        city.add("chennai");
        ArrayAdapter stateAdapter = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, city);
        stateAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_departure_city.setAdapter(stateAdapter);
        spinner_departure_city.setSelection(position);
        stateAdapter.notifyDataSetChanged();

        spinner_departure_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = city.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*spinner timer*/
        List<String> timer = new ArrayList<>();
        timer.add("00:00");
        timer.add("12:00 AM");
        timer.add("1:00 PM");
        timer.add("04:00 PM");
        timer.add("12:00 PM");
        ArrayAdapter timerAdapter = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, timer);
        timerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_departure_time.setAdapter(timerAdapter);
        spinner_departure_time.setSelection(position);
        timerAdapter.notifyDataSetChanged();

        spinner_departure_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = timer.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*spinner of flights*/
        List<String> fligths = new ArrayList<>();
        fligths.add("0");
        fligths.add("1");
        fligths.add("2");
        fligths.add("3");
        fligths.add("4");
        ArrayAdapter flightAdapter = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, fligths);
        flightAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_flights.setAdapter(flightAdapter);
        spinner_flights.setSelection(position);
        flightAdapter.notifyDataSetChanged();
        spinner_flights.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom=fligths.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Spinner Arrival Country*/
        List<String> arrivalCountries = new ArrayList<>();
        arrivalCountries.add("Select Country");
        arrivalCountries.add("America");
        arrivalCountries.add("Australia");
        arrivalCountries.add("India");
        ArrayAdapter arrivalCountryArrayAdapter = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, arrivalCountries);
        arrivalCountryArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_arrival_country.setAdapter(arrivalCountryArrayAdapter);
        spinner_arrival_country.setSelection(position);
        arrivalCountryArrayAdapter.notifyDataSetChanged();

        spinner_arrival_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = arrivalCountries.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /*Spinner arrival city*/
        List<String> arrivalCity = new ArrayList<>();
        arrivalCity.add("Select Country");
        arrivalCity.add("America");
        arrivalCity.add("Australia");
        arrivalCity.add("India");
        ArrayAdapter arrivalCityArrayAdapter = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, arrivalCity);
        arrivalCityArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_arrival_country.setAdapter(arrivalCityArrayAdapter);
        spinner_arrival_country.setSelection(position);
        arrivalCityArrayAdapter.notifyDataSetChanged();

        spinner_arrival_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = arrivalCity.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void actionEvents(View view) {
        et_departure_date.setOnClickListener(new View.OnClickListener() {
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

        et_final_departure_date.setOnClickListener(new View.OnClickListener() {
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
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        startDate = dateFormat.format(calendar.getTime());
        et_departure_date.setText(startDate);
        et_final_departure_date.setText(startDate);
    }

    private void initGUI(View view) {
        countryCodePicker = view.findViewById(R.id.select_code_spinner);
        spinner_flights = view.findViewById(R.id.spinner_flights);
        spinner_transport = view.findViewById(R.id.spinner_transport);
        spinner_departure_city = view.findViewById(R.id.spinner_departure_city);
        spinner_departure_country = view.findViewById(R.id.spinner_departure_country);
        spinner_departure_time = view.findViewById(R.id.spinner_departure_time);
        et_departure_date= view.findViewById(R.id.et_departure_date);
        spinner_arrival_country= view.findViewById(R.id.spinner_arrival_country);
        spinner_arrival_city= view.findViewById(R.id.spinner_arrival_city);
        et_final_departure_date= view.findViewById(R.id.et_final_departure_date);
    }
}