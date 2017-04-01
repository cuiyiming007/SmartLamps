package com.glexer.smartlamp.mqtttest;

import android.content.Context;
import android.util.Log;

import com.glexer.smartlamp.eventbus.MqttEvent;
import com.glexer.smartlamp.eventbus.MqttEventType;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import de.greenrobot.event.EventBus;

/**
 * doing sth when operations(CONNECT,DISCONNECT,SUBSCRIBE,PUBLISHI)are success or fail
 */
public class ActionListener implements IMqttActionListener {

    public enum Action {
        /** Connect Action **/
        CONNECT,
        /** Disconnect Action **/
        DISCONNECT,
        /** Subscribe Action **/
        SUBSCRIBE,
        /** UNSubscribe Action **/
        UNSUBSCRIBE,
        /** Publish Action **/
        PUBLISH
    }
    /**
     * The {@link Action} that is associated with this instance of
     * <code>ActionListener</code>
     **/
    private Action action;
    /** The arguments passed to be used for formatting strings**/
    private String[] additionalArgs;

    private String clientHandle;
    /** {@link Context} for performing various operations **/
    //private Context context;

    private MqttAndroidClient client=null;

    public ActionListener(Action action, String clientHandle, String... additionalArgs) {
       // this.context = context;
        this.action = action;
        this.clientHandle = clientHandle;
        this.additionalArgs = additionalArgs;
    }

    /**
     * do sth when operation success
     * @param iMqttToken
     */
    @Override
    public void onSuccess(IMqttToken iMqttToken) {
        switch (action) {
            case CONNECT :
                Log.i("Mqtt", "连接成功。。。。。。。。。");
                MqttReqAPI.subscribeServerTopic();
                MqttEvent event1 = new MqttEvent(MqttEventType.MQTT_CONNECT, true);
                EventBus.getDefault().post(event1);
                break;
            case DISCONNECT :
                Log.i("Mqtt", "DISCONNECT成功。。。。。。。。。");
//                MqttEvent event2 = new MqttEvent(MqttEventType.MQTT_DISCONNECT, true);
//                EventBus.getDefault().post(event2);
                break;
            case SUBSCRIBE :
                //TODO:
                Log.i("Mqtt", "订阅成功。。订阅的主题是：" + additionalArgs[0]);
                break;
            case UNSUBSCRIBE :
                //TODO:
                Log.i("Mqtt", "取消订阅成功。。主题是：" + additionalArgs[0]);
                break;
            case PUBLISH :
                Log.i("Mqtt", "发布消息成功。。。。发布的主题是：" + additionalArgs[1]);
                break;
        }
    }

    /**
     * do sth when operation fail
     * @param iMqttToken
     * @param throwable
     */
    @Override
    public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
        switch (action) {
            case CONNECT :
                Log.i("Mqtt", "连接失败。。。。。。。。。");
                MqttEvent event = new MqttEvent(MqttEventType.MQTT_CONNECT, false);
                EventBus.getDefault().post(event);
                break;
            case DISCONNECT :
                // TODO:
                break;
            case SUBSCRIBE :
                // TODO:
                break;
            case PUBLISH :
                // TODO:
                Log.i("输出log", "发布消息失败。。。。发布的主题是：" + additionalArgs[1]);
                break;
        }

    }

}
