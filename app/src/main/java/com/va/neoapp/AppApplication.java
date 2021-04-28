package com.va.neoapp;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import androidx.multidex.MultiDex;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // MultiDex.install(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public AssetManager getAssets() {
        return getResources().getAssets();
    }
}
