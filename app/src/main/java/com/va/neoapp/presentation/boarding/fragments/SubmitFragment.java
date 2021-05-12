package com.va.neoapp.presentation.boarding.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.va.neoapp.R;
import com.va.neoapp.custom.signature.SignatureView;
import com.va.neoapp.presentation.home.activities.UniversityDetailAct;
import com.va.neoapp.util.GlobalMethods;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubmitFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubmitFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static FloatingActionButton fab_next;

    public SubmitFragment(FloatingActionButton fab_next) {
        // Required empty public constructor
        SubmitFragment.fab_next=fab_next;
    }


    private Context mContext;
    private boolean flag = false;
    private AppCompatButton form_submit;
    private RadioGroup isolation_group;
    private RadioButton university_terms;
    private LinearLayout linear_layout_yes;
    private AppCompatTextView tv_contact_university;
    private AppCompatEditText isolation_address, isolation_contact;
    private AppCompatSpinner spinner_isolation_city;
    private CountryCodePicker spinnerCountryCodePicker;

    Button mClear, mGetSign, mCancel;

    AppCompatImageView image_signature;

    File file;
    Dialog dialog;
    LinearLayout mContent;
    SignatureView mSignature;
   // View view;

    // Creating Separate Directory for saving Generated Images
    String DIRECTORY = Environment.getExternalStorageDirectory().getPath() + "/DigitSign/";
    String pic_name = new SimpleDateFormat("dd/mm/yyyy", Locale.getDefault()).format(new Date());
    String StoredPath = DIRECTORY + pic_name + ".png";


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubmitFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubmitFragment newInstance(String param1, String param2) {
        SubmitFragment fragment = new SubmitFragment(fab_next);
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
        View view = inflater.inflate(R.layout.fragment_submit, container, false);
        mContext = getActivity();
        fab_next.setVisibility(View.GONE);
        initGUI(view);
        actionEvents(view);
        setDataToSpinner();
        return view;
    }

    private void initGUI(View view) {
        isolation_group = view.findViewById(R.id.isolation_group);
        linear_layout_yes = view.findViewById(R.id.linear_layout_yes);
        tv_contact_university = view.findViewById(R.id.tv_contact_university);
        university_terms = view.findViewById(R.id.university_terms);
        isolation_address = view.findViewById(R.id.isolation_address);
        isolation_contact = view.findViewById(R.id.isolation_contact);
        spinner_isolation_city = view.findViewById(R.id.spinner_isolation_city);
        image_signature = view.findViewById(R.id.image_signature);
        form_submit = view.findViewById(R.id.form_submit);

    }

    private void actionEvents(View view) {
        disableBottomSubmit();
        isolation_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                if (index == 0) {
                    linear_layout_yes.setVisibility(View.VISIBLE);
                    tv_contact_university.setVisibility(View.GONE);
                    flag = true;
                } else {
                    linear_layout_yes.setVisibility(View.GONE);
                    tv_contact_university.setVisibility(View.VISIBLE);
                    flag = true;
                }

                if (flag) {
                    enableBottomSubmit();
                } else {
                    disableBottomSubmit();
                }

            }
        });


        form_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (!isolation_group.isEnabled()) {
                    Toast.makeText(mContext,"select Please",Toast.LENGTH_SHORT).show();
                }else */
                if (!university_terms.isChecked()) {
                    GlobalMethods.showNormalToast((Activity) mContext, "Please accept arrival services terms and conditions", 1);
                } else {
                    GlobalMethods.callForWordActivity(mContext, UniversityDetailAct.class, null, false, true);
                }

            }
        });


        // Method to create Directory, if the Directory doesn't exists
        file = new File(DIRECTORY);
        if (!file.exists()) {
            file.mkdir();
        }

        // Dialog Function
        dialog = new Dialog(mContext);
        // Removing the features of Normal Dialogs
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialogue_signature);
        dialog.setCancelable(true);

        image_signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Function call for Digital Signature
                dialog_action(view);

            }
        });
    }

    // Function for Digital Signature
    public void dialog_action(View view) {

        mContent = (LinearLayout) dialog.findViewById(R.id.linearLayout);
        mSignature = new SignatureView(mContext, null);
        mSignature.setBackgroundColor(Color.WHITE);
        // Dynamically generating Layout through java code
        mContent.addView(mSignature, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mClear = (Button) dialog.findViewById(R.id.clear);
        mGetSign = (Button) dialog.findViewById(R.id.getsign);
       // mGetSign.setEnabled(false);
        mCancel = (Button) dialog.findViewById(R.id.cancel);
        view = mContent;

        mClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("tag", "Panel Cleared");
                mSignature.clear();
               // mGetSign.setEnabled(false);
            }
        });

        View finalView = view;
        mGetSign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("tag", "Panel Saved");
                finalView.setDrawingCacheEnabled(true);
                mSignature.save(finalView, StoredPath);
                dialog.dismiss();
                Toast.makeText(mContext, "Successfully Saved", Toast.LENGTH_SHORT).show();
                // Calling the same class
                getActivity().recreate();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("tag", "Panel Cancelled");
                dialog.dismiss();
                // Calling the same class
                getActivity().recreate();
            }
        });
        dialog.show();
    }

    private void setDataToSpinner() {

    }


    private void enableBottomSubmit() {
        GlobalMethods.enableSubmitButton(mContext, form_submit);
    }

    private void disableBottomSubmit() {
        GlobalMethods.disableSubmitButton(mContext, form_submit);
    }


}