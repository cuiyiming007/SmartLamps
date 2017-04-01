package com.glexer.smartlamp.eventbus;

/**
 * Created by Trice on 2016/1/22.
 */
public class HttpEvent {
    private Object data;
    private HttpEventType type;
    private boolean isSuccess;

    public HttpEvent(HttpEventType type, boolean isSuccess, Object data) {
        this.data = data;
        this.type = type;
        this.isSuccess = isSuccess;
    }

    public HttpEvent(HttpEventType type, boolean isSuccess) {
        this(type, isSuccess, null);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HttpEventType getType() {
        return type;
    }

    public void setType(HttpEventType type) {
        this.type = type;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
