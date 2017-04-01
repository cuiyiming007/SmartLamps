package com.glexer.smartlamp.eventbus;

/**
 * Created by xiaofei on 2016/4/1.
 */
public class ListEventType {
    private static int id = 0;

    public static int nextid() {
        return id++;
    }

    public ListEventType(int id) {
        ListEventType.id = id;
    }

    public static final ListEventType SceneActionList = new ListEventType(nextid());
    public static final ListEventType LinkageList = new ListEventType(nextid());

}
