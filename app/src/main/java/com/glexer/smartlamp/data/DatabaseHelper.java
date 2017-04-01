package com.glexer.smartlamp.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.glexer.smartlamp.data.model.MasterDevice;
import com.glexer.smartlamp.data.model.AlarmMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Trice on 2016/1/26.
 */
public class DatabaseHelper extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "SmartLamp";
    public static final String DEVICES_TABLE = "device_table";
    public static final String ALARM_MESSAGE_TABLE = "alarm_message_table";

    public static final int DATABASE_VERSION = 1;


    public StringBuilder deviceStringBuilder;
    public StringBuilder alarmMessageStringBuilder;

    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context.getApplicationContext());

        return instance;
    }

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        deviceStringBuilder = new StringBuilder();
        alarmMessageStringBuilder = new StringBuilder();
    }

    private void initStringBuilder() {
        deviceStringBuilder.append("CREATE TABLE " + DEVICES_TABLE + " (");
        deviceStringBuilder.append(MasterDevice._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,");
        deviceStringBuilder.append(MasterDevice.DID + " VARCHAR,");
        deviceStringBuilder.append(MasterDevice.NAME + " VARCHAR,");
        deviceStringBuilder.append(MasterDevice.MAC + " VARCHAR,");
        deviceStringBuilder.append(MasterDevice.SN + " VARCHAR,");
        deviceStringBuilder.append(MasterDevice.BOXCODE + " VARCHAR,");
        deviceStringBuilder.append(MasterDevice.NAME + " VARCHAR,");
        deviceStringBuilder.append(MasterDevice.ONLINE + " INTEGER )");


        alarmMessageStringBuilder.append("CREATE TABLE " + ALARM_MESSAGE_TABLE + " (");
        alarmMessageStringBuilder.append(AlarmMessage._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,");
        alarmMessageStringBuilder.append(AlarmMessage.TITLE + " VARCHAR,");
        alarmMessageStringBuilder.append(AlarmMessage.ID + " VARCHAR,");
        alarmMessageStringBuilder.append(AlarmMessage.TIME + " VARCHAR,");
        alarmMessageStringBuilder.append(AlarmMessage.DESCRIPTION + " VARCHAR )");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        initStringBuilder();
        db.execSQL(alarmMessageStringBuilder.toString());
        db.execSQL(deviceStringBuilder.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DEVICES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ALARM_MESSAGE_TABLE);

        onCreate(db);
    }


    public void emptyTable(SQLiteDatabase db, String table) {
        db.execSQL("delete from " + table + " where 1=1");
    }


    /*public List<AlarmMessage> queryAlarmMessageList(SQLiteDatabase db, String selection, String[] selectionArgs) {
        List<AlarmMessage> mList = new ArrayList<>();
        AlarmMessage message;
        Cursor cursor = null;
        try {
            cursor = db.query(ALARM_MESSAGE_TABLE, null, selection, selectionArgs, null, null, AlarmMessage.DATE_TIME + " DESC");
            if (cursor != null) {
                int didIndex = cursor.getColumnIndex(AlarmMessage.D_ID);
                int gatewayNameIndex = cursor.getColumnIndex(AlarmMessage.GATEWAY_NAME);
                int deviceIdIndex = cursor.getColumnIndex(AlarmMessage.DEVICE_ID);
                int zoneTypeIndex = cursor.getColumnIndex(AlarmMessage.ZONE_TYPE);
                int nameIndex = cursor.getColumnIndex(AlarmMessage.NAME);
                int epIndex = cursor.getColumnIndex(AlarmMessage.EP);
                int nwkAddrIndex = cursor.getColumnIndex(AlarmMessage.NWK_ADDR);
                int dateTimeIndex = cursor.getColumnIndex(AlarmMessage.DATE_TIME);
                int wModeIndex = cursor.getColumnIndex(AlarmMessage.W_MODE);
                int statusIndex = cursor.getColumnIndex(AlarmMessage.STATUS);
                int descriptionIndex = cursor.getColumnIndex(AlarmMessage.DESCRIPTION);
                int _idIndex = cursor.getColumnIndex(AlarmMessage._ID);

                while (cursor.moveToNext()) {
                    message = new AlarmMessage();
                    message.setDid(cursor.getString(didIndex));
                    message.setGateway_name(cursor.getString(gatewayNameIndex));
                    message.setDevice_id(cursor.getString(deviceIdIndex));
                    message.setZone_type(cursor.getString(zoneTypeIndex));
                    message.setName(cursor.getString(nameIndex));
                    message.setEp(cursor.getString(epIndex));
                    message.setNwk_addr(cursor.getString(nwkAddrIndex));
                    message.setDate_time(cursor.getString(dateTimeIndex));
                    message.setW_mode(cursor.getInt(wModeIndex));
                    message.setStatus(cursor.getInt(statusIndex));
                    message.setDescription(cursor.getString(descriptionIndex));
                    message.setId(cursor.getString(_idIndex));
                    mList.add(message);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return mList;
    }

    public List<SceneModel> querySceneList(SQLiteDatabase db, String selection, String[] selectionArgs) {
        List<SceneModel> mList = new ArrayList<>();
        SceneModel sceneModel;
        Cursor cursor = null;
        try {
            cursor = db.query(SCENE_TABLE, null, selection, selectionArgs, null, null, SceneModel.ID + " ASC");
            if (cursor != null) {
                int idIndex = cursor.getColumnIndex(SceneModel.ID);
                int nameIndex = cursor.getColumnIndex(SceneModel.NAME);
                int actionNumIndex = cursor.getColumnIndex(SceneModel.ACTION_NUM);

                while (cursor.moveToNext()) {
                    sceneModel = new SceneModel();
                    sceneModel.setId(cursor.getInt(idIndex));
                    sceneModel.setName(cursor.getString(nameIndex));
                    sceneModel.setCount(cursor.getInt(actionNumIndex));
                    mList.add(sceneModel);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return mList;
    }

    public SQLiteStatement insertSceneStatement(SQLiteDatabase db) {
        return db.compileStatement(
                String.format("Insert into %s (%s, %s, %s) values (?,?,?)",
                        SCENE_TABLE,
                        SceneModel.ID,
                        SceneModel.NAME,
                        SceneModel.ACTION_NUM));
    }*/



    public void execSQL(SQLiteDatabase db, String sql) {
        db.execSQL(sql);
    }


    public SQLiteDatabase getSQLiteDatabase() {
        return getWritableDatabase();
    }
}
