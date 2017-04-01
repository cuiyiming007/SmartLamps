package com.glexer.smartlamp.volley;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.glexer.smartlamp.application.SmartLampApplication;
import com.glexer.smartlamp.data.SmartStoreSharedPreferences;
import com.glexer.smartlamp.data.model.MqttAddress;
import com.glexer.smartlamp.eventbus.HttpEvent;
import com.glexer.smartlamp.eventbus.HttpEventType;
import com.glexer.smartlamp.volley.requestmodel.JsonRequestWithHeader;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import de.greenrobot.event.EventBus;

/**
 * Created by Trice on 2016/1/19.
 */
public class ConnectToSeverAPI {

    private static ConnectToSeverAPI mInstance;

    public static synchronized ConnectToSeverAPI getInstance() {
        if (mInstance == null) {
            mInstance = new ConnectToSeverAPI();
        }
        return mInstance;
    }

    public void loginAccount(String username, String password) {

        String url = VolleyUtils.getHTTPHeadStr() + "user/login";
        JSONObject params = new JSONObject();
        try {
            params.put("loginName", username);
            params.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequestWithHeader request = new JsonRequestWithHeader(Method.POST, url, null, params,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String token = "";
                        try {
                            token = jsonObject.getString("token");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        VolleyUtils.setToken(token);
                        HttpEvent event = new HttpEvent(HttpEventType.LoginAccount, true, jsonObject);
                        EventBus.getDefault().post(event);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.LoginAccount, false);
                        if (volleyError instanceof TimeoutError) {
                            event.setData(0);
                        } else {
                            NetworkResponse networkResponse = volleyError.networkResponse;
                            if (networkResponse != null) {
                                event.setData(volleyError.networkResponse.statusCode);
                            }
                        }
                        EventBus.getDefault().post(event);
                    }
                });

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

