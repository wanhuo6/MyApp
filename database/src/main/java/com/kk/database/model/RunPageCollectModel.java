package com.kk.database.model;

/**
 * Created by Ma Zhihua on 2016/8/8.
 * Description : 跑步页面中需要插入数据库的属性类
 */
public class RunPageCollectModel {



    //status 0 : 上传失败 1： 上传成功
    private String startime,endTime,status,duration,distance,ps;
    private int steps ; //步数
    private int kcal ;//卡路里
    private String sessionId; // 与总表关联的外键id
    private String mapPath; // 地图截图保存路径

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public String getMapPath() {
        return mapPath;
    }

    public void setMapPath(String mapPath) {
        this.mapPath = mapPath;
    }

    public String getStartime() {
        return startime;
    }

    public void setStartime(String startime) {
        this.startime = startime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public static final class RunPageCollect{

        public static final String ID = "_id";
        public static final String STARTIME = "startime";
        public static final String ENDTIME = "endTime";
        public static final String STATUS = "status";
        public static final String DURATION = "duration";
        public static final String DISTANCE = "distance";
        public static final String STEPS = "steps";
        public static final String KCAL = "kcla";
        public static final String MAP_PATH = "map_path";
        public static final String SESSIONID = "sessionId";
        public static final String PS = "ps"; // 配速

        public static final String TABLE_NAME = "runpagecollect";

        public static final String CREATE_SQL = "create table " + TABLE_NAME
                + " (" + RunPageCollect.ID + " integer primary key autoincrement,"
                + RunPageCollect.STEPS + " INTEGER," + RunPageCollect.KCAL + " INTEGER," + RunPageCollect.DISTANCE + " varchar(10),"
                + RunPageCollect.DURATION + " varchar(30)," + RunPageCollect.STATUS + " varchar(30)," + RunPageCollect.MAP_PATH + " varchar(200),"
                + RunPageCollect.ENDTIME + " varchar(30)," + RunPageCollect.STARTIME + " varchar(30)," + RunPageCollect.SESSIONID + " varchar(30), "
                + RunPageCollect.PS + " varchar(30)" + ")";

        public static final String DROP_SQL = "drop table if exists " + TABLE_NAME;

        public static final String INSERT_SQL = "insert into " + TABLE_NAME
                + " (" + RunPageCollect.STARTIME + "," + RunPageCollect.ENDTIME + "," + RunPageCollect.STATUS
                + "," + RunPageCollect.DURATION + "," + RunPageCollect.DISTANCE + "," + RunPageCollect.STEPS + ","
                + RunPageCollect.SESSIONID + "," + RunPageCollect.KCAL + "," + RunPageCollect.MAP_PATH +"," + RunPageCollect.PS+ ") values (?,?,?,?,?,?,?,?,?,?)";

        public static final String DELETE_SQL = "delete from " + TABLE_NAME + " where sessionId = ? ";


//        public static final String UPDATE_SQL = "update " + TABLE_NAME +
//                " set " + RunPageCollect.PS + "=?  where " + RunPageCollect.SESSIONID + "=?";

        public static final String QUERY_URL_SQL = "select * from " + TABLE_NAME ;

    }

}
