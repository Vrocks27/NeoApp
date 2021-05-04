package com.va.neoapp.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = setLayoutResource(inflater, container, savedInstanceState);
        initGUI(view,savedInstanceState);
        initData();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected abstract View setLayoutResource(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    protected abstract void initGUI(View view, Bundle savedInstanceState);

    protected abstract void initData();


   /* public interface Callback {
        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }*/


}
