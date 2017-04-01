package com.glexer.smartlamp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.glexer.smartlamp.eventbus.StringEvent;

import de.greenrobot.event.EventBus;

public class NetWorkChangeReceiver extends BroadcastReceiver {
    public final String ACTION = "android.net.conn.CONNECTIVITY_CHANGE";

    public static boolean netWorkStatus = true;
//    private boolean startWork = false; // 注册Reciever时不执行动作(第一次不执行)

    public NetWorkChangeReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION.equals(intent.getAction())) {

            ConnectivityManager connManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

            // 判断有无网络连接
            if (networkInfo == null || !networkInfo.isConnected()) {
                netWorkStatus = false;
            } else {
                netWorkStatus = true;
//                if (startWork && !MqttService.client.isConnected())
//                    MqttService.connect(context);
            }
//            startWork = true;
            EventBus.getDefault().post(StringEvent.NET_STATUS_CHANGE);
        }
//        throw new UnsupportedOperationException("Not yet implemented");
    }
}
