package com.va.neoapp.presentation.homeservices.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.va.neoapp.R;
import com.va.neoapp.adapters.LatestNewsAdapter;
import com.va.neoapp.models.LatestNewModel;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.util.GlobalMethods;

import java.util.ArrayList;
import java.util.List;

public class LatestNewsActivity extends BaseActivity {

    Context mContext;
    AppCompatTextView heading_text;
    RecyclerView rv_latest_news;


    @Override
    protected int setLayoutResource() {
        return R.layout.activity_latest_news;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        mContext = this;
        heading_text = findViewById(R.id.heading_text);
        rv_latest_news = findViewById(R.id.rv_latest_news);

        heading_text.setText("Latest New and Updates");

        //set Data To AdAPTER
        setDataToAdapter();
    }

    @Override
    protected void initData() {
        findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMethods.callFinishForBackWordActivity(mContext, true);
            }
        });
    }

    private void setDataToAdapter() {

        List<LatestNewModel> latestNewModels = new ArrayList<>();
        latestNewModels.add(new LatestNewModel("Title", getResources().getString(R.string.dummy_text), ""));
        latestNewModels.add(new LatestNewModel("Title", getResources().getString(R.string.dummy_text), ""));
        latestNewModels.add(new LatestNewModel("Title", getResources().getString(R.string.dummy_text), ""));
        latestNewModels.add(new LatestNewModel("Title", getResources().getString(R.string.dummy_text), ""));
        latestNewModels.add(new LatestNewModel("Title", getResources().getString(R.string.dummy_text), ""));

        LatestNewsAdapter latestNewsAdapter = new LatestNewsAdapter(mContext, latestNewModels, new LatestNewsAdapter.onItemClickListener() {
            @Override
            public void itemSelected(LatestNewModel latestNewModel) {
                GlobalMethods.showDefaultFullScreenDialog(LatestNewsActivity.this,latestNewModel.getTitle(),latestNewModel.getImage_url(),latestNewModel.getDescription());
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        rv_latest_news.setLayoutManager(linearLayoutManager);
        rv_latest_news.setAdapter(latestNewsAdapter);
        //latestNewsAdapter.notifyDataSetChanged();

      //  GlobalMethods.showDefaultFullScreenDialog(LatestNewsActivity.this,"","","" );

    }

}
