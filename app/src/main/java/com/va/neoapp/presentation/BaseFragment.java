package com.va.neoapp.presentation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGUI(savedInstanceState);
        initData();
    }

    protected abstract void initGUI(Bundle savedInstanceState);


    protected abstract void initData();




   /* public interface Callback {
        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }*/


}
