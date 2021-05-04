package com.va.neoapp.presentation.home.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.va.neoapp.R;
import com.va.neoapp.adapters.HomeViewPagerAdapter;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.presentation.home.fragments.childfragment.CoursesFragment;
import com.va.neoapp.presentation.home.fragments.childfragment.KnowMoreFragment;
import com.va.neoapp.util.GlobalMethods;

public class UniversityDetailAct extends BaseActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Context mContext;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_home_university_details;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        mContext = this;
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText(mContext.getResources().getString(R.string.know_more)));
        tabLayout.addTab(tabLayout.newTab().setText(mContext.getResources().getString(R.string.courses)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        //Creating our pager adapter
//        final FragmentAdapter adapter = new FragmentAdapter(mContext, getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);

        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        homeViewPagerAdapter.addFrag(new KnowMoreFragment(), getString(R.string.know_more));
        homeViewPagerAdapter.addFrag(new CoursesFragment(), getString(R.string.courses));
        viewPager.setAdapter(homeViewPagerAdapter);

        //Adding onTabSelectedListener to swipe views
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);
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
        findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMethods.callFinishForBackWordActivity(UniversityDetailAct.this, false);
            }
        });
    }

}