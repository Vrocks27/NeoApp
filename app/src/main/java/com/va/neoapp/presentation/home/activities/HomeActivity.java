package com.va.neoapp.presentation.home.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.va.neoapp.R;
import com.va.neoapp.adapters.HomeViewPagerAdapter;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.presentation.home.fragments.ChatFragment;
import com.va.neoapp.presentation.home.fragments.HomeFragment;
import com.va.neoapp.presentation.home.fragments.ProfileFragment;
import com.va.neoapp.presentation.home.fragments.SupportFragment;
import com.va.neoapp.presentation.home.fragments.TagFragment;


public class HomeActivity extends BaseActivity {

    private TabLayout tabLayout;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {

        ViewPager viewPager = findViewById(R.id.home_viewpager);
        readBundle();
        setupViewPager(viewPager);
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorHeight(0);
        actionEvents();
        setupTabIcons();
    }

    private void setupTabIcons() {

        LinearLayout tabtag = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null, false);
        AppCompatImageView tag_image = tabtag.findViewById(R.id.home_button_img);
        tag_image.setImageResource(R.drawable.ic_tag);
        tabLayout.getTabAt(0).setCustomView(tabtag);

        LinearLayout tabSupport = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null, false);
        AppCompatImageView support_image = tabSupport.findViewById(R.id.home_button_img);
        support_image.setImageResource(R.drawable.ic_support);
        tabLayout.getTabAt(1).setCustomView(tabSupport);

        LinearLayout tabHome = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null, false);
        AppCompatImageView home_image = tabHome.findViewById(R.id.home_button_img);
        home_image.setImageResource(R.drawable.ic_home);
        tabLayout.getTabAt(2).setCustomView(tabHome);

        LinearLayout tabProfile = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null, false);
        AppCompatImageView profile_image = tabProfile.findViewById(R.id.home_button_img);
        profile_image.setImageResource(R.drawable.ic_profile);
        tabLayout.getTabAt(3).setCustomView(tabProfile);

        LinearLayout tabChat = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.custom_tab, null, false);
        AppCompatImageView chat_image = tabChat.findViewById(R.id.home_button_img);
        chat_image.setImageResource(R.drawable.ic_chat);
        tabLayout.getTabAt(4).setCustomView(tabChat);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {

                }
                if (tab.getPosition() == 1) {

                }
                if (tab.getPosition() == 2) {

                }
                if (tab.getPosition() == 3) {

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void actionEvents() {

    }

    private void setupViewPager(ViewPager viewPager) {

        // Fragment overviewFrag = HomeFragment.newInstance();

        HomeViewPagerAdapter homeViewPagerAdapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        homeViewPagerAdapter.addFrag(new TagFragment(), getString(R.string.text_support));
        homeViewPagerAdapter.addFrag(new SupportFragment(), getString(R.string.text_support));
        homeViewPagerAdapter.addFrag(new HomeFragment(), getString(R.string.text_home));
        homeViewPagerAdapter.addFrag(new ProfileFragment(), getString(R.string.text_profile));
        homeViewPagerAdapter.addFrag(new ChatFragment(), getString(R.string.text_chat));
        viewPager.setAdapter(homeViewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position == 0) {
//
//                } else if (position == 1) {
//
//
//                } else if (position == 2) {
//
//                } else if (position == 3) {
//
//                }
            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void readBundle() {

    }

    @Override
    protected void initData() {

    }
}
