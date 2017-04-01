package com.glexer.smartlamp.eventbus;

/**
 * Created by Trice on 2016/1/28.
 */
public class MqttEventType {
    // mqtt服务状态
    private static int id = 100;
    public static final int MQTT_CONNECT = id++;
    public static final int MQTT_DISCONNECT = id++;
    public static final int MQTT_CONNECT_LOST = id++;

    public static final int LINKAGE_LIST = id++;

    // 服务器二进制信息
    public static final int SERVER_GATEWAY_ONLINE = 1;
}
