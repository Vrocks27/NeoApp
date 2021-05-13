package com.va.neoapp.presentation.homeservices.activities;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;

import com.va.neoapp.R;
import com.va.neoapp.presentation.BaseActivity;

public class StudentLifeActivity extends BaseActivity {

    private Context mContext;
    private AppCompatTextView header_text;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_student_life;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        mContext=this;
        header_text=findViewById(R.id.heading_text);

        header_text.setText("Student Life at Campus");
    }

    @Override
    protected void initData() {

    }
}
