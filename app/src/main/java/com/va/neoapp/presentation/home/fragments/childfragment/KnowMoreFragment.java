package com.va.neoapp.presentation.home.fragments.childfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.va.neoapp.R;
import com.va.neoapp.adapters.FaqAdapter;
import com.va.neoapp.adapters.OverViewAdapter;
import com.va.neoapp.models.FaqModel;
import com.va.neoapp.models.HomeGrid;
import com.va.neoapp.models.OverviewModel;
import com.va.neoapp.presentation.BaseFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class KnowMoreFragment extends BaseFragment {

    private Context mContext;
    private AppCompatTextView tv_faq, tv_overview;
    private RecyclerView rv_faq,rv_overview;
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
        rv_overview = view.findViewById(R.id.rv_overview);
        rv_faq = view.findViewById(R.id.rv_faq);
        second_Linearlayout = view.findViewById(R.id.second_Linearlayout);

        tv_overview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_overview.setTextColor(mContext.getResources().getColor(R.color.default_main_color));
                tv_faq.setTextColor(mContext.getResources().getColor(R.color.default_text_color));
                second_Linearlayout.setVisibility(View.VISIBLE);
                rv_faq.setVisibility(View.GONE);
                rv_overview.setVisibility(View.VISIBLE);

            }
        });

        tv_faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_faq.setTextColor(mContext.getResources().getColor(R.color.default_main_color));
                tv_overview.setTextColor(mContext.getResources().getColor(R.color.default_text_color));
                second_Linearlayout.setVisibility(View.GONE);
                rv_faq.setVisibility(View.VISIBLE);
                rv_overview.setVisibility(View.GONE);
            }
        });


        //set data to adapter

        List<FaqModel> faqModel = new ArrayList<>();
        faqModel.add(new FaqModel(mContext.getResources().getString(R.string.question),mContext.getResources().getString(R.string.answer)));
        faqModel.add(new FaqModel(mContext.getResources().getString(R.string.question),mContext.getResources().getString(R.string.answer)));
        faqModel.add(new FaqModel(mContext.getResources().getString(R.string.question),mContext.getResources().getString(R.string.answer)));
        faqModel.add(new FaqModel(mContext.getResources().getString(R.string.question),mContext.getResources().getString(R.string.answer)));
        faqModel.add(new FaqModel(mContext.getResources().getString(R.string.question),mContext.getResources().getString(R.string.answer)));
        faqModel.add(new FaqModel(mContext.getResources().getString(R.string.question),mContext.getResources().getString(R.string.answer)));

        FaqAdapter faqAdapter=new FaqAdapter(mContext,faqModel);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        rv_faq.setLayoutManager(linearLayoutManager);
        faqAdapter.notifyDataSetChanged();
        rv_faq.setAdapter(faqAdapter);


        //set data to overview Adapter
        List<OverviewModel> overviewModelList=new ArrayList<>();
        overviewModelList.add(new OverviewModel(mContext.getResources().getDrawable(R.drawable.icon_university),"Year of EstablishMent","1994"));
        overviewModelList.add(new OverviewModel(mContext.getResources().getDrawable(R.drawable.icon_staff),"Staff","1200 (approx)"));
        overviewModelList.add(new OverviewModel(mContext.getResources().getDrawable(R.drawable.icon_criteria),"Acceptance Criteria","Profile Dependent"));
        overviewModelList.add(new OverviewModel(mContext.getResources().getDrawable(R.drawable.icon_accomdation),"Accommodation","Yes"));
        overviewModelList.add(new OverviewModel(mContext.getResources().getDrawable(R.drawable.icon_website),"Website","www.universitytoronto.com"));
        overviewModelList.add(new OverviewModel(mContext.getResources().getDrawable(R.drawable.icon_mobile),"Contact Number","+011 6745487564"));

        OverViewAdapter overViewAdapter=new OverViewAdapter(mContext,overviewModelList);
        LinearLayoutManager linearLayoutManager1=new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false);
        rv_overview.setLayoutManager(linearLayoutManager1);
        overViewAdapter.notifyDataSetChanged();
        rv_overview.setAdapter(overViewAdapter);
    }

    @Override
    protected void initData() {

    }
}
