package com.va.neoapp.presentation.home.activities;

import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.va.neoapp.R;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.presentation.home.fragments.childfragment.FragmentAdapter;

public class HomeUniversityDetails extends BaseActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    Context mContext;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_home_university_details;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        mContext=this;
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText(mContext.getResources().getString(R.string.know_more)));
        tabLayout.addTab(tabLayout.newTab().setText(mContext.getResources().getString(R.string.updates)));
        tabLayout.addTab(tabLayout.newTab().setText(mContext.getResources().getString(R.string.courses)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        //Creating our pager adapter
        final FragmentAdapter adapter = new FragmentAdapter(mContext, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               viewPager.setCurrentItem(tab.getPosition(),true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected void initData() {

    }

}