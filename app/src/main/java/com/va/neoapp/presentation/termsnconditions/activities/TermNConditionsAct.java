package com.va.neoapp.presentation.termsnconditions.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.va.neoapp.R;
import com.va.neoapp.adapters.TermsNConditionsAdapter;
import com.va.neoapp.models.TermsConditions;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.presentation.home.activities.HomeActivity;
import com.va.neoapp.util.GlobalMethods;

import java.util.ArrayList;
import java.util.List;

public class TermNConditionsAct extends BaseActivity {

    private RecyclerView conditionsView;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_terms_conditions;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        conditionsView = findViewById(R.id.conditions_list);
        conditionsView.setLayoutManager(new LinearLayoutManager(this));

        RadioGroup terms_group = findViewById(R.id.terms_group);
        terms_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                View radioButton = radioGroup.findViewById(i);
                int index = radioGroup.indexOfChild(radioButton);
                if (index == 0) {
                    TermsNConditionsAdapter personalAdapter = new TermsNConditionsAdapter(TermNConditionsAct.this, getPersonalSpaceData());
                    conditionsView.setAdapter(personalAdapter);
                } else {
                    TermsNConditionsAdapter universityAdapter = new TermsNConditionsAdapter(TermNConditionsAct.this, getUniversitySpaceData());
                    conditionsView.setAdapter(universityAdapter);
                }
            }
        });

        conditionsView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && findViewById(R.id.fab_next).getVisibility() == View.VISIBLE) {
                    ((FloatingActionButton)findViewById(R.id.fab_next)).hide();
                } else if (dy < 0 && findViewById(R.id.fab_next).getVisibility() != View.VISIBLE) {
                    ((FloatingActionButton)findViewById(R.id.fab_next)).show();
                }
            }
        });
    }

    private List<TermsConditions> getPersonalSpaceData() {
        List<TermsConditions> personalSpaceList = new ArrayList<>();
        personalSpaceList.add(new TermsConditions("Personal Condition 1", getString(R.string.dummy_text)));
        personalSpaceList.add(new TermsConditions("Personal Condition 2", getString(R.string.dummy_text)));
        personalSpaceList.add(new TermsConditions("Personal Condition 3", getString(R.string.dummy_text)));
        personalSpaceList.add(new TermsConditions("Personal Condition 4", getString(R.string.dummy_text)));
        personalSpaceList.add(new TermsConditions("Personal Condition 5", getString(R.string.dummy_text)));
        personalSpaceList.add(new TermsConditions("Personal Condition 6", getString(R.string.dummy_text)));
        personalSpaceList.add(new TermsConditions("Personal Condition 7", getString(R.string.dummy_text)));
        personalSpaceList.add(new TermsConditions("Personal Condition 8", getString(R.string.dummy_text)));
        personalSpaceList.add(new TermsConditions("Personal Condition 9", getString(R.string.dummy_text)));
        return personalSpaceList;
    }

    private List<TermsConditions> getUniversitySpaceData() {
        List<TermsConditions> universityList = new ArrayList<>();
        universityList.add(new TermsConditions("University Condition 1", getString(R.string.dummy_text)));
        universityList.add(new TermsConditions("University Condition 2", getString(R.string.dummy_text)));
        universityList.add(new TermsConditions("University Condition 3", getString(R.string.dummy_text)));
        universityList.add(new TermsConditions("University Condition 4", getString(R.string.dummy_text)));
        universityList.add(new TermsConditions("University Condition 5", getString(R.string.dummy_text)));
        universityList.add(new TermsConditions("University Condition 6", getString(R.string.dummy_text)));
        universityList.add(new TermsConditions("University Condition 7", getString(R.string.dummy_text)));
        universityList.add(new TermsConditions("University Condition 8", getString(R.string.dummy_text)));
        universityList.add(new TermsConditions("University Condition 9", getString(R.string.dummy_text)));
        return universityList;
    }

    @Override
    protected void initData() {
        findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMethods.callFinishForBackWordActivity(TermNConditionsAct.this, false);
            }
        });

        findViewById(R.id.fab_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMethods.callForWordActivity(TermNConditionsAct.this, HomeActivity.class, null, false, true);
            }
        });
    }
}
