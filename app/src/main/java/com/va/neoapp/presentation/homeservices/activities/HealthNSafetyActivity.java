package com.va.neoapp.presentation.homeservices.activities;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.va.neoapp.R;
import com.va.neoapp.adapters.HealthSafetyAdapter;
import com.va.neoapp.models.HealthSafetyModel;
import com.va.neoapp.presentation.BaseActivity;
import com.va.neoapp.util.GlobalMethods;

import java.util.ArrayList;
import java.util.List;

public class HealthNSafetyActivity extends BaseActivity {

    private List<HealthSafetyModel> healthSafetyList;

    @Override
    protected int setLayoutResource() {
        return R.layout.activity_health_safety;
    }

    @Override
    protected void initGUI(Bundle savedInstanceState) {
        RecyclerView rv_health_safety = findViewById(R.id.rv_health_safety);
        rv_health_safety.setLayoutManager(new LinearLayoutManager(this));

        healthSafetyList = new ArrayList<>();
        healthSafetyList.add(new HealthSafetyModel("10 Tips for Staying Safe in the Era of COVID-19",
                "hsidhfoihsofdhouhsohfousirhfouhfiuhisufhihsifuhishhofuhoihdoighdhoihohoihooiuhihosuhfou",
                "xVu_I6WCsto", "https://www.youtube.com/watch?v=xVu_I6WCsto",
                "https://i.ytimg.com/an_webp/xVu_I6WCsto/mqdefault_6s.webp?du=3000&sqp=CL6C_4QG&rs=AOn4CLAayUC1_7HIrSrnMqJN3UT-K5NTsg"));

        healthSafetyList.add(new HealthSafetyModel("10 Tips for Staying Safe in the Era of COVID-19",
                "hsidhfoihsofdhouhsohfousirhfouhfiuhisufhihsifuhishhofuhoihdoighdhoihohoihooiuhihosuhfou",
                "xVu_I6WCsto", "https://www.youtube.com/watch?v=xVu_I6WCsto",
                "https://i.ytimg.com/vi/xVu_I6WCsto/hq720.jpg"));


        healthSafetyList.add(new HealthSafetyModel("COVID-19 Health and Safety measures on campus",
                "hsidhfoihsofdhouhsohfousirhfouhfiuhisufhihsifuhishhofuhoihdoighdhoihohoihooiuhihosuhfou",
                "obJPsCeY4E8", "https://www.youtube.com/watch?v=obJPsCeY4E8",
                "https://i.ytimg.com/vi/obJPsCeY4E8/maxresdefault.jpg"));

        healthSafetyList.add(new HealthSafetyModel("Covid-19 Health & Safety Protocol",
                "hsidhfoihsofdhouhsohfousirhfouhfiuhisufhihsifuhishhofuhoihdoighdhoihohoihooiuhihosuhfou",
                "Dpe8aoMtfXU", "https://www.youtube.com/watch?v=Dpe8aoMtfXU", "https://i.ytimg.com/vi/Dpe8aoMtfXU/hqdefault.jpg"));


        HealthSafetyAdapter healthSafetyAdapter = new HealthSafetyAdapter(this, healthSafetyList, new HealthSafetyAdapter.onItemClickListener() {
            @Override
            public void itemSelected(HealthSafetyModel healthSafetyModel) {

                Bundle b = new Bundle();
                b.putString("youtube_id", healthSafetyModel.getYoutube_id());
                GlobalMethods.callForWordActivity(HealthNSafetyActivity.this, VideosPlayActivity.class, b, false, true);
            }
        });
        rv_health_safety.setAdapter(healthSafetyAdapter);
    }

    @Override
    protected void initData() {

    }
}
