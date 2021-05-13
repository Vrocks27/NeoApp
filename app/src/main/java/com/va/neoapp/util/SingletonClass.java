package com.va.neoapp.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;


public class SingletonClass {
    private static SingletonClass INSTANCE = null;

    private SingletonClass() {
    }

    public static SingletonClass getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingletonClass();
        }
        return INSTANCE;
    }


    public  boolean isConnectedToInternet(Context mContext) {
        if (mContext == null)
            return false;

        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                final Network network = connectivityManager.getActiveNetwork();
                if (network != null) {
                    final NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(network);
                    return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)||
                            nc.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
                }
            } else {
                final NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                //activeNetwork.isConnectedOrConnecting();
                //conMgr.isActiveNetworkMetered();
                return activeNetwork != null && activeNetwork.isConnected();
//                NetworkInfo[] networkInfos = connectivityManager.getAllNetworkInfo();
//                for (NetworkInfo tempNetworkInfo : networkInfos) {
//                    if (tempNetworkInfo.isConnected()) {
//                        return true;
//                    }
//                }
            }
        }
        return false;
    }

    public static AlertDialog.Builder getAlertDialog(Context context, String message, String positiveButtonMessage, DialogInterface.OnClickListener positiveButtonOnClickListener,
                                                     String negativeButtonMessage, DialogInterface.OnClickListener negativeButtonOnClickListener, boolean isCancelable) {
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder(context);
        dlgAlert.setMessage(message);
        if (positiveButtonMessage != null && !positiveButtonMessage.isEmpty()) {
            dlgAlert.setPositiveButton(positiveButtonMessage, positiveButtonOnClickListener);
        }
        if (negativeButtonMessage != null && !negativeButtonMessage.isEmpty()) {
            dlgAlert.setNegativeButton(negativeButtonMessage, negativeButtonOnClickListener);
        }
        dlgAlert.setCancelable(isCancelable);
        return dlgAlert;
    }
   /* public boolean isDeviceOnline(Context context) throws Exception {
        try {

            final ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
            // notify user you are online
            // notify user you are not online
            //activeNetwork.isConnectedOrConnecting();
            //conMgr.isActiveNetworkMetered();
            return activeNetwork != null && activeNetwork.isConnected();
        } catch (Exception e) {
            Log.e("connection_error", e.getMessage());
            return false;
        }
    }*/

    /*public boolean isDeviceOnline1(Context context) throws Exception {
        try {
            final ConnectivityManager conMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
             final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnected()) {
                // notify user you are online
                return true;
            } else {
                // notify user you are not online
                return false;
            }
        } catch (Exception e) {
            throw e;
        }

    }*/
}
