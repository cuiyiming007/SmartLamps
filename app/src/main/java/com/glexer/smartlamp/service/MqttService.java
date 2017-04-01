package com.glexer.smartlamp.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.glexer.smartlamp.data.SmartStoreSharedPreferences;
import com.glexer.smartlamp.data.model.MqttAddress;
import com.glexer.smartlamp.mqtttest.ActionListener;
import com.glexer.smartlamp.mqtttest.MqttCallbackHandler;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;


public class MqttService extends Service {


    public static boolean isConnected = false;
    public static MqttAndroidClient client;

    private static MqttService instance;

    public static MqttService getInstance() {
        if (instance == null) {
            instance = new MqttService();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("1111111111","service已经开始");
        String mClientId = "app_" + SmartStoreSharedPreferences.getInstence().getUUid();
        MqttAddress mqttAddress = SmartStoreSharedPreferences.getInstence().getMqttAddress();
        String uri = "tcp://" + mqttAddress.getUrl() + ":" + mqttAddress.getPort();
//        String uri="tcp://120.25.206.170:1883";
        client = new MqttAndroidClient(this, uri, mClientId);
        connect(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disconnect();
        client.unregisterResources();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }




    /**
     * 创建mqtt连接,订阅消息
     */
    public static void connect(Context context) {
        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);
        conOpt.setConnectionTimeout(1000);
        conOpt.setKeepAliveInterval(60);

        String mClientId = "app_" + SmartStoreSharedPreferences.getInstence().getUUid();
        MqttAddress mqttAddress = SmartStoreSharedPreferences.getInstence().getMqttAddress();
        String uri = "tcp://" + mqttAddress.getUrl() + ":" + mqttAddress.getPort();
//        String uri="tcp://120.25.206.170:1883";

        String clientHandle = uri + mClientId;

        String[] actionArgs = new String[1];
        actionArgs[0] = mClientId;

        final ActionListener callback = new ActionListener(ActionListener.Action.CONNECT, clientHandle, actionArgs);

        client.setCallback(new MqttCallbackHandler(context, clientHandle));
        try {
            client.connect(conOpt, null, callback);
        } catch (MqttException e) {
            Log.e(context.getClass().getCanonicalName(), "MqttException Occured", e);
        }
        isConnected = true;
    }

    /**
     * 订阅消息
     * @param topic
     */
    public static void subscribe(final String topic){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String clientHandle ="tcp://120.25.206.170:1883";
                String[] topics = new String[1];
                topics[0] = topic;
                try {
                    client.subscribe(topic, 0, null, new ActionListener( ActionListener.Action.SUBSCRIBE, clientHandle, topics));
                } catch (MqttException e) {
                    // Log.e(this.getClass().getCanonicalName(), "Failed to subscribe to" + "abc" + " the client with the handle " + clientHandle, e);
                }
            }
        }).start();
    }

    /**
     * 发布消息
     */
    public static void publish(final String topic, final byte[] msg, final int qos){

        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] args = new String[2];
                args[0] = msg.toString();
                args[1] = topic+";qos:"+qos+";retained:"+false;
                String clientHandle ="tcp://120.25.206.170:1883";

                try {
                    client.publish(topic, msg, 0, false, null, new ActionListener(ActionListener.Action.PUBLISH, clientHandle, args));
                } catch (MqttException e) {
                    Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + clientHandle, e);
                }
            }
        }).start();
    }

    /**
     * 取消订阅消息
     */
    public static void unsubscribe(final String topic){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] args = new String[1];
                args[0] = topic;
                String clientHandle ="tcp://120.25.206.170:1883";

                try {
                    client.unsubscribe(topic, null, new ActionListener(ActionListener.Action.UNSUBSCRIBE, clientHandle, args));
                } catch (MqttException e) {
                    Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + clientHandle, e);
                }
            }
        }).start();
    }

    public static void disconnect() {
        try {
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
