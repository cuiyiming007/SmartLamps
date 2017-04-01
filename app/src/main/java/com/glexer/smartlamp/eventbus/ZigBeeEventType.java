package com.glexer.smartlamp.eventbus;

/**
 * Created by lenovo on 2016/1/23.
 */
public class ZigBeeEventType {
    /**
     * 请求和响应
     */
    public static final int GET_GATEWAY_INFO = 1;  //获取网关信息
    public static final int GET_ZIGBEE_DEVICE_LIST = 2;  //获取ZigBee设备列表
    public static final int ALLOW_IN_REQUEST = 3;  //允许入网
    public static final int DELETE_DEVICE_REQUEST = 4;//删除设备
    public static final int CHANGE_EP_NAME_REQUEST = 5;//修改EP名称
    public static final int DEVICE_ON_OFF_ARM_REQUEST = 6;//设备布撤防
    public static final int STOP_ALARM = 7;//停止报警
    public static final int SET_ZIGBEE_PROPERTY = 8;//ZigBee属性设置
    public static final int QUIET_MODE_SETTING = 9;//警号静音设置
    public static final int GET_GATEWAY_VERSION = 12;//获取网关版本
    public static final int SCENE_CONTROL = 13;//情景控制
    public static final int LINKAGE = 14;//联动
    public static final int GET_IPC_LIST = 15;//获取网关局域网内IPC列表
    public static final int SET_SWITCH_STATE = 16;//设置设备开关状态

    /**
     * 状态更新
     */
    public static final int NEW_DEVICE = 300;//新增设备
    public static final int DELETE_DEVICE_STATE_UPDATE = 301;//删除设备
    public static final int DEVICE_ONLINE_STATE_UPDATE = 302;//设备在线状态更新
    public static final int EP_NAME_UPDATE = 303;//EP名称更新
    public static final int EP_ON_OFF_ARM_STATE_UPDATE = 304;//EP布撤防状态更新
    public static final int DEVICE_ALARM_MESSAGE = 305;//设备告警
    public static final int ZIGBEE_PROPERTY_UPDATE = 306;//zigbee属性更新
    public static final int QUIET_MODE_UPDATE = 307;//警号静音更新
    public static final int GATEWAY_INITIALIZATION = 309;//网关初始化
    public static final int GATEWAY_UPDATE = 310;//网关开始更新
    public static final int GATEWAY_UPDATE_COMPLETE = 311;//网关更新完成
    public static final int TEMPERATURE_UPDATE = 312;//温度更新
    public static final int HUMIDITY_UPDATE = 313;//湿度更新

    public static final int SCENE_CONTROL_UPDATE = 315;//情景控制上报
    public static final int LINKAGE_UPDATE = 316;//联动更新
    public static final int IPC_LINKAGE_UPDATE = 317;//IPC联动告警更新
    public static final int SWITCH_STATES_UPDATE = 319;//智能开关状态上报


    public static final int BIND_GATEWAY = 1000;//delete gateway
}
