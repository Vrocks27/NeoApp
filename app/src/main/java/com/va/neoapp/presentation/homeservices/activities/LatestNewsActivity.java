package com.va.neoapp.presentation.homeservices.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.va.neoapp.R;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.util.GlobalMethods;

public class LatestNewsActivity extends BaseActivity {

    Context mContext;
    AppCompatTextView heading_text;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_latest_news;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        mContext=this;
        heading_text=findViewById(R.id.heading_text);

        heading_text.setText("Latest New and Updates");
    }

    @Override
    protected void initData() {
        findViewById(R.id.toolbar_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalMethods.callFinishForBackWordActivity(mContext,true);
            }
        });
    }
}
