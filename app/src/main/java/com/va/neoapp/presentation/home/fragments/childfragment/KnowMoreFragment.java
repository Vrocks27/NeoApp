package com.va.neoapp.presentation.home.fragments.childfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.va.neoapp.R;
import com.va.neoapp.presentation.BaseFragment;

public class KnowMoreFragment extends BaseFragment {

    private Context mContext;
    private AppCompatTextView tv_faq, tv_overview;
    private ViewGroup view_layout;
    private RecyclerView rv_faq;
    private LinearLayout second_Linearlayout;

    @Override
    protected View setLayoutResource(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_home_knowmore, container, false);
        mContext = getActivity();
        initGUI(myView, savedInstanceState);
        return myView;
    }

    @Override
    protected void initGUI(View view, Bundle savedInstanceState) {
        tv_faq = view.findViewById(R.id.tv_faq);
        tv_overview = view.findViewById(R.id.tv_overview);
        view_layout = view.findViewById(R.id.view_layout);
        rv_faq = view.findViewById(R.id.rv_faq);
        second_Linearlayout = view.findViewById(R.id.second_Linearlayout);

        tv_overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_overview.setTextColor(mContext.getResources().getColor(R.color.default_main_color));
                tv_faq.setTextColor(mContext.getResources().getColor(R.color.default_text_color));
                view_layout.setVisibility(View.VISIBLE);
                second_Linearlayout.setVisibility(View.VISIBLE);
                rv_faq.setVisibility(View.GONE);

            }
        });

        tv_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_faq.setTextColor(mContext.getResources().getColor(R.color.default_main_color));
                tv_overview.setTextColor(mContext.getResources().getColor(R.color.default_text_color));
                view_layout.setVisibility(View.GONE);
                second_Linearlayout.setVisibility(View.GONE);
                rv_faq.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    protected void initData() {

    }
}
