package com.va.neoapp.presentation.home.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.va.neoapp.R;
import com.va.neoapp.adapters.ImagesViewPagerAdapter;
import com.va.neoapp.presentation.home.activities.UniversityDetailAct;
import com.va.neoapp.util.GlobalMethods;

public class HomeFragment extends Fragment {

    private ViewPager viewPager;
    private LinearLayout sliderDots;
    private Context mContext;
    private int dotscount;
    private ImageView[] dots;


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        readBundle();
        initGUI(view);
        initData();
        actionEvents(view);
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void actionEvents(View view) {
        view.findViewById(R.id.cardview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getActivity(), UniversityDetailAct.class));
                GlobalMethods.callForWordActivity(getActivity(), UniversityDetailAct.class, null, false, true);

            }
        });
    }

    private void initData() {

    }

    private void initGUI(View view) {
        mContext = getActivity();
        viewPager = view.findViewById(R.id.viewpager);
        sliderDots = view.findViewById(R.id.sliderDots);

        ImagesViewPagerAdapter viewPagerAdapter = new ImagesViewPagerAdapter(mContext);
        viewPagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(viewPagerAdapter);

        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for (int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(mContext);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDots.addView(dots[i], params);

        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void readBundle() {
        Bundle bundle = this.getArguments();
        if (bundle != null) {

        }
    }
}
