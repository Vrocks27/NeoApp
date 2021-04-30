package com.va.neoapp.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
//import com.va.neoapp.BuildConfig;
import com.va.neoapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class GlobalMethods {

    /*public static String getBuildVersionName() {
        return BuildConfig.VERSION_NAME;
    }

    public static int getBuildVersionCode() {
        return BuildConfig.VERSION_CODE;
    }*/

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
    public static int dpToPx(float dp, Resources resources) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
        return (int) px;
    }

    public static boolean isOnline(Context context) throws Exception {
//        try {
//            return SingletonClass.getInstance().isDeviceOnline(context);
//        } catch (Exception e) {
//            throw e;
//        }

        return false;
    }

    public static void showNormalToast(Activity activity, String message, int lengthLong) {
        // if 1 = long, 0 = short
        int length =Toast.LENGTH_SHORT; // default short
        if (lengthLong == 1) {
            length = Toast.LENGTH_LONG;
        }
        Toast.makeText(activity, message, length).show();
    }

    public static void showMessage(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        view = snackbar.getView();
        AppCompatTextView tv = view.findViewById(com.google.android.material.R.id.snackbar_text);
        tv.setMaxLines(3);
        snackbar.show();
    }

    public static boolean isGPSEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        assert locationManager != null;
        if (locationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER)) {
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } else {
            return false;
        }
    }
    public static boolean isGPSServiceOn(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }


    public static boolean isNull(String data) {
        boolean isnull = false;
        if (data != null) {
            if (!data.equals("") && !data.equals("null") && !data.equals("-1")) {
                isnull = true;
            }
        }
        return isnull;
    }

    public static boolean isValidEmailAddress(String email) {
        Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        return emailPattern.matcher(email).matches();
    }

    public static boolean isValidMobileNumber(String mobileNumber) {
        Pattern mobilePattern = Pattern.compile("^[0-9]{10}$");
        return mobilePattern.matcher(mobileNumber).matches();
    }

    public static boolean isNumber(String number) {
        Pattern mobilePattern = Pattern.compile("^[0-9]");
        return mobilePattern.matcher(number).matches();
    }

    public static boolean isValidInternationalMobileNumber(String mobileNumber) {
       // send along with the +country code, if using the below pattern
//        Pattern mobilePattern = Pattern.compile("^\\+[0-9]{1,3}\\.[0-9]{4,14}(?:x.+)?$");
//        return !(mobileNumber.length() < 6 || mobileNumber.length() > 13)
//                && mobilePattern.matcher(mobileNumber).matches();
        return !(mobileNumber.length() < 4 || mobileNumber.length() > 13)
                && android.util.Patterns.PHONE.matcher(mobileNumber).matches();
    }

    public static boolean isValidJson(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }

    public static boolean isValidJsonArray(String test) {
        try {
            new JSONArray(test);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }

    public static void loadImageWithDefault(final Context context, final String url, final AppCompatImageView viewById) {
        //Picasso.with(context).load(url).into(viewById);
      /*  Picasso.get()
                .load(url)
                .into(viewById);*/
//        String tempUrl = url.replace("https:", "http:");
//        Log.e("tempUrl: -", tempUrl);

        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
               // .placeholder(R.drawable.ic_image_coming_soon)
                //.crossFade()
                .into(viewById);

//                .listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        Glide.with(context).load(url).centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(viewById);
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        if (progressBar != null)
//                            progressBar.setVisibility(View.GONE);
//                        return false;
//                    }
//                })

    }


    public static ProgressDialog showProgress(Context context) {
        ProgressDialog progress = ProgressDialog.show(context, null, null);
//        progress.setMessage(message);
        progress.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        progress.setContentView(R.layout.progress_layout);
        progress.setCancelable(false);
        progress.show();
        return progress;
    }

    public static void hideProgress(ProgressDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static void setServiceType(Context context, String values) {
        SharedPreferences.Editor editor = context.getSharedPreferences(Constants.APP_PREFERENCE, Context.MODE_PRIVATE).edit();
        editor.putString("serviceType", values);
        editor.apply();
    }

    public static String getServiceType(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(Constants.APP_PREFERENCE, Context.MODE_PRIVATE);
        return prefs.getString("serviceType", null);
    }

    public static String convertDatToTime(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateconverted = null;
        try {
            Date d = dateFormat.parse(dateString);

            // SimpleDateFormat dateFormat1 = new SimpleDateFormat("hh:mm:ss");
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("hh:mm aa");
            assert d != null;
            dateconverted = dateFormat1.format(d);
        } catch (ParseException e) {
            //e.printStackTrace();
            Log.e("date_convert_err:", e.getMessage());
        }
        return dateconverted;
    }

    public static void callForWordActivity(Context context, Class forwardClass, Bundle bundle , boolean isActivityFinish, boolean isForwardAnimation) {
        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        context.startActivity(myIntent);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in, R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right, R.anim.slide_right_in);
        }
        if (isActivityFinish)
            ((Activity) context).finish();
    }
    public static void callFinishForBackWordActivity(Context context, boolean isForwardAnimation) {
        try {

            ((Activity) context).finish();
            if (isForwardAnimation) {
                ((Activity) context).overridePendingTransition(R.anim.slide_left_in, R.anim.slide_out_left);
            } else {
                ((Activity) context).overridePendingTransition(R.anim.slide_out_right, R.anim.slide_right_in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void enableBottomNext(Context context, FloatingActionButton bottom_fab_button, AppCompatTextView text_next) {
        bottom_fab_button.setEnabled(true);
        bottom_fab_button.setClickable(true);
        bottom_fab_button.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.white)));
        bottom_fab_button.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.default_main_color)));
        text_next.setTextColor(context.getResources().getColor(R.color.default_text_color));
    }

    public static void disableBottomNext(Context context, FloatingActionButton bottom_fab_button, AppCompatTextView text_next) {
        bottom_fab_button.setEnabled(false);
        bottom_fab_button.setClickable(false);
        bottom_fab_button.setImageTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.disabled_color)));
        bottom_fab_button.setBackgroundTintList(ColorStateList.valueOf(context.getResources().getColor(R.color.default_disabled)));
        text_next.setTextColor(context.getResources().getColor(R.color.disabled_color));
    }
  /*

    public static void callBackWordActivity(Context context, Class forwardClass, Bundle bundle, boolean isActivityFinish, boolean isForwardAnimation) {
        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in, R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right, R.anim.slide_right_in);
        }
        if (isActivityFinish)
            ((Activity) context).finish();
    }



    public static void callForWordActivityForResult(Context context, Class forwardClass, Bundle bundle, int ResultCode, boolean isForwardAnimation) {

        Intent myIntent = new Intent(context, forwardClass);
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        ((Activity) context).startActivityForResult(myIntent, ResultCode);
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in, R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right, R.anim.slide_right_in);
        }

    }

    public static void callBackWordActivityForResult(Context context, Bundle bundle, int ResultCode, boolean isForwardAnimation) {
        Intent myIntent = new Intent();
        if (bundle != null) {
            myIntent.putExtras(bundle);
        }
        ((Activity) context).setResult(Activity.RESULT_OK, myIntent);
        ((Activity) context).finish();
        if (isForwardAnimation) {
            ((Activity) context).overridePendingTransition(R.anim.slide_left_in, R.anim.slide_out_left);
        } else {
            ((Activity) context).overridePendingTransition(R.anim.slide_out_right, R.anim.slide_right_in);
        }
    }

    public static CharSequence setEditextError(Context context, String str, EditText editext) {

        editext.requestFocus();
        editext.setError(str);

        return "";
    }
*/

