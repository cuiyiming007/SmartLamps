package com.glexer.smartlamp.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.glexer.smartlamp.R;
import com.glexer.smartlamp.application.SmartLampApplication;
import com.glexer.smartlamp.data.model.MqttAddress;

import java.util.UUID;

/**
 * Created by Trice on 2016/1/24.
 */
public class SmartStoreSharedPreferences {
    private static SmartStoreSharedPreferences mInstance;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    Context mContext;

    public SmartStoreSharedPreferences() {
        mContext = SmartLampApplication.getInstance();
        mSharedPreferences = mContext.getSharedPreferences(mContext.getString(R.string.app_name), Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }

    public static synchronized SmartStoreSharedPreferences getInstence() {
        if (mInstance == null) {
            mInstance = new SmartStoreSharedPreferences();
        }
        return mInstance;
    }

    // mqtt address
    public MqttAddress getMqttAddress() {
        MqttAddress address = new MqttAddress();
        address.setUser(mSharedPreferences.getString(mContext.getString(R.string.mqtt_user_shared_preference), mContext.getString(R.string.empty_shared_preference)));
        address.setPassword(mSharedPreferences.getString(mContext.getString(R.string.mqtt_password_shared_preference), mContext.getString(R.string.empty_shared_preference)));
        address.setUrl(mSharedPreferences.getString(mContext.getString(R.string.mqtt_url_shared_preference), mContext.getString(R.string.empty_shared_preference)));
        address.setPort(mSharedPreferences.getString(mContext.getString(R.string.mqtt_port_shared_preference), mContext.getString(R.string.empty_shared_preference)));
        return address;
    }

    public boolean setMqttAddress(MqttAddress address) {
        mEditor.putString(mContext.getString(R.string.mqtt_user_shared_preference), address.getUser());
        mEditor.putString(mContext.getString(R.string.mqtt_password_shared_preference), address.getPassword());
        mEditor.putString(mContext.getString(R.string.mqtt_url_shared_preference), address.getUrl());
        mEditor.putString(mContext.getString(R.string.mqtt_port_shared_preference), address.getPort());
        return mEditor.commit();
    }

    // token
    public String getSecretKey() {
        return mSharedPreferences.getString(mContext.getString(R.string.secretkey_shared_preference), mContext.getString(R.string.empty_shared_preference));
    }

    public boolean setSecretKey(String secretKey) {
        mEditor.putString(mContext.getString(R.string.secretkey_shared_preference), secretKey);
        return mEditor.commit();
    }

    // login name
    public String getLoginName() {
        return mSharedPreferences.getString(mContext.getString(R.string.login_name_shared_preference), mContext.getString(R.string.empty_shared_preference));
    }

    public boolean setLoginName(String loginUser) {
        mEditor.putString(mContext.getString(R.string.login_name_shared_preference), loginUser);
        return mEditor.commit();
    }

    // user name
    public String getUserName() {
        return mSharedPreferences.getString(mContext.getString(R.string.user_name_shared_preference), mContext.getString(R.string.empty_shared_preference));
    }

    public boolean setUserName(String userName) {
        mEditor.putString(mContext.getString(R.string.user_name_shared_preference), userName);
        return mEditor.commit();
    }

    // user Id
    public String getUserId() {
        return mSharedPreferences.getString(mContext.getString(R.string.user_id_shared_preference), mContext.getString(R.string.empty_shared_preference));
    }

    public boolean setUserId(String id) {
        mEditor.putString(mContext.getString(R.string.user_id_shared_preference), id);
        return mEditor.commit();
    }

    // company id
    public String getCompanyId() {
        return mSharedPreferences.getString(mContext.getString(R.string.company_id_shared_preference),mContext.getString(R.string.empty_shared_preference));
    }

    public boolean setCompanyId(String companyId) {
        mEditor.putString(mContext.getString(R.string.company_id_shared_preference), companyId);
        return mEditor.commit();
    }

    // UUid (AppClientId)
    public String getUUid() {
        String temp = mSharedPreferences.getString(mContext.getString(R.string.uuid_shared_preference), mContext.getString(R.string.empty_shared_preference));
        if (temp.equals("")) {
            temp = UUID.randomUUID().toString();
            setUUid(temp);
        }
        return temp;
    }

    public boolean setUUid(String uuid) {
        mEditor.putString(mContext.getString(R.string.uuid_shared_preference), uuid);
        return mEditor.commit();
    }

    // warning time
    public boolean setGatewayWduration(int wduration) {
        mEditor.putInt(mContext.getString(R.string.gateway_wduration_shared_preference), wduration);
        return mEditor.commit();
    }

    public int getGatewayWduration() {
        return mSharedPreferences.getInt(mContext.getString(R.string.gateway_wduration_shared_preference), -1);
    }

    // warning without sound
    public boolean setGatewayQuietmode(int quietmode) {
        mEditor.putInt(mContext.getString(R.string.gateway_quietmode_shared_preference), quietmode);
        return mEditor.commit();
    }

    public int getGatewayQuietmode() {
        return mSharedPreferences.getInt(mContext.getString(R.string.gateway_quietmode_shared_preference), -1);
    }

    // gateway current software version
    public String getGatewayCurrentSwVersion() {
        return mSharedPreferences.getString(mContext.getString(R.string.gateway_current_sw_version_shared_preference), mContext.getString(R.string.empty_shared_preference));
    }

    public boolean setGatewayCurrentSwVersion(String currentSw) {
        mEditor.putString(mContext.getString(R.string.gateway_current_sw_version_shared_preference), currentSw);
        return mEditor.commit();
    }

    // gateway latest software version
    public String getGatewayLatestSwVersion() {
        return mSharedPreferences.getString(mContext.getString(R.string.gateway_latest_sw_version_shared_preference), mContext.getString(R.string.empty_shared_preference));
    }

    public boolean setGatewayLatestSwVersion(String currentSw) {
        mEditor.putString(mContext.getString(R.string.gateway_latest_sw_version_shared_preference), currentSw);
        return mEditor.commit();
    }

    public boolean setAccountActive(int code){
        mEditor.putInt(mContext.getString(R.string.account_active_shared_preference), code);
        return mEditor.commit();
    }

    public int getAccountActive(){
        return mSharedPreferences.getInt(mContext.getString(R.string.account_active_shared_preference), -1);
    }

}
