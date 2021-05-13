package com.va.neoapp.presentation.boarding.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
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
import com.va.neoapp.custom.signatureview.SignatureView;
import com.va.neoapp.presentation.home.activities.UniversityDetailAct;
import com.va.neoapp.util.GlobalMethods;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        SubmitFragment.fab_next = fab_next;
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

    private AppCompatImageView image_signature;

    File file;
    Dialog dialog;
    LinearLayout mContent;

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
//        file = new File(DIRECTORY);
//        if (!file.exists()) {
//            file.mkdir();
//        }

        view.findViewById(R.id.image_signature).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSignaturePad(image_signature);
            }
        });
    }

    private void showSignaturePad(AppCompatImageView image_signature) {

        try {
            Dialog dialog = new Dialog(getContext());
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.layout_dialog_signature);
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.setCanceledOnTouchOutside(false);

            SignatureView signatureView = dialog.findViewById(R.id.signature_view);
            dialog.findViewById(R.id.img_close_option).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });

            dialog.findViewById(R.id.bt_reset).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signatureView.clearCanvas();
                }
            });

            dialog.findViewById(R.id.bt_done).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!signatureView.isBitmapEmpty()) {
                        Bitmap bitmap = signatureView.getSignatureBitmap();
                        image_signature.setImageBitmap(bitmap);
                        dialog.dismiss();
                        generateFile(bitmap);
                    }else {
                        GlobalMethods.showNormalToast(getActivity(), "Please do the signature to proceed.", 0);
                    }
                }
            });

            dialog.show();
        } catch (Exception exp) {
            Log.e("sign_dia_exp", exp.getMessage());
        }
    }

    private void generateFile(Bitmap bitmap) {
        // check the permission storage
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File file = new File(directory, System.currentTimeMillis() + ".png");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            if (bitmap != null) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            } else {
                throw new FileNotFoundException();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();

                    if (bitmap != null) {

                        Toast.makeText(getContext(), "Image saved successfully at " + file.getPath(), Toast.LENGTH_LONG).show();
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                            new MyMediaScanner(getContext(), file);
                        } else {
                            ArrayList<String> toBeScanned = new ArrayList<String>();
                            toBeScanned.add(file.getAbsolutePath());
                            String[] toBeScannedStr = new String[toBeScanned.size()];
                            toBeScannedStr = toBeScanned.toArray(toBeScannedStr);
                            MediaScannerConnection.scanFile(getContext(), toBeScannedStr, null, null);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MyMediaScanner implements MediaScannerConnection.MediaScannerConnectionClient {

        private MediaScannerConnection mSC;
        private File file;

        MyMediaScanner(Context context, File file) {
            this.file = file;
            mSC = new MediaScannerConnection(context, this);
            mSC.connect();
        }

        @Override
        public void onMediaScannerConnected() {
            mSC.scanFile(file.getAbsolutePath(), null);
        }

        @Override
        public void onScanCompleted(String path, Uri uri) {
            mSC.disconnect();
        }
    }
    // Function for Digital Signature
    /*public void dialog_action(View view) {

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
    }*/

    private void setDataToSpinner() {

    }


    private void enableBottomSubmit() {
        GlobalMethods.enableSubmitButton(mContext, form_submit);
    }

    private void disableBottomSubmit() {
        GlobalMethods.disableSubmitButton(mContext, form_submit);
    }


}