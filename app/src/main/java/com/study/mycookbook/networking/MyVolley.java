package com.study.mycookbook.networking;

import android.content.Context;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;

import java.util.Map;

/**
 * Created by
 */
public class MyVolley {

    private  static MyVolley myVolley;

    public static MyVolley newMyVolley() {
        if (myVolley == null) {
            myVolley = new MyVolley();
        }
        return myVolley;
    }

    private CallBack callBack;
    private StringRequest stringRequest;
    private Context context;

    // string get
    public void stringRequestGet(Context context,String url,CallBack callBack){
        RequestQueue queue = Volley.newRequestQueue(context);
        this.callBack = callBack;
        stringRequest = new StringRequest(Request.Method.GET, url, stringListener, errorListener);
        queue.add(stringRequest);
    }

    // string post
    public void stringRequestPost(Context context, String url, final  Map map, CallBack callBack) {
        RequestQueue queue = Volley.newRequestQueue(context);
        this.callBack = callBack;
        stringRequest = new StringRequest(Request.Method.POST, url, stringListener, errorListener){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return  map;
            }
        };
    }

    // string listener
    private Response.Listener<String> stringListener = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                callBack.onStringSuccess(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };


    // error listener
    private Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            callBack.onFailure(error);
        }
    };

    // call back
    public interface CallBack{
        public void onStringSuccess(String response) throws JSONException;
        public void onFailure(VolleyError error);
    }

}
