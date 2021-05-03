package com.va.neoapp.presentation.home.fragments.childfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.va.neoapp.R;
import com.va.neoapp.presentation.BaseFragment;

public class KnowMoreFragment extends BaseFragment {

    Context mContext;

    @Override
    protected View setLayoutResource(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView=inflater.inflate(R.layout.fragment_home_knowmore,container,false);
        mContext=getActivity();
        return myView;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }
}
