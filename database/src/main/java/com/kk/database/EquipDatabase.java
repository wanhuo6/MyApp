package com.kk.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

import com.kk.database.model.CoursePlayModel;
import com.kk.database.model.CoursePlayUpload;
import com.kk.database.model.EquipSyncDataModel;
import com.kk.database.model.LocationInfoModel;
import com.kk.database.model.RunPageCollectModel;
import com.kk.database.model.RunPageModel;

import java.util.ArrayList;

/**
 * Created by Ma Zhihua on 2016/8/8.
 * Description :
 */
public class EquipDatabase {

    private DatabaseHelper dbHelper;

    private static final String TAG = EquipDatabase.class.getSimpleName();

    public EquipDatabase(Context context) {
        super();
        dbHelper = new DatabaseHelper(context);
    }

    /**
     * ================================================跑步页面记录操作===================================================
     */
    public synchronized void insertRunRecord(RunPageModel rpm) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.execSQL(RunPageModel.RunPageColumn.INSERT_SQL,
                new Object[]{rpm.getHeartBeats(), rpm.getSteps(), rpm.getKcal(), rpm.getLng(), rpm.getLat()
                        , rpm.getTime(), rpm.getAccuracy(), rpm.getSpeed(), rpm.getIsPause(), rpm.getSessionId()});

        sqlite.close();
    }


    public synchronized void deleteRunRecord(String sessionId) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.beginTransaction();
        sqlite.execSQL(RunPageModel.RunPageColumn.DELETE_SQL, new String[]{sessionId});
        sqlite.setTransactionSuccessful();
        sqlite.endTransaction();
        sqlite.close();
    }

    public synchronized ArrayList<RunPageModel> queryRunRecords(String sessionId) {

        if (TextUtils.isEmpty(sessionId)) {
            return null;
        }

        ArrayList<RunPageModel> records = new ArrayList<>();
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        Cursor cursor = sqlite.rawQuery(RunPageModel.RunPageColumn.QUERY_URL_SQL, new String[]{sessionId});
        while (cursor.moveToNext()) {
            RunPageModel rpm = new RunPageModel();
            rpm.setAccuracy(cursor.getInt(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.ACCURACY)));
            rpm.setHeartBeats(cursor.getInt(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.HEARTBEATS)));
            rpm.setKcal(cursor.getInt(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.KCAL)));
            rpm.setSteps(cursor.getInt(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.STEPS)));
            rpm.setIsPause(cursor.getString(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.ISPAUSE)));
            rpm.setSessionId(cursor.getString(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.SESSIONID)));
            rpm.setLng(cursor.getString(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.LNG)));
            rpm.setLat(cursor.getString(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.LAT)));
            rpm.setSpeed(cursor.getString(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.SPEED)));
            rpm.setTime(cursor.getString(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.TIME)));
            records.add(rpm);
        }
        sqlite.close();
        return records;
    }


    public synchronized void updateRunRecord() {
        //暂时不需要更新功能，如有需求，后期扩展
    }


    /**
     * 查询在一次跑步结束后保存的所有心率数据，需要上传给服务器
     *
     * @param sessionId
     * @return
     */
    public synchronized ArrayList<Integer> queryRunHbWithSessionId(String sessionId) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        Cursor cursor = sqlite.rawQuery(RunPageModel.RunPageColumn.QUERY_HB_WITH_SESSIONID, new String[]{sessionId});
        ArrayList<Integer> records = new ArrayList<>();
        while (cursor.moveToNext()) {
            records.add(cursor.getInt(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.HEARTBEATS)));
        }
        return records;
    }


    /**
     * 查询在一次跑步结束后保存的所有位置数据，需要上传给服务器
     *
     * @param sessionId
     * @return
     */
    public synchronized ArrayList<LocationInfoModel> queryLocationInfoWithSessionId(String sessionId) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        Cursor cursor = sqlite.rawQuery(RunPageModel.RunPageColumn.QUERY_LOCATION_WITH_SESSIONID, new String[]{sessionId});
        ArrayList<LocationInfoModel> records = new ArrayList<>();
        while (cursor.moveToNext()) {
            LocationInfoModel locationInfoModel = new LocationInfoModel();
            locationInfoModel.setLat(cursor.getString(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.LAT)));
            locationInfoModel.setLng(cursor.getString(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.LNG)));
            locationInfoModel.setPause(cursor.getString(cursor.getColumnIndexOrThrow(RunPageModel.RunPageColumn.ISPAUSE)));
            records.add(locationInfoModel);
        }
        return records;
    }


    /**================================================跑步页面记录操作===================================================*/


    /**
     * ================================================跑步页面主表操作===================================================
     */
    public synchronized void insertRunCollectRecord(RunPageCollectModel rpcm) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.execSQL(RunPageCollectModel.RunPageCollect.INSERT_SQL,
                new Object[]{rpcm.getStartime(), rpcm.getEndTime(), rpcm.getStatus(), rpcm.getDuration(), rpcm.getDistance()
                        , rpcm.getSteps(), rpcm.getSessionId(), rpcm.getKcal(), rpcm.getPs()});

        sqlite.close();
    }


    public synchronized void deleteRunCollectRecord(String sessionId) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.beginTransaction();
        sqlite.execSQL(RunPageCollectModel.RunPageCollect.DELETE_SQL, new String[]{sessionId});
        sqlite.setTransactionSuccessful();
        sqlite.endTransaction();
        sqlite.close();
    }

    public synchronized void updateRunCollectRecord(ContentValues values) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        String sessionid = (String) values.get(RunPageCollectModel.RunPageCollect.SESSIONID);
        sqlite.update(RunPageCollectModel.RunPageCollect.TABLE_NAME, values, "sessionId = ?", new String[]{sessionid});