/*
    public static void showLoginBottomSheetDialog(Activity activity, boolean showSignUp) {
        BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(activity);
        View bottomSheetLayout = LayoutInflater.from(activity).inflate(R.layout.bottom_sheet_login, null);
        ((LinearLayout) bottomSheetLayout.findViewById(R.id.layout_sign_up)).setVisibility(showSignUp ? View.VISIBLE : View.GONE);

        // LinearLayout layout_login = (LinearLayout) bottomSheetLayout.findViewById(R.id.layout_login);
        PrefixEditText mobile_number = (PrefixEditText) bottomSheetLayout.findViewById(R.id.input_mobile);
        ((AppCompatButton) bottomSheetLayout.findViewById(R.id.login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(SplashScreenActivity.this, "login", Toast.LENGTH_SHORT).show();
                String mobile = mobile_number.getText().toString().trim();
                if (!TextUtils.isEmpty(mobile)) {
                    callVerifyAndLogin(mobile);
                } else {
                    Toast.makeText(activity, activity.getString(R.string.enter_mobile), Toast.LENGTH_SHORT).show();
                }
            }
        });
        ((AppCompatTextView) bottomSheetLayout.findViewById(R.id.sign_up)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
                showSignUp(activity, mobile_number.getText().toString().trim());
            }
        });

        ((AppCompatImageButton) bottomSheetLayout.findViewById(R.id.button_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });


        mBottomSheetDialog.setContentView(bottomSheetLayout);
        mBottomSheetDialog.setCancelable(false);
        mBottomSheetDialog.show();
    }

    private static void showSignUp(Activity activity, String mobile) {
        SignUpDialogFragment signUpDialogFragment = new SignUpDialogFragment(activity, mobile);
        AndroidUtils.dialogFragmentShowRight(activity, signUpDialogFragment);
    }

    private static void callVerifyAndLogin(String mobile) {
        Call<DefaultResponse> defaultResponseCall = CustomerApplication.getApplicationInstance().getAppWebService().doVerifyMobileNumber(mobile);
        defaultResponseCall.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(@NonNull Call<DefaultResponse> call, @NonNull Response<DefaultResponse> response) {
                Log.e("response", "" + response.toString());

                Log.e("response", "" + response.body());

                Log.e("response", "" + response.body().getMsg());


                if (response.body().getStatus().equalsIgnoreCase("0")) {
                    Log.e("response", "" + response.body().getStatus());
                } else if (response.body().getStatus().equalsIgnoreCase("1")) {


                }
            }

            @Override
            public void onFailure(@NonNull Call<DefaultResponse> call, @NonNull Throwable t) {
                call.cancel();
            }
        });
    }

    public static void enableGpsLocation(Activity activity, int REQUEST_LOCATION) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(activity)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(Bundle bundle) {

                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        // googleApiClient.connect();
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult connectionResult) {

                        Log.d("Location error", "Location error " + connectionResult.getErrorCode());
                    }
                }).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(3 * 1000);
        locationRequest.setFastestInterval(1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try {
                            status.startResolutionForResult(activity, REQUEST_LOCATION);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                }
            }
        });
    }

    */



}
