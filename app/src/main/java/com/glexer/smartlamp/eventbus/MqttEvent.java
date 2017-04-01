package com.glexer.smartlamp.eventbus;

/**
 * Created by Trice on 2016/1/28.
 */
public class MqttEvent {
    private Object data;
    private int type;
    private boolean isSuccess;

    public MqttEvent(int type, boolean isSuccess) {
        this.type = type;
        this.isSuccess = isSuccess;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
