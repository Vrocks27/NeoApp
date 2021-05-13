package com.va.neoapp.webaccess.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.va.neoapp.webaccess.VersionControls;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonVolleyConnectionGet {


    private final Context mContext;
    private OnVolleyResponseListenerForJson mOnVolleyResponseListener;
    private String mMethodName;

    public JsonVolleyConnectionGet(Context context, OnVolleyResponseListenerForJson onVolleyResponseListener) {
        mContext = context;
        mOnVolleyResponseListener = onVolleyResponseListener;
    }

    public JsonVolleyConnectionGet(Context context) {
        mContext = context;
        if (context instanceof OnVolleyResponseListenerForJson) {
            Log.d("json_get_err:", "VolleyConnection VolleyConnection: context instanceof OnVolleyResponseListener");
            mOnVolleyResponseListener = (OnVolleyResponseListenerForJson) context;
        } else {
            Log.d("json_get_err:", "VolleyConnection VolleyConnection: context NOT instanceof OnVolleyResponseListener ': ");
        }
    }

    public final void executeJson(final String mMethodName, final String isFrom) {
        this.mMethodName = mMethodName;
        //HttpsTrustManager.allowAllSSL();
        Log.d("json_get_req_obj", "execute: method url rest: " + getRequestUrl());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(getRequestMethod(), getRequestUrl(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Log.d("volley_get_res:", "execute onResponse: " + jsonObject.toString());
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
                Log.d("volley_get_err: ", "onErrorResponse:  in execute " + error.toString());

                try {
                    if (error instanceof AuthFailureError || error instanceof ServerError || error instanceof NetworkError || error instanceof ParseError) {
                        if (mOnVolleyResponseListener != null) {
                            mOnVolleyResponseListener.onResponse(error.toString().trim(), mMethodName, isFrom);
                        }
                    }
                } catch (Exception e) {

                }
            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                //headers.put("Content-Type", "application/json; charset=utf-8");
//                if (GlobalMethods.isNull(GlobalMethods.getAccessToken(mContext))) {
//                    headers.put("ACCESS-TOKEN", GlobalMethods.getAccessToken(mContext));
//                }
                return headers;
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        requestQueue.add(jsonObjectRequest);
    }

    private String getRequestUrl() {
        return VersionControls.getVersionControls(mContext).getBaseURL() + mMethodName;
    }

    private int getRequestMethod() {
        return Request.Method.GET;
    }
}
