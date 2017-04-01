package com.glexer.smartlamp.volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.glexer.smartlamp.application.SmartLampApplication;
import com.glexer.smartlamp.eventbus.HttpEvent;
import com.glexer.smartlamp.eventbus.HttpEventType;
import com.glexer.smartlamp.utils.UrlUtils;

import java.util.HashMap;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 *
 * Created by Trice on 2016/10/21.
 */
public class VolleyRequest {

    public static void addDevice(String sn) {
        String url = UrlUtils.getHTTPHeadStr() +"device/bindWithCurUser";
        final String body = "sn="+sn;
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.i("addDevice onResponse", s);

                        HttpEvent event = new HttpEvent(HttpEventType.BindInternetDevice, true, s);
                        EventBus.getDefault().post(event);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                HttpEvent event = new HttpEvent(HttpEventType.BindInternetDevice, false);
                NetworkResponse networkResponse = volleyError.networkResponse;
                if (networkResponse != null) {
                    event.setData(volleyError.networkResponse.statusCode);
                }
                EventBus.getDefault().post(event);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> sessionHeader = new HashMap<>();
//                sessionHeader.put("Cookie", SharedPreferencesUtils.getInstance().getSessionId());
                return sessionHeader;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                return body.getBytes();
            }
        };

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }


}
