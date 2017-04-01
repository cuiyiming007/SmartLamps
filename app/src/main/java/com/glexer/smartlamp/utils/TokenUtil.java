package com.glexer.smartlamp.utils;

import android.support.v4.util.LongSparseArray;
import android.util.Log;

import com.glexer.smartlamp.data.SmartStoreSharedPreferences;

import java.security.DigestException;


/**
 * Created by Trice on 2017/3/28.
 */

public class TokenUtil {
    private static LongSparseArray<String> tokenMap = new LongSparseArray<>();

    static public String getToken() {
        long timeStamp = Utils.timestampOfDay();
        String token = tokenMap.get(timeStamp);
        if (token == null) {
            String secret = SmartStoreSharedPreferences.getInstence().getSecretKey();
            String loginName = SmartStoreSharedPreferences.getInstence().getLoginName();
            String seed = secret +
                    loginName +
                    timeStamp;
            token = Utils.getMD5(seed);
            Log.i("getTokennew", token);
            tokenMap.clear();
            tokenMap.append(timeStamp, token);
        }
        Log.i("getToken", token);
        return token;
    }
}
