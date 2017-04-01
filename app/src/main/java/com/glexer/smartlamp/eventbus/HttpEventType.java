package com.glexer.smartlamp.eventbus;

/**
 * Created by Trice on 2016/1/22.
 */
public class HttpEventType {
    private static int id = 0;

    public HttpEventType(int id) {
        HttpEventType.id = id;
    }

    public static int nextid() {
        return id++;
    }

    public static final HttpEventType RegisterAccount = new HttpEventType(nextid());
    public static final HttpEventType LoginAccount = new HttpEventType(nextid());
    public static final HttpEventType GetMqttAddress = new HttpEventType(nextid());
    public static final HttpEventType GetDeviceList = new HttpEventType(nextid());
    public static final HttpEventType BindInternetDevice = new HttpEventType(nextid());
    public static final HttpEventType UnbindInternetDevice = new HttpEventType(nextid());
    public static final HttpEventType GetCameraList = new HttpEventType(nextid());
    public static final HttpEventType ChangeCameraName = new HttpEventType(nextid());
    public static final HttpEventType DeleteCamera = new HttpEventType(nextid());
    public static final HttpEventType BindYS = new HttpEventType(nextid());
    public static final HttpEventType UnBindYS = new HttpEventType(nextid());
    public static final HttpEventType Back = new HttpEventType(nextid());
    public static final HttpEventType ChangePassword = new HttpEventType(nextid());
    public static final HttpEventType ChangeGatewayName = new HttpEventType(nextid());
    public static final HttpEventType GetZigbeeHistory = new HttpEventType(nextid());

    public static final HttpEventType GetSmsCode = new HttpEventType(nextid());
    public static final HttpEventType CheckSmsCode = new HttpEventType(nextid());
    public static final HttpEventType ResetGetSmsCode = new HttpEventType(nextid());
    public static final HttpEventType ResetCheckSmsCode = new HttpEventType(nextid());
    public static final HttpEventType ResetPassword = new HttpEventType(nextid());


    public static final HttpEventType GetMqttConnect = new HttpEventType(nextid());
    public static final HttpEventType GetZigbeeList = new HttpEventType(nextid());

    public static final HttpEventType UpdateCameraStates = new HttpEventType(nextid());//更新摄像头在线状态
}
