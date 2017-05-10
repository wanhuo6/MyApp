package com.kk.database.model;

/**
 * Created by Ma Zhihua on 2016/8/17.
 * Description : 视频课程详细数据保存实体
 */
public class CoursePlayUpload {

    private String uid, courseCode, unitCode, unitIndex, actionCode, actionId, actionIndex, pluse, kcal, percent, date, number;


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUid() {

        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitIndex() {
        return unitIndex;
    }

    public void setUnitIndex(String unitIndex) {
        this.unitIndex = unitIndex;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getActionIndex() {
        return actionIndex;
    }

    public void setActionIndex(String actionIndex) {
        this.actionIndex = actionIndex;
    }

    public String getPluse() {
        return pluse;
    }

    public void setPluse(String pluse) {
        this.pluse = pluse;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public static final class CoursePlayUploadColumn {

        public static final String ID = "_id";
        public static final String UID = "uid";
        public static final String COURSE_CODE = "course_code";
        public static final String UNIT_CODE = "unit_code";
        public static final String UNIT_INDEX = "unit_index";
        public static final String ACTION_CODE = "action_code";
        public static final String ACTION_ID = "action_id";
        public static final String ACTION_INDEX = "action_index";
        public static final String PLUSE = "pluse";
        public static final String KCAL = "kcal";
        public static final String NUMBER = "number";
        public static final String PERCENT = "percent";
        public static final String DATE = "date";

        public static final String TABLE_NAME = "courseplayupload";


        public static final String CREATE_SQL = "create table " + TABLE_NAME
                + " (" + CoursePlayUploadColumn.ID + " integer primary key autoincrement," + CoursePlayUploadColumn.UID + " varchar(100),"
                + CoursePlayUploadColumn.COURSE_CODE + " varchar(100)," + CoursePlayUploadColumn.UNIT_CODE + " varchar(100)," + CoursePlayUploadColumn.UNIT_INDEX + " varchar(100),"
                + CoursePlayUploadColumn.ACTION_CODE + " varchar(100)," + CoursePlayUploadColumn.ACTION_ID + " varchar(100),"
                + CoursePlayUploadColumn.ACTION_INDEX + " varchar(100)," + CoursePlayUploadColumn.PLUSE + " varchar(50),"
                + CoursePlayUploadColumn.KCAL + " varchar(100)," + CoursePlayUploadColumn.NUMBER + " varchar,"
                + CoursePlayUploadColumn.PERCENT + " varchar(100), " + CoursePlayUploadColumn.DATE + " varchar(50) " + ")";

        public static final String DROP_SQL = "drop table if exists " + TABLE_NAME;

        public static final String INSERT_SQL = "insert into " + TABLE_NAME
                + " (" + CoursePlayUploadColumn.UID + "," + CoursePlayUploadColumn.COURSE_CODE + "," + CoursePlayUploadColumn.UNIT_CODE
                + "," + CoursePlayUploadColumn.UNIT_INDEX + "," + CoursePlayUploadColumn.ACTION_CODE + "," + CoursePlayUploadColumn.ACTION_ID + ","
                + CoursePlayUploadColumn.ACTION_INDEX + "," + CoursePlayUploadColumn.PLUSE + "," + CoursePlayUploadColumn.KCAL + ","
                + CoursePlayUploadColumn.NUMBER + "," + CoursePlayUploadColumn.PERCENT + "," + CoursePlayUploadColumn.DATE +
                ") values (?,?,?,?,?,?,?,?,?,?,?,?)";

        public static final String DELETE_SQL = "delete from " + TABLE_NAME + " where " + CoursePlayUploadColumn.UID + "=?" + " and " + CoursePlayUploadColumn.COURSE_CODE + "=? ";
        //
        public static final String QUERY_SQL = "select * from " + TABLE_NAME
                + " where " + CoursePlayUploadColumn.UID + "=?" + " and " + CoursePlayUploadColumn.COURSE_CODE + "=? " + "order by " + CoursePlayUploadColumn.DATE + " asc";

    }


}
