package com.va.neoapp.presentation.boarding.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.va.neoapp.R;
import com.va.neoapp.adapters.HomeViewPagerAdapter;
import com.va.neoapp.custom.stepper.StepView;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.presentation.boarding.fragments.PersonalInfoFragment;
import com.va.neoapp.presentation.boarding.fragments.ProgramInfoFragment;
import com.va.neoapp.presentation.boarding.fragments.ReviewFragment;
import com.va.neoapp.presentation.boarding.fragments.SubmitFragment;
import com.va.neoapp.presentation.boarding.fragments.TravelFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniversityBoardingAct extends BaseActivity {

    private int currentStep = 0;
    private ViewPager viewPager;
    private StepView stepView;

    private String[] stepTextArray = {"Personal Information", "Program Information", "Travel", "Review", "Submit"};

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_university_boarding;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        viewPager = findViewById(R.id.on_boarding_viewpager);
        stepView = (StepView) findViewById(R.id.step_view);

        List<String> stepList = new ArrayList<>(Arrays.asList(stepTextArray));
        stepView.setSteps(stepList);

        stepView.setOnStepClickListener(new StepView.OnStepClickListener() {
            @Override
            public void onStepClick(int step) {
                Toast.makeText(UniversityBoardingAct.this, "Step " + step, Toast.LENGTH_SHORT).show();
               /* switch (step){

                }*/
                currentStep = step;
                viewPager.setCurrentItem(step);
                stepView.done(false);
                stepView.go(step, true);
            }
        });

        findViewById(R.id.fab_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentStep < stepView.getStepCount() - 1) {
                    currentStep++;
                    stepView.go(currentStep, true);
                    viewPager.setCurrentItem(currentStep);
                } else {
                    stepView.done(true);
                }
            }
        });
    }

    @Override
    protected void initData() {


        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        homeViewPagerAdapter.addFrag(new PersonalInfoFragment(), getString(R.string.text_personal));
        homeViewPagerAdapter.addFrag(new ProgramInfoFragment(), getString(R.string.text_program));
        homeViewPagerAdapter.addFrag(new TravelFragment(), getString(R.string.text_travel));
        homeViewPagerAdapter.addFrag(new ReviewFragment(), getString(R.string.text_review));
        homeViewPagerAdapter.addFrag(new SubmitFragment(), getString(R.string.text_submit));
        viewPager.setAdapter(homeViewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
               // stepView.go(position, true);
            }
            @Override
            public void onPageSelected(int position) {
                currentStep =position;
                stepView.go(position, true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
