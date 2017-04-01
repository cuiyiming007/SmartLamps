package com.glexer.smartlamp.mqtttest;

import android.util.Log;

import com.glexer.smartlamp.data.SmartStoreSharedPreferences;
import com.glexer.smartlamp.service.MqttService;
import com.glexer.smartlamp.volley.VolleyUtils;

import java.util.List;

/**
 * Created by lenovo on 2016/1/26.
 */
public class MqttReqAPI {


//    private static String publish_recive_topic = "app2dev/" + SmartStoreSharedPreferences.getInstence().get + "/" +;
//    private static String publish_request_topic = "app2dev/did/" +;

    private static MqttReqAPI mInstance;

    public static synchronized MqttReqAPI getInstance() {
        if (mInstance == null) {
            mInstance = new MqttReqAPI();
        }
        return mInstance;
    }

    //====================================subscribe==========================================

    public static void subscribeServerTopic() {
        MqttService.subscribe("ser2cli_noti/" + SmartStoreSharedPreferences.getInstence().getUUid());
        MqttService.subscribe("ser2cli_noti/");
        MqttService.subscribe("ser2cli_noti/" + VolleyUtils.APP_ID);
    }

    public static void subscribeGatewayTopic(String did) {
        MqttService.subscribe("dev2app/" + did + "/" + SmartStoreSharedPreferences.getInstence().getUUid());
        MqttService.subscribe("dev2app/" + did);
    }
    //====================================publish==========================================

    private static String getPublish_control_topic(String did) {
        return "app2dev/" + did + "/" + SmartStoreSharedPreferences.getInstence().getUUid();
    }

    /**
     * 获取网关信息
     */
    public static void mqttGetGateWayInfor(String did) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(1);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 获取ZigBee设备列表
     */
    public static void mqttGetZigbeeDeviceList(String did) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(2);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 允许入网
     */
    public static void mqttAllowIn(String did) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(3);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 删除设备
     */
    public static void mqttDeleteDevice(String did, String nwk_addr, String ep, String ieee) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(4);
        ssmsg.setNwkAddr(nwk_addr);
        ssmsg.setEp(ep);
        ssmsg.setIeee(ieee);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 修改设备名称
     */
    public static void mqttChangeDeviceName(String did, String nwk_addr, String ep, String ep_name) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(5);
        ssmsg.setNwkAddr(nwk_addr);
        ssmsg.setEp(ep);
        ssmsg.setEpName(ep_name);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 设备布撤防
     */
    public static void mqttDeviceArmed(String did, String nwk_addr, String ep, int arm) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(6);
        ssmsg.setNwkAddr(nwk_addr);
        ssmsg.setEp(ep);
        ssmsg.setArm(arm);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 停止报警
     */
    public static void mqttStopAlarm(String did) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(7);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * zigBee属性设置
     */
    public static void mqttSetZigbeeProperty(String did, String nwk_addr, String ep, int on_off) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(8);
        ssmsg.setNwkAddr(nwk_addr);
        ssmsg.setEp(ep);
        ssmsg.setOnOff(on_off);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 警号静音设置
     */
    public static void mqttQuietModeSet(String did, int on_off) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(9);
        ssmsg.setOnOff(on_off);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 网关升级
     */
    public static void mqttGetGateWayUpdate(String did) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(11);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 获取网关版本号
     */
    public static void mqttGetGateWayVersion(String did) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(12);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 获取情景列表
     */
    public static void mqttGetSceneList(String did) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(13);
        ssmsg.setSubtype(1);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }


    /**
     * 删除联动列表
     */
    public static void mqttDeleteLinkage(String did, int id) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(14);
        ssmsg.setSubtype(3);
        ssmsg.setId(id);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 启用联动列表
     */
    public static void mqttEnableLinkage(String did, int id, int enable) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(14);
        ssmsg.setSubtype(4);
        ssmsg.setId(id);
        ssmsg.setEnable(enable);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 获取局域网IPC列表
     */
    public static void mqttGetIpcList(String did) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(15);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }

    /**
     * 开关面板状态设置
     */
    public static void mqttSetSwitchStates(String did, String nwk_addr, String ep, int on_off) {
        Log.i("mqttreqapi", "调用开关面板开关接口");
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(16);
        ssmsg.setNwkAddr(nwk_addr);
        ssmsg.setEp(ep);
        ssmsg.setOnOff(on_off);
        byte[] msg = ProtoclBuffer.ssmsgBuild(ssmsg);
        MqttService.publish(getPublish_control_topic(did), msg, 0);
    }
}
