package com.glexer.smartlamp.mqtttest;

/**
 * the procedure to create mqtt connection ,subscribe message and publish message
 */
public class MqttOperationDetail {

    /** creat new connection
     *
     * private void connectAction(){

     MqttConnectOptions conOpt = new MqttConnectOptions();
     conOpt.setCleanSession(false);
     conOpt.setConnectionTimeout(1000);
     conOpt.setKeepAliveInterval(0);

     String mClientId=clientId.getText().toString();
     String uri="tcp://"+server.getText().toString()+":"+Integer.parseInt(port.getText().toString());

     String clientHandle = uri + mClientId;

     String[] actionArgs = new String[1];
     actionArgs[0] = mClientId;

     final ActionListener callback = new ActionListener(getActivity(),ActionListener.Action.CONNECT, clientHandle, actionArgs);

     client=new MqttAndroidClient(getActivity(),uri,mClientId);

     client.setCallback(new MqttCallbackHandler(getActivity(), clientHandle));
     try {
        client.connect(conOpt, null, callback);
     } catch (MqttException e) {
         Log.e(this.getClass().getCanonicalName(), "MqttException Occured", e);
     }

     }
     */

    /**
     *      SUBSCRIBE MESSAGE
     *
     *      String clientHandle ="tcp://iot.eclipse.org:1883";
            String[] topics = new String[1];
            topics[0] = "abc";
             try {
                client.subscribe("abc", 0, null, new ActionListener(getActivity(), ActionListener.Action.SUBSCRIBE, clientHandle, topics));
             } catch (MqttSecurityException e) {
                Log.e(this.getClass().getCanonicalName(), "Failed to subscribe to" + "abc" + " the client with the handle " + clientHandle, e);
             }
             catch (MqttException e) {
             Log.e(this.getClass().getCanonicalName(), "Failed to subscribe to" + "abc" + " the client with the handle " + clientHandle, e);
             }
     */

    /**
     *      PUBLISH MESSAGE
     *
     *      String message="hello....!!!";
             String[] args = new String[2];
             args[0] = message;
             args[1] = "abc"+";qos:"+0+";retained:"+false;
             String clientHandle ="tcp://iot.eclipse.org:1883";

             try {
             client.publish("abc", message.getBytes(), 0, false, null, new ActionListener(getActivity(), ActionListener.Action.PUBLISH, clientHandle, args));
             }
             catch (MqttSecurityException e) {
             Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + clientHandle, e);
             }
             catch (MqttException e) {
             Log.e(this.getClass().getCanonicalName(), "Failed to publish a messged from the client with the handle " + clientHandle, e);
             }
     *
     */

}
