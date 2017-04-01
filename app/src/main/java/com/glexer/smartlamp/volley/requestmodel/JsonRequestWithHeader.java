package com.glexer.smartlamp.volley.requestmodel;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Trice on 2016/1/19.
 */
public class JsonRequestWithHeader extends JsonObjectRequest {


    private final Map<String, String> mHeaders;

    public JsonRequestWithHeader(int method, String url, Map<String, String> headers, JSONObject jsonRequest, Listener<JSONObject> listener,
                                 ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        mHeaders = headers;
    }

    public JsonRequestWithHeader(String url, Map<String, String> headers, Listener<JSONObject> listener,
                                 ErrorListener errorListener) {
        this(Method.GET, url, headers, null, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return mHeaders != null ? mHeaders : super.getHeaders();
    }

}