package com.glexer.smartlamp.mqtttest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/1/22.
 */
public class ProtoclBuffer {


    /**
     * 发送的消息打包
     * 创建SSMsg，将消息打包成ByteArrayOutputStream创建SSMsg
     *
     * @return
     */
    public static byte[] ssmsgBuild(SmartStore.SSMsg.Builder ssmsg) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ssmsg.build().writeTo(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] messages = output.toByteArray();

        //add pakage header
        byte[] static_header = int2Byte(2);

        byte[] comader = new byte[2];
        comader[0] = 0;
        comader[1] = 0;

        byte[] lengths = int2Byte(comader.length + messages.length);
        List<byte[]> byteList = new ArrayList<>();
        byteList.add(static_header);
        byteList.add(lengths);
        byteList.add(comader);
        byteList.add(messages);
        byte[] aa = streamCopy(byteList);
        return aa;
    }

    public static byte[] ssmsgBuild(int msgtype, String nwk_addr, String ep, int onoff, String ieee) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(msgtype);
        ssmsg.setNwkAddr(nwk_addr);
        ssmsg.setEp(ep);
        ssmsg.setOnOff(onoff);
        ssmsg.setIeee(ieee);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ssmsg.build().writeTo(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] messages = output.toByteArray();

        //add pakage header
        byte[] static_header = int2Byte(2);

        byte[] comader = new byte[2];
        comader[0] = 0;
        comader[1] = 0;

        byte[] lengths = int2Byte(comader.length + messages.length);
        List<byte[]> byteList = new ArrayList<>();
        byteList.add(static_header);
        byteList.add(lengths);
        byteList.add(comader);
        byteList.add(messages);
        byte[] aa = streamCopy(byteList);
        return aa;
    }

    public static byte[] ssmsgBuild(int msgtype, int result, String nwk_addr, String ep, int onoff, String ieee) {
        SmartStore.SSMsg.Builder ssmsg = SmartStore.SSMsg.newBuilder();
        ssmsg.setMsgtype(msgtype);
        ssmsg.setResult(result);
        ssmsg.setNwkAddr(nwk_addr);
        ssmsg.setEp(ep);
        ssmsg.setOnOff(onoff);
        ssmsg.setIeee(ieee);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            ssmsg.build().writeTo(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] messages = output.toByteArray();

        //add pakage header
        byte[] static_header = int2Byte(2);

        byte[] comader = new byte[2];
        comader[0] = 0;
        comader[1] = 0;

        byte[] lengths = int2Byte(comader.length + messages.length);
        List<byte[]> byteList = new ArrayList<>();
        byteList.add(static_header);
        byteList.add(lengths);
        byteList.add(comader);
        byteList.add(messages);
        byte[] aa = streamCopy(byteList);
        return aa;
    }

    public static byte[] int2Byte(int intValue) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (intValue >> 8 * (i) & 0xFF);
            //System.out.print(Integer.toBinaryString(b[i])+" ");
            //System.out.print((b[i] & 0xFF) + " ");
        }
        return b;
    }

    public static byte[] streamCopy(List<byte[]> srcArrays) {
        byte[] destAray = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            for (byte[] srcArray : srcArrays) {
                bos.write(srcArray);
            }
            bos.flush();
            destAray = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
            }
        }
        return destAray;
    }

    /**
     * protocl解析
     */
    public SmartStore.SSMsg undecodeSSMSG(ByteArrayInputStream input) {
        // 反序列化
        SmartStore.SSMsg ssmsg = null;
        try {
            ssmsg = SmartStore.SSMsg.parseFrom(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ssmsg;
    }

    public static SmartStore.SSMsg undecodeSSMSG(byte[] input) {
        // 反序列化
        SmartStore.SSMsg ssmsg = null;
        try {
            ssmsg = SmartStore.SSMsg.parseFrom(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ssmsg;
    }

    public static int byte2Int(byte[] b) {
        int intValue = 0;
        for (int i = 0; i < b.length; i++) {
            intValue += (b[i] & 0xFF) << (8 * (i));
        }
        return intValue;
    }
}
