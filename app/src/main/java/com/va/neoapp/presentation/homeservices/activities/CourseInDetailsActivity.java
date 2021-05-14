package com.va.neoapp.presentation.homeservices.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
        mContext = this;
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
            tv_courseType.setText(coursesmodels.getCourseType() + " ");
            tv_coursePeriod.setText(" " + coursesmodels.getCoursePeriod() + " ");
            tv_courseTimeperiod.setText(" " + coursesmodels.getCourseTimePeriod());
            tv_courseIntake.setText("Intake: " + coursesmodels.getIntakePeriod());
            tv_courseEligible.setText("Eligibility Criteria: " + coursesmodels.getEligibilityCriteria());
            tv_courseDescription.setText(coursesmodels.getCourseDescription());
        }

        image_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMethods.callFinishForBackWordActivity(mContext, true);
            }
        });

        button_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GlobalMethods.callForWordActivity(mContext,SubmitApplicationActivity.class,null,false,true);

                showApplicationFullDialog();
            }
        });

    }

    private void showApplicationFullDialog() {
        try {
            Dialog dialog = new Dialog(CourseInDetailsActivity.this, android.R.style.Theme_DeviceDefault_Light_NoActionBar);
            dialog.setContentView(R.layout.layout_course_apply);
            dialog.setCancelable(true);

           /* ((AppCompatTextView) dialog.findViewById(R.id.text_title_full_option)).setText(title);
            ((AppCompatTextView) dialog.findViewById(R.id.tv_descriptions)).setText(description);
            AppCompatImageView image_full_screen = dialog.findViewById(R.id.image_full_screen);*/

           /* if (GlobalMethods.isNull(imageUrl)) {
                Glide.with(activity).load(imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        *//*.placeholder(R.drawable.default_university)*//*
                        //.crossFade()
                        .error(R.drawable.default_university)
                        .into(image_full_screen);
               *//* Glide.with(activity)
                        .load(imageUrl)
                        .asBitmap()
                        .placeholder(R.drawable.default_university)
                        .error(R.drawable.default_university)
                        .override(250, 250)
                        .into(new BitmapImageViewTarget(image_full_screen) {
                            @Override
                            protected void setResource(Bitmap resource) {
                                RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(activity.getResources(), resource);
                                circularBitmapDrawable.setCircular(true);
                                image_full_screen.setImageDrawable(circularBitmapDrawable);
                            }
                        });*//*
            } else {
                image_full_screen.setImageDrawable(activity.getResources().getDrawable(R.drawable.default_university));
            }
*/
            dialog.findViewById(R.id.image_close_option).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();

        } catch (Exception exp) {
            Log.e("display_full_dia_exp", exp.getMessage());
        }
    }

    @Override
    protected void initData() {

    }
}