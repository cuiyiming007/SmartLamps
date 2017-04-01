package com.glexer.smartlamp.utils;

import android.content.Context;
import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

public class Utils {

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5F);
    }

    public static long timestampOfDay() {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.HOUR_OF_DAY, 0);
        mCalendar.set(Calendar.MINUTE, 0);
        mCalendar.set(Calendar.SECOND, 0);
        Log.i("timestampOfDay", mCalendar.getTimeInMillis()/1000 + "");
        return mCalendar.getTimeInMillis()/1000;
    }

    public static String getMD5(String val){
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md5.update(val.getBytes());
        byte[] bytes = md5.digest();//加密

        int length = bytes.length;
        StringBuilder stringBuilder = new StringBuilder(2 * length);

        for (byte aByte : bytes) {
            char c0 = hexDigits[(aByte & 240) >> 4];
            char c1 = hexDigits[aByte & 15];
            stringBuilder.append(c0);
            stringBuilder.append(c1);
        }
        return stringBuilder.toString();
    }
}
