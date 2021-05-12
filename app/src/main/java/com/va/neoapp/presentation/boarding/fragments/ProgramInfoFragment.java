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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.va.neoapp.R;
import com.va.neoapp.presentation.boarding.activities.UniversityBoardingAct;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProgramInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProgramInfoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static FloatingActionButton fab_next;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProgramInfoFragment(FloatingActionButton fab_next) {
        this.fab_next=fab_next;
    }

    private Context mContext;
    private AppCompatSpinner spinner_universties;
    private AppCompatEditText visa_date;
    private int position;
    private String selectedUom;
    private Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    private String startDate;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProgramInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProgramInfoFragment newInstance(String param1, String param2) {
        ProgramInfoFragment fragment = new ProgramInfoFragment(fab_next);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_program_info, container, false);
        mContext=getActivity();
        fab_next.setVisibility(View.VISIBLE);
        initGUI(view);
        actionEvents(view);
        setDataToSpinner();
        return view;
    }

    private void setDataToSpinner() {
        List<String> university = new ArrayList<>();
        university.add("Select University");
        university.add("Toronto University");
        university.add("Oxford University");
        university.add("VIT University");

        ArrayAdapter arrayAdapter = new ArrayAdapter(mContext, R.layout.activity_spinner_dialogue, university);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_universties.setAdapter(arrayAdapter);
        spinner_universties.setSelection(position);
        arrayAdapter.notifyDataSetChanged();

        spinner_universties.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUom = university.get(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void actionEvents(View view) {
        visa_date.setOnClickListener(new View.OnClickListener() {
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
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        startDate = simpleDateFormat.format(calendar.getTime());
        visa_date.setText(startDate);
    }

    private void initGUI(View view) {
        spinner_universties=view.findViewById(R.id.spinner_universties);
        visa_date=view.findViewById(R.id.visa_date);
    }
}