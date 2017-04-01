package com.glexer.smartlamp.mqtttest;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.glexer.smartlamp.R;

import com.glexer.smartlamp.eventbus.HttpEvent;
import com.glexer.smartlamp.eventbus.HttpEventType;
import com.glexer.smartlamp.eventbus.MqttEvent;
import com.glexer.smartlamp.eventbus.MqttEventType;
import com.glexer.smartlamp.eventbus.ZigBeeEventType;
import com.glexer.smartlamp.service.MqttService;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.greenrobot.event.EventBus;

/**
 * necessary callback when creating a new mqtt connection.
 * deal with the situations: message arrive and connection lost
 */
public class MqttCallbackHandler implements MqttCallback {
    private String TAG = "MqttCallbackHandler";
    private Context context;
    private String clientHandle;

    public MqttCallbackHandler(Context context, String clientHandle) {
        this.context = context;
        this.clientHandle = clientHandle;

    }

    @Override
    public void connectionLost(Throwable throwable) {
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        // 判断有无网络连接
        if (networkInfo == null || !networkInfo.isConnected()) {
//            MqttEvent event = new MqttEvent(MqttEventType.MQTT_CONNECT_LOST, true);
//            EventBus.getDefault().post(event);
        } else {
            MqttService.connect(context);
        }
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        byte[] payLoad = message.getPayload();
        byte[] msg_types = new byte[4];
        System.arraycopy(payLoad, 0, msg_types, 0, 4);
        int mes_type = ProtoclBuffer.byte2Int(msg_types);

        byte[] msg_lengths = new byte[4];
        System.arraycopy(payLoad, 4, msg_lengths, 0, 4);
        int msg_length = ProtoclBuffer.byte2Int(msg_lengths) - 2;

        byte[] msg_command = new byte[2];
        System.arraycopy(payLoad, 8, msg_command, 0, 2);
        byte[] mqtt_msg = new byte[msg_length];
        System.arraycopy(payLoad, 10, mqtt_msg, 0, msg_length);
//        Log.i(TAG, "接收消息成功。。。。。。。。。topic==" + topic + " ~~~message=" + mes_type);
        switch (mes_type) {
            case 1: //json
                break;
            case 2: //protocol buffer
                handleMqttMessage(topic, mqtt_msg);
                break;
            case 3: //binary
                handleBinaryMessage(topic, msg_command, mqtt_msg);
                break;
        }

    }

    private void handleBinaryMessage(String topic, byte[] command, byte[] msg) {
        String[] temp = topic.split("/");
        String did = temp[1];
        int command_msg = ProtoclBuffer.byte2Int(command);
        int message = ProtoclBuffer.byte2Int(msg);
        switch (command_msg) {
            case 1: //gateway online
                MqttEvent event1 = new MqttEvent(MqttEventType.SERVER_GATEWAY_ONLINE, message == 1);
                event1.setData(did);
                EventBus.getDefault().post(event1);
                break;
        }
    }

    private void handleMqttMessage(final String topic, final byte[] msg) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String did = "";
                if (topic.contains("/")) {
                    String[] temp = topic.split("/");
                    did = temp[1];
                }
                SmartStore.SSMsg ssmsg = ProtoclBuffer.undecodeSSMSG(msg);
                int msgType = ssmsg.getMsgtype();
                Log.i(TAG, "接收消息成功。。。。。。。。。topic==" + topic + " ~~~message=" + msgType);

            }
        }).start();
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        //TODO:do nothing in the demo
    }


    void makeNotify(Intent i, String title, String message, int id) {

        NotificationManager notifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Bitmap btm = BitmapFactory.decodeResource(context.getResources(), R.drawable.app);

        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        // PendingIntent
        PendingIntent contentIntent = PendingIntent.getActivity(context, id, i, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setLargeIcon(btm)
                .setTicker("报警消息")
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.app)
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        notifyManager.notify(id, mBuilder.build());

    }
}
