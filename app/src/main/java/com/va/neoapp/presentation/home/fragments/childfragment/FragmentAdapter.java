package com.va.neoapp.presentation.home.fragments.childfragment;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {

    private Context myContext;
    int totalTabs;

    public FragmentAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                KnowMoreFragment knowMoreFragment=new KnowMoreFragment();
                return  knowMoreFragment;

               // ((BaseActivity)myContext).replaceFragment(KnowMoreFragment.class, R.id.pager,null);
            case 1:
                UpdateFragment updateFragment = new UpdateFragment();
                return updateFragment;
            case 2:
                CoursesFragment coursesFragment = new CoursesFragment();
                return coursesFragment;
            default:
                return null;
        }
    }

    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}