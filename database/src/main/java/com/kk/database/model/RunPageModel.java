package com.kk.database.model;

/**
 * Created by Ma Zhihua on 2016/8/8.
 * Description : 跑步页面中需要插入数据库的属性类
 */
public class RunPageModel {


    private int heartBeats ; //心率
    private int steps ; //步数
    private int kcal ;//卡路里
    private String sessionId; // 与总表关联的外键id
    private String lng ;//经度
    private String lat ;//纬度
    private String time ; //当前打点的时间
    private int accuracy ; //打点时GPS的精度
    private String speed ; // 打点时的速度
    private String isPause ; // 标识是否为暂停状态下打的点 ： 0:正常 1：暂停

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getHeartBeats() {
        return heartBeats;
    }

    public void setHeartBeats(int heartBeats) {
        this.heartBeats = heartBeats;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getIsPause() {
        return isPause;
    }

    public void setIsPause(String isPause) {
        this.isPause = isPause;
    }

    public static final class RunPageColumn{
        public static final String ID = "_id";
        public static final String HEARTBEATS = "heartBeats";
        public static final String STEPS = "steps";
        public static final String KCAL = "kcal";
        public static final String LNG = "lng";
        public static final String LAT = "lat";
        public static final String TIME = "time";
        public static final String ACCURACY = "accuracy";
        public static final String SPEED = "speed";
        public static final String ISPAUSE = "isPause";
        public static final String SESSIONID = "sessionId";

        public static final String TABLE_NAME = "runtable";

        public static final String CREATE_SQL = "create table " + TABLE_NAME
                + " (" + RunPageColumn.ID + " integer primary key autoincrement," + RunPageColumn.HEARTBEATS + " INTEGER,"
                + RunPageColumn.STEPS + " INTEGER," + RunPageColumn.KCAL + " INTEGER," + RunPageColumn.LNG + " varchar(10),"
                + RunPageColumn.LAT + " varchar(10)," + RunPageColumn.TIME + " varchar(30),"
                + RunPageColumn.ACCURACY + " INTEGER," + RunPageColumn.SPEED + " varchar(10),"
                + RunPageColumn.ISPAUSE + " varchar(5)," + RunPageColumn.SESSIONID +" varchar " + ")";

        public static final String DROP_SQL = "drop table if exists " + TABLE_NAME;

        public static final String INSERT_SQL = "insert into " + TABLE_NAME
                + " (" + RunPageColumn.HEARTBEATS + "," + RunPageColumn.STEPS + "," + RunPageColumn.KCAL
                + "," + RunPageColumn.LNG + "," + RunPageColumn.LAT + "," + RunPageColumn.TIME + ","
                + RunPageColumn.ACCURACY + "," + RunPageColumn.SPEED + "," + RunPageColumn.ISPAUSE + ","
                + RunPageColumn.SESSIONID +
                ") values (?,?,?,?,?,?,?,?,?,?)";

        public static final String DELETE_SQL = "delete from " + TABLE_NAME + " where sessionId = ? ";

        public static final String QUERY_URL_SQL = "select * from " + TABLE_NAME
                + " where " + RunPageColumn.SESSIONID + "=?";


        public static final String QUERY_HB_WITH_SESSIONID = "select heartBeats from " + TABLE_NAME + " where " + RunPageColumn.SESSIONID + "=?";

        public static final String QUERY_LOCATION_WITH_SESSIONID = "select * from " + TABLE_NAME + " where " + RunPageColumn.SESSIONID + "=?";

    }

}
