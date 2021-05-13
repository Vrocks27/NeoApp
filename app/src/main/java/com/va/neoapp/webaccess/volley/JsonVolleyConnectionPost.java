package com.va.neoapp.webaccess.volley;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.va.neoapp.R;
import com.va.neoapp.util.SingletonClass;
import com.va.neoapp.webaccess.VersionControls;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonVolleyConnectionPost {

    private final Context mContext;
    private OnVolleyResponseListenerForJson mOnVolleyResponseListener;
    private String mMethodName;

    public JsonVolleyConnectionPost(Context context, OnVolleyResponseListenerForJson onVolleyResponseListener) {
        mContext = context;
        mOnVolleyResponseListener = onVolleyResponseListener;
    }

    public JsonVolleyConnectionPost(Context context) {
        mContext = context;
        if (context instanceof OnVolleyResponseListenerForJson) {
            Log.d("json_post_err:", "VolleyConnection VolleyConnection: context instanceof OnVolleyResponseListener");
            mOnVolleyResponseListener = (OnVolleyResponseListenerForJson) context;
        } else {
            Log.d("json_post_err:", "VolleyConnection VolleyConnection: context NOT instanceof OnVolleyResponseListener ': ");
        }
    }

    public final void executeJson(final String mMethodName, JSONObject mJsonObjectParams, final String isFrom) {

        this.mMethodName = mMethodName;

        Log.d("json_req_obj: ", mJsonObjectParams.toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(getRequestMethod(), getRequestUrl(), mJsonObjectParams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Log.d("volley_res: ", "execute onResponse: " + jsonObject.toString());

                        try {
                            if (mOnVolleyResponseListener != null) {
                                mOnVolleyResponseListener.onResponse(jsonObject.toString().trim(), mMethodName, isFrom);
                            }
                        } catch (Exception e) {
                            Log.e("", e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                Log.d("volley_err_res: ", "onErrorResponse:  in execute " + error.toString());
                // GlobalMethods.hideProgress(progressDialog);
                try {
                    if (error instanceof NoConnectionError) {
                        SingletonClass.getAlertDialog(mContext, mContext.getResources().getString(R.string.error_internet_unavailable), mContext.getResources().getString(R.string.retry), new
                                DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        //  new VolleyConnection(mContext, mOnVolleyResponseListener).execute(params);
                                    }
                                }, mContext.getResources().getString(R.string.dismiss), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if (mOnVolleyResponseListener != null) {
                                    mOnVolleyResponseListener.onResponse(error.toString().trim(), mMethodName, isFrom);
                                }

                            }
                        }, false).create().show();

                    } else if (error instanceof TimeoutError) {
                        // GlobalMethods.hideProgress(progressDialog);

                        Log.d("volley_time_out_err: ", "onErrorResponse: params[0]" + mMethodName);

                        if (mMethodName.equals("mobikulmpMarketplaceContactSeller") || mMethodName.equals("mobikulmpMarketplaceAskQuestion") ||
                                mMethodName.equals("mobikulmpMarketplaceInvoice") || mMethodName.equals("mobikulmpMarketplaceSendinvoiceMail")) {
                            // Toast.makeText(mContext, mContext.getString(R.string.request_send), Toast.LENGTH_SHORT).show();
                            if (mOnVolleyResponseListener != null) {
                                mOnVolleyResponseListener.onResponse(error.toString().trim(), mMethodName, isFrom);
                            }
                            return;
                        }

                        SingletonClass.getAlertDialog(mContext, mContext.getString(R.string.request_failed), mContext.getResources().getString(R.string.retry), new DialogInterface
                                .OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // new VolleyConnection(mContext, mOnVolleyResponseListener).execute(params);
                            }
                        }, mContext.getResources().getString(R.string.dismiss), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (mOnVolleyResponseListener != null) {
                                    mOnVolleyResponseListener.onResponse(error.toString().trim(), mMethodName, isFrom);
                                }
                            }
                        }, true).create().show();

                    } else if (error instanceof AuthFailureError || error instanceof ServerError || error instanceof NetworkError || error instanceof ParseError) {
                        if (mOnVolleyResponseListener != null) {
                            mOnVolleyResponseListener.onResponse(error.toString().trim(), mMethodName, isFrom);
                        }
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                    Log.d("volley_error_exp: ", "" + e.toString());
                    // GlobalMethods.hideProgress(progressDialog);
                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //  VolleySingleton.getInstance(mContext).addToRequestQueue(jsonObjectRequest);
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(jsonObjectRequest);
        // RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        ///requestQueue.add(jsonObjectRequest);
        //stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 30, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

    }

    private String getRequestUrl() {
        return VersionControls.getVersionControls(mContext).getBaseURL() + mMethodName;
    }

    private int getRequestMethod() {
        return Request.Method.POST;
    }
}
