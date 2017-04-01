package com.glexer.smartlamp.data.fakedata;

import com.glexer.smartlamp.data.model.MasterDevice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trice on 2017/3/23.
 */

public class LocalDataSoures {

    public static List<MasterDevice> getSampleMasterDevice(int lenth) {
        List<MasterDevice> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            MasterDevice device = new MasterDevice();
            device.setName("集中管理器" + i);
            device.setDid("" + (10000 + i));
            device.setBoxCode(i % 2 == 0 ? "" + (10000 + i) : "");
            device.setMac("" + (10000 + i));
            device.setSn("" + (i % 2) + (i * 1000));
            device.setOnline(i % 3 == 1 ? 0 : 1);
            list.add(device);
        }
        return list;
    }
}
