package com.glexer.smartlamp.utils;

import com.glexer.smartlamp.R;
import com.glexer.smartlamp.application.SmartLampApplication;


/**
 *
 * Created by Trice on 2016/1/19.
 */
public class UrlUtils {



    public static String getFlashUrl() {
        return SmartLampApplication.getInstance().getResources().getString(R.string.server_host) + "/GCloudWeb/a/app/lift/sys/skip";
    }

    public static String getLoginUrl() {
        return SmartLampApplication.getInstance().getResources().getString(R.string.server_host) + "/GCloudWeb/a/login";
    }

    public static String getDeviceListUrl() {
        return SmartLampApplication.getInstance().getResources().getString(R.string.server_host) + "/GCloudWeb/a/app/lift/channel?pageSize=4";
    }

    public static String getQuestionUrl() {
        return SmartLampApplication.getInstance().getResources().getString(R.string.server_host) + "/GCloudWeb/a/app/lift/sys/help";
    }

    public static String getIntroductionUrl() {
        return SmartLampApplication.getInstance().getResources().getString(R.string.server_host) + "/GCloudWeb/a/app/lift/sys/advertise";
    }

    public static String getUserInfoUrl() {
        return SmartLampApplication.getInstance().getResources().getString(R.string.server_host) + "/GCloudWeb/a/sys/user/info";
    }

    public static String getHTTPHeadStr() {
        return SmartLampApplication.getInstance().getResources().getString(R.string.server_host) + "/GCloudWeb/a/"; //测试服务器
    }

    //    final static String HTTPHeadStr = "http://192.168.1.177:8080/GCloudAPI/api/";
    //final static String HTTPHeadStr = "http://121.199.21.14:8686/GCloudAPI/api/";
//    final static String HTTPHeadStr = "http://test.glexer.com:8888/GCloudAPI/api/"; //测试服务器
//    final static String HTTPHeadStr = "http://prod.glexer.com:8888/GCloudAPI/api/"; //正式服务器
}
