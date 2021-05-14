package com.va.neoapp.presentation.homeservices.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.va.neoapp.R;
import com.va.neoapp.models.Coursesmodel;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.util.GlobalMethods;

public class CourseInDetailsActivity extends BaseActivity {

    private Context mContext;
    private AppCompatImageView image_cancel;
    private AppCompatTextView tv_courseName, tv_courseType, tv_coursePeriod,
            tv_courseTimeperiod, tv_courseIntake, tv_courseEligible, tv_courseDescription;

    private AppCompatButton button_apply;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_course_in_details;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        mContext=this;
        tv_courseName = findViewById(R.id.tv_courseName);
        tv_courseType = findViewById(R.id.tv_courseType);
        tv_coursePeriod = findViewById(R.id.tv_coursePeriod);
        tv_courseTimeperiod = findViewById(R.id.tv_courseTimeperiod);
        tv_courseIntake = findViewById(R.id.tv_courseIntake);
        tv_courseEligible = findViewById(R.id.tv_courseEligible);
        tv_courseDescription = findViewById(R.id.tv_courseDescription);
        button_apply = findViewById(R.id.button_apply);
        image_cancel = findViewById(R.id.image_cancel);

        Bundle extras = getIntent().getExtras();
        Coursesmodel coursesmodels = (Coursesmodel) extras.getSerializable("course");

        if (coursesmodels != null) {
            tv_courseName.setText(coursesmodels.getCourseName());
            tv_courseType.setText(coursesmodels.getCourseType()+" ");
            tv_coursePeriod.setText(" "+coursesmodels.getCoursePeriod()+" ");
            tv_courseTimeperiod.setText(" "+coursesmodels.getCourseTimePeriod());
            tv_courseIntake.setText("Intake: "+coursesmodels.getIntakePeriod());
            tv_courseEligible.setText("Eligibility Criteria: "+coursesmodels.getEligibilityCriteria());
            tv_courseDescription.setText(coursesmodels.getCourseDescription());
        }

        image_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMethods.callFinishForBackWordActivity(mContext,true);
            }
        });

        button_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMethods.callForWordActivity(mContext,SubmitApplicationActivity.class,null,false,true);
        }
        });

    }

    @Override
    protected void initData() {

    }
}