//        sqlite.execSQL(RunPageCollectModel.RunPageCollect.UPDATE_SQL, new Object[]{entity.downed, String.valueOf(entity.dataId)});
        sqlite.close();
    }

    public synchronized ArrayList<RunPageCollectModel> queryRunCollectRecords() {
        ArrayList<RunPageCollectModel> records = new ArrayList<>();
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        Cursor cursor = sqlite.query(RunPageCollectModel.RunPageCollect.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            RunPageCollectModel rpm = new RunPageCollectModel();
            rpm.setSteps(cursor.getInt(cursor.getColumnIndexOrThrow(RunPageCollectModel.RunPageCollect.STEPS)));
            rpm.setKcal(cursor.getInt(cursor.getColumnIndexOrThrow(RunPageCollectModel.RunPageCollect.KCAL)));
            rpm.setSessionId(cursor.getString(cursor.getColumnIndexOrThrow(RunPageCollectModel.RunPageCollect.SESSIONID)));
            rpm.setDistance(cursor.getString(cursor.getColumnIndexOrThrow(RunPageCollectModel.RunPageCollect.DISTANCE)));
            rpm.setDuration(cursor.getString(cursor.getColumnIndexOrThrow(RunPageCollectModel.RunPageCollect.DURATION)));
            rpm.setStatus(cursor.getString(cursor.getColumnIndexOrThrow(RunPageCollectModel.RunPageCollect.STATUS)));
            rpm.setEndTime(cursor.getString(cursor.getColumnIndexOrThrow(RunPageCollectModel.RunPageCollect.ENDTIME)));
            rpm.setStartime(cursor.getString(cursor.getColumnIndexOrThrow(RunPageCollectModel.RunPageCollect.STARTIME)));
            records.add(rpm);
        }
        sqlite.close();
        return records;
    }


    /**================================================跑步页面主表操作===================================================*/


    /**
     * ================================================视频课程记录操作===================================================
     */
    public synchronized void insertPlayRecord(CoursePlayModel rpcm) {
        Log.e(TAG, "视频数据添加");
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.execSQL(CoursePlayModel.CoursePlayColumn.INSERT_SQL,
                new Object[]{rpcm.getHeartbeats(), rpcm.getName(), rpcm.getMusicPosition(), rpcm.getActionIndex(), rpcm.getCompleted()
                        , rpcm.getCals(), rpcm.getSecond(), rpcm.getAllSize(), rpcm.getCoureShre(), rpcm.getImageUrl(), rpcm.getBeforCals()});
        sqlite.close();
    }


    /**
     * 更新视频课程记录
     */
    public synchronized void updatePlayRecord(CoursePlayModel coursePlayModel) {
        Log.e(TAG, "视频数据更新");
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.execSQL(CoursePlayModel.CoursePlayColumn.UP_DATE, new Object[]{coursePlayModel.getActionIndex(),coursePlayModel.getMusicPosition(),coursePlayModel.getSecond(),coursePlayModel.getCals(),coursePlayModel.getBeforCals(),coursePlayModel.getName()});
        sqlite.close();
    }

    public synchronized void deleteCoursePlayRecord(String name) { // name = courseCode + uid
        if (TextUtils.isEmpty(name)) {
            return;
        }
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.beginTransaction();
        sqlite.execSQL(CoursePlayModel.CoursePlayColumn.DELETE_SQL, new String[]{name});
        sqlite.setTransactionSuccessful();
        sqlite.endTransaction();
        sqlite.close();
    }

    public synchronized void updateCoursePlayRecord() {
        //暂时不需要更新功能，如有需求，后期扩展
    }

    public synchronized void insetPlayRecord(CoursePlayModel coursePlayModel) {
        if (isCoursePlayRecord(coursePlayModel.getName())) {
            Log.e(TAG, "视频记录.有数据");
            updatePlayRecord(coursePlayModel);
        } else {
            Log.e(TAG, "视频记录.无数据");
            insertPlayRecord(coursePlayModel);
        }
    }

    public boolean isCoursePlayRecord(String name) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        Cursor cursor = sqlite.rawQuery(CoursePlayModel.CoursePlayColumn.QUERY_URL_SQL, new String[]{name});
        if (cursor.getCount() > 0) {
            Log.e(TAG, "数据条数 : " + String.valueOf(cursor.getCount()));
            return true;
        } else {
            Log.e(TAG, "没有视频数据记录");
        }
        sqlite.close();
        return false;
    }

    public synchronized  CoursePlayModel  queryCoursePlayRecords(String name) {

        if (TextUtils.isEmpty(name)) {
            return null;
        }
        CoursePlayModel rpm = new CoursePlayModel();
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        Cursor cursor = sqlite.rawQuery(CoursePlayModel.CoursePlayColumn.QUERY_URL_SQL, new String[]{name});
        while (cursor.moveToNext()) {
            rpm.setHeartbeats(cursor.getInt(cursor.getColumnIndexOrThrow(CoursePlayModel.CoursePlayColumn.HEARTBEATS)));
            rpm.setMusicPosition(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayModel.CoursePlayColumn.MUSICPOSITION)));
            rpm.setCals(cursor.getInt(cursor.getColumnIndexOrThrow(CoursePlayModel.CoursePlayColumn.CALS)));
            rpm.setAllSize(cursor.getInt(cursor.getColumnIndexOrThrow(CoursePlayModel.CoursePlayColumn.ALLSIZE)));
            rpm.setSecond(cursor.getInt(cursor.getColumnIndexOrThrow(CoursePlayModel.CoursePlayColumn.SECOND)));
            rpm.setName(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayModel.CoursePlayColumn.NAME)));
            rpm.setActionIndex(cursor.getInt(cursor.getColumnIndexOrThrow(CoursePlayModel.CoursePlayColumn.ACTIONINDEX)));
            rpm.setImageUrl(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayModel.CoursePlayColumn.IMAGEURL)));
            rpm.setBeforCals(cursor.getInt(cursor.getColumnIndexOrThrow(CoursePlayModel.CoursePlayColumn.BEFORCALS)));
            rpm.setCoureShre(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayModel.CoursePlayColumn.COURESHRE)));
            rpm.setCompleted(cursor.getInt(cursor.getColumnIndexOrThrow(CoursePlayModel.CoursePlayColumn.COMPLETED)));
        }
        sqlite.close();
        return rpm;
    }
    /**================================================视频课程记录操作===================================================*/


    /**
     * ================================================视频课程记录上传操作===================================================
     */
    public synchronized void insertPlayRecord(CoursePlayUpload rpcm) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.execSQL(CoursePlayUpload.CoursePlayUploadColumn.INSERT_SQL,
                new Object[]{rpcm.getUid(), rpcm.getCourseCode(), rpcm.getUnitCode(), rpcm.getUnitIndex(), rpcm.getActionCode()
                        , rpcm.getActionId(), rpcm.getActionIndex(), rpcm.getPluse(), rpcm.getKcal(), rpcm.getNumber(), rpcm.getPercent(), rpcm.getDate()});
        sqlite.close();
    }


    public synchronized void deletePlayRecord(String uid, String courseCode) { // courseCode / uid

        if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(courseCode)) {
            return;
        }

        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.beginTransaction();
        sqlite.execSQL(CoursePlayUpload.CoursePlayUploadColumn.DELETE_SQL, new String[]{uid, courseCode});
        sqlite.setTransactionSuccessful();
        sqlite.endTransaction();
        sqlite.close();
    }

    public synchronized void updatePlayRecord() {
        //暂时不需要更新功能，如有需求，后期扩展
    }

    public synchronized ArrayList<CoursePlayUpload> D(String uid, String courseCode) {

        if (TextUtils.isEmpty(uid) || TextUtils.isEmpty(courseCode)) {
            return null;
        }

        ArrayList<CoursePlayUpload> records = new ArrayList<>();
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        Cursor cursor = sqlite.rawQuery(CoursePlayUpload.CoursePlayUploadColumn.QUERY_SQL, new String[]{uid, courseCode});
        while (cursor.moveToNext()) {
            CoursePlayUpload rpm = new CoursePlayUpload();
            rpm.setUid(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayUpload.CoursePlayUploadColumn.UID)));
            rpm.setCourseCode(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayUpload.CoursePlayUploadColumn.COURSE_CODE)));
            rpm.setUnitCode(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayUpload.CoursePlayUploadColumn.UNIT_CODE)));
            rpm.setUnitIndex(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayUpload.CoursePlayUploadColumn.UNIT_INDEX)));
            rpm.setActionCode(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayUpload.CoursePlayUploadColumn.ACTION_CODE)));
            rpm.setActionId(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayUpload.CoursePlayUploadColumn.ACTION_ID)));
            rpm.setActionIndex(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayUpload.CoursePlayUploadColumn.ACTION_INDEX)));
            rpm.setPluse(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayUpload.CoursePlayUploadColumn.PLUSE)));
            rpm.setKcal(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayUpload.CoursePlayUploadColumn.KCAL)));
            rpm.setNumber(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayUpload.CoursePlayUploadColumn.NUMBER)));
            rpm.setPercent(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayUpload.CoursePlayUploadColumn.PERCENT)));
            rpm.setDate(cursor.getString(cursor.getColumnIndexOrThrow(CoursePlayUpload.CoursePlayUploadColumn.DATE)));
            records.add(rpm);
        }
        sqlite.close();
        return records;
    }
    /**================================================视频课程记录上传操作===================================================*/


    /**
     * ================================================同步腕表历史数据操作===================================================
     */
    public synchronized void insertEquipRecord(EquipSyncDataModel esdm) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.execSQL(EquipSyncDataModel.EquipSyncDataColumn.INSERT_SQL,
                new Object[]{esdm.getUserId(), esdm.getDate(), esdm.getSteps(), esdm.getKcal(), esdm.getDistance()});
        sqlite.close();
    }


    public synchronized void deleteEquipRecord(String userid) {
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        sqlite.beginTransaction();
        sqlite.execSQL(EquipSyncDataModel.EquipSyncDataColumn.DELETE_SQL,
                new Object[]{userid});
        sqlite.setTransactionSuccessful();
        sqlite.endTransaction();
        sqlite.close();
    }

    public synchronized ArrayList<EquipSyncDataModel> queryAllSyncData(String uid) {
        ArrayList<EquipSyncDataModel> arrayList = new ArrayList<>();
        SQLiteDatabase sqlite = dbHelper.getWritableDatabase();
        Cursor cursor = sqlite.rawQuery(EquipSyncDataModel.EquipSyncDataColumn.QUERY_SQL, new String[]{uid});
        while (cursor.moveToNext()) {
            EquipSyncDataModel esdm = new EquipSyncDataModel();
//            esdm.setUserId(cursor.getString(cursor.getColumnIndexOrThrow(EquipSyncDataModel.EquipSyncDataColumn.UID)));
            esdm.setDistance(cursor.getString(cursor.getColumnIndexOrThrow(EquipSyncDataModel.EquipSyncDataColumn.DISTANCE)));
            esdm.setDate(cursor.getString(cursor.getColumnIndexOrThrow(EquipSyncDataModel.EquipSyncDataColumn.TEST_TIME)));
            esdm.setSteps(cursor.getString(cursor.getColumnIndexOrThrow(EquipSyncDataModel.EquipSyncDataColumn.STEPS)));
            esdm.setKcal(cursor.getString(cursor.getColumnIndexOrThrow(EquipSyncDataModel.EquipSyncDataColumn.KCAL)));
            arrayList.add(esdm);
        }
        return arrayList;
    }


    /**
     * ================================================同步腕表历史数据操作===================================================
     */


    public synchronized void close() {
        if (dbHelper != null) {
            dbHelper.close();
            dbHelper = null;
        }
    }


}