    /***
     * user register
     *
     * @param username
     * @param email
     * @param phone
     * @param password
     */
    public void userRegisterAccount(String username, String email, String phone, String password) {

        String url = VolleyUtils.getHTTPHeadStr() + "user/register";
        JSONObject params = new JSONObject();
        try {
            params.put("login_name", username);
            params.put("email", email);
            params.put("mobile", phone);
            params.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequestWithHeader request = new JsonRequestWithHeader(Method.POST, url, null, params,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        HttpEvent event = new HttpEvent(HttpEventType.RegisterAccount, true, jsonObject);
                        EventBus.getDefault().post(event);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.RegisterAccount, false);
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null) {
                            event.setData(volleyError.networkResponse.statusCode);
                        }
                        EventBus.getDefault().post(event);
                    }
                });

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

    /***
     * user login
     *
     * @param username
     * @param password response
     *                 {
     *                 "result":"success",//登录结果
     *                 "id":"xxx",//用户id
     *                 "token":"xxxx",//api token
     *                 "yingshitoken":"xxxxx"//莹石云token
     *                 }
     */
    public void userLoginAccount(String username, String password) {

        String url = VolleyUtils.getHTTPHeadStr() + "user/login";
        JSONObject params = new JSONObject();
        try {
            params.put("login_name", username);
            params.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequestWithHeader request = new JsonRequestWithHeader(Method.POST, url, VolleyUtils.Header_Signature, params,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String token = "";
                        try {
                            token = jsonObject.getString("token");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        VolleyUtils.setToken(token);
                        HttpEvent event = new HttpEvent(HttpEventType.LoginAccount, true, jsonObject);
                        EventBus.getDefault().post(event);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.LoginAccount, false);
                        if (volleyError instanceof TimeoutError) {
                            event.setData(0);
                        } else {
                            NetworkResponse networkResponse = volleyError.networkResponse;
                            if (networkResponse != null) {
                                event.setData(volleyError.networkResponse.statusCode);
                            }
                        }
                        EventBus.getDefault().post(event);
                    }
                });

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

    /***
     * get mqtt address from http service
     */
    public void getMQTTServiceAddress() {
        String url = VolleyUtils.getHTTPHeadStr() + "metadata/getvalue/mqttserver";
        Log.i("getMQTTServiceAddress", url);
        final Gson gson = new Gson();
        StringRequest request = new StringRequest(url,
                new Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Log.i("getMQTTServiceAddress", s);
                        List<MqttAddress> mqttAdrressList = gson.fromJson(s,
                                new TypeToken<List<MqttAddress>>() {
                                }.getType());
                        HttpEvent event = new HttpEvent(HttpEventType.GetMqttAddress, true);
                        EventBus.getDefault().post(event);
                        SmartStoreSharedPreferences.getInstence().setMqttAddress(mqttAdrressList.get(0));
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.GetMqttAddress, false);
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null) {
                            event.setData(volleyError.networkResponse.statusCode);
                        }
                        EventBus.getDefault().post(event);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return VolleyUtils.Header_Token;
            }
        };
        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

    /***
     * bind internet device
     *
     * @param sn
     */
    public void bindInternetDevice(String sn) {

        String url = VolleyUtils.getHTTPHeadStr() + "device/bind";

        String usr_id = SmartStoreSharedPreferences.getInstence().getUserId();
        JSONObject params = new JSONObject();
        try {
            params.put("id", usr_id);
            params.put("sn", sn);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequestWithHeader request = new JsonRequestWithHeader(Method.POST, url, VolleyUtils.Header_Token, params,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        HttpEvent event = new HttpEvent(HttpEventType.BindInternetDevice, true, jsonObject);
                        EventBus.getDefault().post(event);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.BindInternetDevice, false);
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null) {
                            event.setData(volleyError.networkResponse.statusCode);
                        }
                        EventBus.getDefault().post(event);
                    }
                });

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

    /***
     * unbind Internet Device
     *
     * @param did
     */
    public void unbindInternetDevice(String did) {

        String url = VolleyUtils.getHTTPHeadStr() + "device/unbind";

        String usr_id = SmartStoreSharedPreferences.getInstence().getUserId();
        JSONObject params = new JSONObject();
        try {
            params.put("id", usr_id);
            params.put("did", did);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequestWithHeader request = new JsonRequestWithHeader(Method.PUT, url, VolleyUtils.Header_Token, params,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        HttpEvent event = new HttpEvent(HttpEventType.UnbindInternetDevice, true, jsonObject);
                        EventBus.getDefault().post(event);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.UnbindInternetDevice, false);
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null) {
                            event.setData(volleyError.networkResponse.statusCode);
                        }
                        EventBus.getDefault().post(event);
                    }
                });

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }



    /**
     * 用户修改密码
     *
     * @param password
     */
    public void changePassword(final String password) {
        String url = VolleyUtils.getHTTPHeadStr() + "user/updateuser/";
        String userId = SmartStoreSharedPreferences.getInstence().getUserId();
        url = url + userId;
        Log.i("输出log", "修改密码接口，URL：" + url);
        JSONObject params = new JSONObject();
        try {
            params.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequestWithHeader request = new JsonRequestWithHeader(Method.PUT, url, VolleyUtils.Header_Token, params,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        try {
                            String result = jsonObject.getString("result");
                            if (result.equals("success")) {
                            }
                            HttpEvent event = new HttpEvent(HttpEventType.ChangePassword, true, jsonObject);
                            EventBus.getDefault().post(event);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.ChangePassword, false);
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null) {
                            event.setData(volleyError.networkResponse.statusCode);
                        }
                        EventBus.getDefault().post(event);
                    }
                });

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

    /**
     * 修改网关名称
     *
     * @param name
     */
    public void changeGatewayName(final String name, String did) {
        String url = VolleyUtils.getHTTPHeadStr() + "device/update/";
        url = url + did;
        JSONObject params = new JSONObject();
        try {
            params.put("device_name", name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequestWithHeader request = new JsonRequestWithHeader(Method.PUT, url, VolleyUtils.Header_Token, params,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            String result = jsonObject.getString("result");
                            Bundle bundle = new Bundle();
                            bundle.putString("name", name);
                            bundle.putString("result", result);
                            HttpEvent event = new HttpEvent(HttpEventType.ChangeGatewayName, true);
                            event.setData(bundle);
                            EventBus.getDefault().post(event);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.ChangeGatewayName, false);
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null) {
                            event.setData(volleyError.networkResponse.statusCode);
                        }
                        EventBus.getDefault().post(event);
                    }
                });

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

    public void getZigbeeHistory(String did, String nwk_addr, String ep, String attributeId, int page, int num) {
        String url = VolleyUtils.getHTTPHeadStr() + "smhm.search/deviceHistState/";

        HashMap<String, String> paraMap = new HashMap<>();
        paraMap.put("oderField", "logTime");
        paraMap.put("Order", "DESC");
        paraMap.put("sensorId", nwk_addr);
        paraMap.put("sensorEp", ep);
        paraMap.put("attributeId", attributeId);
        paraMap.put("pageNum", page + "");
        paraMap.put("lineNum", num + "");
        String param = hashMap2ParamString(paraMap);
        url = url + did + "?" + param;
        Log.i("getZigbeeHistory", url);

        final Gson gson = new Gson();
        StringRequest request = new StringRequest(url,
                new Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        Gson gson = new Gson();
                        JsonParser parser = new JsonParser();
                        JsonObject jsonObject = parser.parse(s).getAsJsonObject();
                        String result = jsonObject.get("result").getAsString();
//                        if (result.equals("success")) {
//                            Log.i("getZigbeeHistory onResponse", "success");
//                            JsonElement jsonElement = jsonObject.get("data");
//                            List<HistoryPoint> historyPoints = gson.fromJson(jsonElement,
//                                    new TypeToken<List<HistoryPoint>>() {
//                                    }.getType());
//                            HttpEvent event = new HttpEvent(HttpEventType.GetZigbeeHistory, true);
//                            event.setData(historyPoints);
//                            EventBus.getDefault().post(event);
//                        } else {
//                            HttpEvent event = new HttpEvent(HttpEventType.GetZigbeeHistory, false);
//                            event.setData(0);
//                            EventBus.getDefault().post(event);
//                        }
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.GetZigbeeHistory, false);
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null) {
                            event.setData(volleyError.networkResponse.statusCode);
                        }
                        EventBus.getDefault().post(event);
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return VolleyUtils.Header_Token;
            }
        };

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

    public String hashMap2ParamString(HashMap<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuilder para = new StringBuilder();
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<java.lang.String, java.lang.String> entry = iterator
                    .next();
            para.append(entry.getKey());
            para.append("=");
            para.append(entry.getValue());
            para.append("&");
        }
        para.deleteCharAt(para.length() - 1);
        return para.toString();
    }

    /**
     *手机号码注册获取短信验证码
     * @param mobile
     */
    public void getSmsCode(String mobile) {

        String url = VolleyUtils.getHTTPHeadStr() + "validate/mobile/sendcode";
        JSONObject params = new JSONObject();
        try {
            params.put("mobile", mobile);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequestWithHeader request = new JsonRequestWithHeader(Method.POST, url, VolleyUtils.Header_Token, params,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        HttpEvent event = new HttpEvent(HttpEventType.GetSmsCode, true, jsonObject);
                        EventBus.getDefault().post(event);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.GetSmsCode, false);
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null) {
                            event.setData(volleyError.networkResponse.statusCode);
                        }
                        EventBus.getDefault().post(event);
                    }
                });

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

    /**
     * 验证手机短信验证码
     * @param mobile
     * @param code
     */
    public void checkSmsCode(String mobile, String code) {

        String url = VolleyUtils.getHTTPHeadStr() + "validate/mobile/checkcode";
        JSONObject params = new JSONObject();
        try {
            params.put("mobile", mobile);
            params.put("code", code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequestWithHeader request = new JsonRequestWithHeader(Method.PUT, url, VolleyUtils.Header_Token, params,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        HttpEvent event = new HttpEvent(HttpEventType.CheckSmsCode, true, jsonObject);
                        EventBus.getDefault().post(event);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.CheckSmsCode, false);
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null) {
                            event.setData(volleyError.networkResponse.statusCode);
                        }
                        EventBus.getDefault().post(event);
                    }
                });

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

    /**
     *找回密码--获取短信验证码/获取邮件
     * @param phone
     */
    public void resetGetSmsCode(String phone) {

        String url = VolleyUtils.getHTTPHeadStr() + "validate/reset/sendcode";
        JSONObject params = new JSONObject();
        try {
            params.put("login_name", phone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequestWithHeader request = new JsonRequestWithHeader(Method.POST, url, VolleyUtils.Header_Token, params,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        HttpEvent event = new HttpEvent(HttpEventType.ResetGetSmsCode, true, jsonObject);
                        EventBus.getDefault().post(event);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.ResetGetSmsCode, false);
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null) {
                            event.setData(volleyError.networkResponse.statusCode);
                        }
                        EventBus.getDefault().post(event);
                    }
                });

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

    /**
     * 忘记密码--验证手机短信验证码
     * @param mobile
     * @param code
     */
    public void resetCheckSmsCode(String mobile, String code) {

        String url = VolleyUtils.getHTTPHeadStr() + "validate/reset/checkcode";
        JSONObject params = new JSONObject();
        try {
            params.put("login_name", mobile);
            params.put("code", code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequestWithHeader request = new JsonRequestWithHeader(Method.PUT, url, VolleyUtils.Header_Token, params,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        HttpEvent event = new HttpEvent(HttpEventType.ResetCheckSmsCode, true, jsonObject);
                        EventBus.getDefault().post(event);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.ResetCheckSmsCode, false);
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null) {
                            event.setData(volleyError.networkResponse.statusCode);
                        }
                        EventBus.getDefault().post(event);
                    }
                });

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

    /**
     * 重置密码
     * @param mobile
     * @param code
     */
    public void resetPassword(String mobile, String code) {

        String url = VolleyUtils.getHTTPHeadStr() + "validate/reset/newpassword";
        JSONObject params = new JSONObject();
        try {
            params.put("login_name", mobile);
            params.put("password", code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonRequestWithHeader request = new JsonRequestWithHeader(Method.PUT, url, VolleyUtils.Header_Token, params,
                new Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        HttpEvent event = new HttpEvent(HttpEventType.ResetPassword, true, jsonObject);
                        EventBus.getDefault().post(event);
                    }
                },
                new ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        HttpEvent event = new HttpEvent(HttpEventType.ResetPassword, false);
                        NetworkResponse networkResponse = volleyError.networkResponse;
                        if (networkResponse != null) {
                            event.setData(volleyError.networkResponse.statusCode);
                        }
                        EventBus.getDefault().post(event);
                    }
                });

        SmartLampApplication.getInstance().addToRequestQueue(request);
    }

}
