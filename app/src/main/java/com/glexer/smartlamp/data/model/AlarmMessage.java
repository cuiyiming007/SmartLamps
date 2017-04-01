package com.glexer.smartlamp.data.model;

import android.provider.BaseColumns;

/**
 * 报警信息
 */

public class AlarmMessage implements AlarmMessageColumns{
    private String title;
    private String id;
    private String description;
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

interface AlarmMessageColumns extends BaseColumns{
    String TITLE = "alarm_title";
    String ID = "alarm_id";
    String DESCRIPTION = "alarm_description";
    String TIME = "alarm_time";
}
