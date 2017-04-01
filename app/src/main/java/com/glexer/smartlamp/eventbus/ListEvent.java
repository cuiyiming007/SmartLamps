package com.glexer.smartlamp.eventbus;

import java.util.List;

/**
 * Created by xiaofei on 2016/4/1.
 * post list<E>
 */
public class ListEvent {
    private List<? extends Object> data;
    private ListEventType type;

    public ListEvent(ListEventType type, List<? extends Object> data) {
        this.data = data;
        this.type = type;
    }

    public List<? extends Object> getData() {
        return data;
    }

    public void setData(List<? extends Object> data) {
        this.data = data;
    }

    public ListEventType getType() {
        return type;
    }

    public void setType(ListEventType type) {
        this.type = type;
    }


}
