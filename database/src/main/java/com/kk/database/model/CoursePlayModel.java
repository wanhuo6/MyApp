package com.kk.database.model;

/**
 * Created by Ma Zhihua on 2016/8/8.
 * Description : 课程播放页面实体
 */
public class CoursePlayModel {

//    beforCals 卡路里 int
//    ImageUrl   图片  String
//    AllSize    视频长度  int
//    second     视频播放时间 long
//    cals      当前卡路里    int
//    completed  完成度      int
//    actionIndex 当前进度   int
//    coureShre     待定
//    musicPosition 当前音频  int
//    name           主key courseCode + uid String

    private String ImageUrl,name ,coureShre;
    private int second;
    private int beforCals,AllSize,cals,completed,actionIndex,heartbeats;

    private String musicPosition;

    public int getHeartbeats() {
        return heartbeats;
    }

    public void setHeartbeats(int heartbeats) {
        this.heartbeats = heartbeats;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoureShre() {
        return coureShre;
    }

    public void setCoureShre(String coureShre) {
        this.coureShre = coureShre;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getBeforCals() {
        return beforCals;
    }

    public void setBeforCals(int beforCals) {
        this.beforCals = beforCals;
    }

    public int getAllSize() {
        return AllSize;
    }

    public void setAllSize(int allSize) {
        AllSize = allSize;
    }

    public int getCals() {
        return cals;
    }

    public void setCals(int cals) {
        this.cals = cals;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getActionIndex() {
        return actionIndex;
    }

    public void setActionIndex(int actionIndex) {
        this.actionIndex = actionIndex;
    }

    public String getMusicPosition() {
        return musicPosition;
    }

    public void setMusicPosition(String musicPosition) {
        this.musicPosition = musicPosition;
    }

    public static final class CoursePlayColumn{


        //    beforCals 卡路里 int
//    ImageUrl   图片  String
//    AllSize    视频长度  int
//    second     视频播放时间 long
//    cals      当前卡路里    int
//    completed  完成度      int
//    actionIndex 当前进度   int
//    coureShre     待定
//    musicPosition 当前音频  int
//    name           主key courseCode + uid String
        //heartbeats 心率

        public static final String ID = "_id";
        public static final String BEFORCALS = "beforCals";
        public static final String IMAGEURL = "ImageUrl";
        public static final String COURESHRE = "coureShre";
        public static final String ALLSIZE = "AllSize";
        public static final String SECOND = "second";
        public static final String CALS = "cals";
        public static final String COMPLETED = "completed";
        public static final String ACTIONINDEX = "actionIndex";
        public static final String MUSICPOSITION = "musicPosition";
        public static final String NAME = "name";
        public static final String HEARTBEATS = "heartbeats";

        public static final String TABLE_NAME = "courseplay";

        public static final String CREATE_SQL = "create table " + TABLE_NAME
                + " (" + CoursePlayColumn.ID + " integer primary key autoincrement," + CoursePlayColumn.HEARTBEATS + " INTEGER,"
                + CoursePlayColumn.MUSICPOSITION + " varchar," + CoursePlayColumn.CALS + " INTEGER," + CoursePlayColumn.ALLSIZE + " INTEGER,"
                + CoursePlayColumn.SECOND + " INTEGER," + CoursePlayColumn.NAME + " varchar(100),"
                + CoursePlayColumn.ACTIONINDEX + " INTEGER," + CoursePlayColumn.IMAGEURL + " varchar(300),"
                + CoursePlayColumn.BEFORCALS + " INTEGER," + CoursePlayColumn.COURESHRE + " varchar,"
                + CoursePlayColumn.COMPLETED +" INTEGER " + ")";

        public static final String DROP_SQL = "drop table if exists " + TABLE_NAME;

        public static final String INSERT_SQL = "insert into " + TABLE_NAME
                + " (" + CoursePlayColumn.HEARTBEATS + "," + CoursePlayColumn.NAME + "," + CoursePlayColumn.MUSICPOSITION
                + "," + CoursePlayColumn.ACTIONINDEX + "," + CoursePlayColumn.COMPLETED + "," + CoursePlayColumn.CALS + ","
                + CoursePlayColumn.SECOND + "," + CoursePlayColumn.ALLSIZE + "," + CoursePlayColumn.COURESHRE + ","
                + CoursePlayColumn.IMAGEURL + "," + CoursePlayColumn.BEFORCALS +
                ") values (?,?,?,?,?,?,?,?,?,?,?)";

        public static final String DELETE_SQL = "delete from " + TABLE_NAME + " where name = ? ";
//
        public static final String QUERY_URL_SQL = "select * from " + TABLE_NAME
                + " where " + CoursePlayColumn.NAME + "=?";

        public static final String UP_DATE = "update " + TABLE_NAME + " set " +CoursePlayColumn.ACTIONINDEX + "=? , "+CoursePlayColumn.MUSICPOSITION +"=? , "+CoursePlayColumn.SECOND+"=? , "+CoursePlayColumn.BEFORCALS+"=? , "+CoursePlayColumn.CALS+"=? where " + CoursePlayColumn.NAME  + "=?";

    }

}
