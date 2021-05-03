package com.va.neoapp.presentation.home.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.va.neoapp.R;

public class TagFragment extends Fragment {

    public static TagFragment newInstance() {
        return new TagFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        readBundle();
        initGUI(view);
        initData();
        actionEvents(view);
        return view;
        // return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void actionEvents(View view) {

    }

    private void initData() {

    }

    private void initGUI(View view) {

    }

    private void readBundle() {

    }
}

