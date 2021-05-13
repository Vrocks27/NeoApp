package com.va.neoapp.webaccess;

import android.content.Context;

import com.va.neoapp.util.Constants;
import com.va.neoapp.util.GlobalMethods;

public class VersionControls {

    private static VersionControls instance = null;

    private String Url = URLS.Development.getBaseURL();

    public VersionControls(Context context) {

    }

    public static VersionControls getVersionControls(Context context) {
        if (instance == null)
            instance = new VersionControls(context);
        setUrls(context);
        return instance;
    }

    private static void setUrls(Context context) {
        if (GlobalMethods.isNull(GlobalMethods.getServiceType(context))) {
            if (instance != null) {
                switch (GlobalMethods.getServiceType(context)) {
                    case Constants.POINT_PRODUCTION:
                        instance.setBaseUrl(URLS.Production.getBaseURL());
                        break;
                    default:
                    case Constants.POINT_DEV:
                        instance.setBaseUrl(URLS.Development.getBaseURL());
                        break;

                }
            }
        }
    }

    enum URLS {
        Development("https://www.varundev.com/"),
        Production("");
        String URL;
        URLS(String URL) {
            this.URL = URL;
        }
        String getBaseURL() {
            return this.URL;
        }
    }

    public String getBaseURL() {
        return Url;
    }

    private void setBaseUrl(String url) {
        Url = url;
    }
}