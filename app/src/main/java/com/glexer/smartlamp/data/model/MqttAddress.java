package com.glexer.smartlamp.data.model;

/**
 * Created by Trice on 2016/2/22.
 */
public class MqttAddress {
    /**
     * user : ttt
     * password : test
     * url : http://localhost
     * port : 1883
     */

    private String user;
    private String password;
    private String url;
    private String port;

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getPort() {
        return port;
    }
}
