package com.va.neoapp.util;

import android.Manifest;

public interface Constants {
    // all the other constants.

    String[] PERMISSIONS = {
            Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE};
    //Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,
    // Manifest.permission.MODIFY_AUDIO_SETTINGS, Manifest.permission.RECORD_AUDIO,


    String POINT_DEV = "Dev";
    String POINT_PRODUCTION = "Production";

    String APP_PREFERENCE = "AppPrefer";

    String LOGIN = "login";

    // messaging
    int MSG_TYPE_LEFT = 0;
    int MSG_TYPE_RIGHT = 1;


}