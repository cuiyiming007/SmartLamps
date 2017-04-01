package com.glexer.smartlamp.volley;

import com.glexer.smartlamp.BuildConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Trice on 2016/1/19.
 */
public class VolleyUtils {

    public final static String APP_ID = "96bf22cb0022408c89c3347804744517";
    final static String APP_SECRET = "wcxKDwb6cmCuRZ910imDiNCbjEFEzc2U";
//    final static String SIGNATURE = MD5Util.getMD5String(APP_ID + APP_SECRET);

    static String Token = "";

    public static void setToken(String token) {
        Token = token;
    }

    public static Map<String, String> Header_Signature;

    static {
        Header_Signature = new HashMap<>();
        Header_Signature.put("app_id", APP_ID);
//        Header_Signature.put("signature", SIGNATURE);
    }

    public static Map<String, String> Header_Token;

    static {
        Header_Token = new HashMap<>();
        Header_Token.put("token", Token);
    }

    public static String getHTTPHeadStr() {
        if (BuildConfig.DEBUG) {
            return "http://test.glexer.com:8888/GCloudAPI/api/"; //测试服务器
        } else {
            return "http://prod.glexer.com:8888/GCloudAPI/api/"; //正式服务器
        }
    }

    //    final static String HTTPHeadStr = "http://192.168.1.177:8080/GCloudAPI/api/";
    //final static String HTTPHeadStr = "http://121.199.21.14:8686/GCloudAPI/api/";
//    final static String HTTPHeadStr = "http://test.glexer.com:8888/GCloudAPI/api/"; //测试服务器
//    final static String HTTPHeadStr = "http://prod.glexer.com:8888/GCloudAPI/api/"; //正式服务器
}
