package com.kk.database.model;

/**
 * Created by Ma Zhihua on 2016/8/17.
 * Description : 设备数据同步实体
 */
public class EquipSyncDataModel {

//    int userId = DataUtils.byteArrayToInt(new byte[]{data[1],data[2],data[3],data[4]});
//    int testTime = DataUtils.byteArrayToInt(new byte[]{data[5],data[6],data[7],data[8]}); // 测量时间
//    int steps = DataUtils.byteArrayToInt(new byte[]{data[9],data[10]}); // 步数
//    int kcal = DataUtils.byteArrayToInt(new byte[]{data[11],data[12]}); // 卡路里
//    int distance = DataUtils.byteArrayToInt(new byte[]{data[13],data[14]});// 距离

    private String userId ,date,steps,kcal,distance ;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public static final class EquipSyncDataColumn{
        
        public static final String ID = "_id";
        public static final String UID = "uid" ;
        public static final String TEST_TIME = "test_time";
        public static final String STEPS = "steps";
        public static final String KCAL = "kcal";
        public static final String DISTANCE = "distance";

        public static final String TABLE_NAME = "equipsync";


        public static final String CREATE_SQL = "create table " + TABLE_NAME
                + " (" + EquipSyncDataColumn.ID + " integer primary key autoincrement," + EquipSyncDataColumn.UID + " varchar(100),"
                + EquipSyncDataColumn.TEST_TIME + " varchar(100)," + EquipSyncDataColumn.STEPS + " varchar(100)," + EquipSyncDataColumn.KCAL + " varchar(100),"
                + EquipSyncDataColumn.DISTANCE + " varchar(100) " + " )";

        public static final String DROP_SQL = "drop table if exists " + TABLE_NAME;

        public static final String INSERT_SQL = "insert into " + TABLE_NAME
                + " (" + EquipSyncDataColumn.UID + "," + EquipSyncDataColumn.TEST_TIME + "," + EquipSyncDataColumn.STEPS
                + "," + EquipSyncDataColumn.KCAL + "," + EquipSyncDataColumn.DISTANCE + ") values (?,?,?,?,?)";

        public static final String DELETE_SQL = "delete from " + TABLE_NAME + " where " + EquipSyncDataColumn.UID + "=?";
        //
        public static final String QUERY_SQL = "select * from " + TABLE_NAME
                + " where " + EquipSyncDataColumn.UID + "=?";
    }
    

}
