package com.kk.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kk.database.model.CoursePlayModel;
import com.kk.database.model.CoursePlayUpload;
import com.kk.database.model.EquipSyncDataModel;
import com.kk.database.model.RunPageCollectModel;
import com.kk.database.model.RunPageModel;

/**
 * 数据缓存（如：查询历史数据）
 *
 * @author houlianyong
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "KKCoachPad.db";
    //数据库升级，放弃老数据。
    public static final int DB_VERSION = 5;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DownLoadDatabase.DownLoad.CREATE_SQL);
        db.execSQL(RunPageModel.RunPageColumn.CREATE_SQL);
        db.execSQL(RunPageCollectModel.RunPageCollect.CREATE_SQL);
        db.execSQL(CoursePlayModel.CoursePlayColumn.CREATE_SQL);
        db.execSQL(CoursePlayUpload.CoursePlayUploadColumn.CREATE_SQL);
        db.execSQL(EquipSyncDataModel.EquipSyncDataColumn.CREATE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DownLoadDatabase.DownLoad.DROP_SQL);
        db.execSQL(RunPageModel.RunPageColumn.DROP_SQL);
        db.execSQL(RunPageCollectModel.RunPageCollect.DROP_SQL);
        db.execSQL(CoursePlayModel.CoursePlayColumn.DROP_SQL);
        db.execSQL(CoursePlayUpload.CoursePlayUploadColumn.DROP_SQL);
        db.execSQL(EquipSyncDataModel.EquipSyncDataColumn.DROP_SQL);
        onCreate(db);
    }

}