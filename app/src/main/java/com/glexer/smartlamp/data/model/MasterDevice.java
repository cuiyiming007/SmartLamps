package com.glexer.smartlamp.data.model;

import android.provider.BaseColumns;
import android.support.annotation.NonNull;

/**
 * 集中管理器
 */

public class MasterDevice implements MasterDeviceColumns, Comparable<MasterDevice> {


    /**
     * did : xxx
     * name : xxx
     * mac : xxx
     * sn : xxx
     * online : 1
     * boxCode : xxx
     */

    private String did;
    private String name;
    private String mac;
    private String sn;
    private int online;
    private String boxCode;
    private int configed; // 0 for no, 1 for yes


    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfiged() {
        return configed;
    }

    public void setConfiged(int configed) {
        this.configed = configed;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public String getBoxCode() {
        return boxCode;
    }

    public void setBoxCode(String boxCode) {
        this.boxCode = boxCode;
        configed = boxCode.isEmpty() ? 0 : 1;
    }

    @Override
    public int compareTo(@NonNull MasterDevice another) {
        if (this.configed > another.configed) {
            return 1;
        } else if (this.configed < another.configed) {
            return -1;
        } else {
            if (this.online > another.online) {
                return 1;
            } else if (this.online < another.online) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

interface MasterDeviceColumns extends BaseColumns {
    String DID = "device_did";
    String NAME = "device_name";
    String MAC = "device_mac";
    String SN = "devcie_sn";
    String ONLINE = "device_online";
    String BOXCODE = "device_boxcode";
}
