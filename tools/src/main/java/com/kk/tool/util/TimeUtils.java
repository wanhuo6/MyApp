package com.kk.tool.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * <font size="3" color="green"><b>.</b></font><p>
 * <p>
 * <font size="2" color="green"><b>返回：.</b></font><p>
 * <p>
 * <font size="1">Created on 2016-07-27.</font><p>
 * <p>
 * <font size="1">@author LuoShuiquan.</font>
 */
public class TimeUtils {
    public final static SimpleDateFormat SDF_NO_CONNECT_DATE = new SimpleDateFormat("yyyyMMdd");
    public final static SimpleDateFormat SDF_HAS_CONNECT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    public final static SimpleDateFormat SDF_SERVER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String CHECK_TIME_FORMAT = "yyyyMMddHHmmss";


    /**
     * 是否是今天
     * @param selectday
     * @return
     */

    public static boolean isToday(String selectday) {

        Date date = new Date();
        String curent = SDF_NO_CONNECT_DATE.format(date);

        return curent.equals(selectday);

    }
    /**
     * 是否大于今天
     *
     * @param other_day 格式yyyyMMdd
     * @return
     */
    public static boolean isAfterToday(String other_day) {
        return isAfterThisDay(new SimpleDateFormat("yyyyMMdd").format(new Date()), other_day);
    }

    /**
     * 是否大于某天
     *
     * @param this_day  参考日期
     * @param other_day 对比日期
     * @return
     */
    public static boolean isAfterThisDay(String this_day, String other_day) {
        return Integer.parseInt(this_day) < Integer.parseInt(other_day.replaceAll("-", ""));
    }

    /**
     * 毫秒转时间
     *
     * @param time
     * @return  "12:12"
     */
    public static String formatMillisecond(double time) {
        if (time >= 0) {
            int total_s = (int) (time / 1000);
            int s = total_s / 60;
            int m = total_s % 60;
            return appendTime(s) + ":" + appendTime(m);
        } else {
            return "00:00";
        }
    }

    /**
     * 秒转时间
     *
     * @param time
     * @return  "12:12:12"
     */
    public static String formatSecond(double time) {
        if (time >= 0) {
            int h = (int) (time / 3600);
            int m = (int) ((time - h * 3600) / 60);
            int s = ((int) time) % 60;
            return appendTime(h) + ":" + appendTime(m) + ":" + appendTime(s);
        } else {
            return "00:00:00";
        }
    }

    /**
     * 秒转时间
     *
     * @param time
     * @return 0'00"
     */
    public static String formatTime(double time) {
        if (time >= 0) {
            int m = (int) (time / 60);
            int s = ((int) time) % 60;
            return m + "'" + appendTime(s) + "\"";
        } else {
            return "0'00" + "\"";
        }
    }

    public static String appendTime(int s) {
        return s < 10 ? ("0" + s) : String.valueOf(s);
    }



    public static String getDateString(long milliseconds, String format) {
        Date date = new Date(milliseconds);
        return getDateString(date, format);
    }
    public static String getDateString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        return sdf.format(date);
    }


    /**
     * 计算给定日期的周一
     *
     * @param date
     * @return
     */
    public static String getMondayOfWeek(String date) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(SDF_HAS_CONNECT_DATE.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //判断要计算的日期是否是周日，如果是则减一天计算周六的
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);//设置一个星期的第一天
        int day = cal.get(Calendar.DAY_OF_WEEK);//获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
//        cal.add(Calendar.DATE, 6);   //如果有其他需要：计算周日是哪天
        return SDF_HAS_CONNECT_DATE.format(cal.getTime());
    }

    /**
     * @param date yyyy-MM-dd
     * @return
     */
    public static String getFirstDayOfMonth(String date) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        int index = date.lastIndexOf("-");
        return date.substring(0, index) + "-01";
    }

    /**
     * 给定日期偏移多少天
     *
     * @param date
     * @param offset
     * @return
     */
    public static String dayOffset(String date, int offset) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(SDF_HAS_CONNECT_DATE.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.DATE, offset);
        return SDF_HAS_CONNECT_DATE.format(cal.getTime());
    }


    public static String monthOffset(String date, int offset) {
        if (TextUtils.isEmpty(date)) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(SDF_HAS_CONNECT_DATE.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.add(Calendar.MONTH, offset);
        return SDF_HAS_CONNECT_DATE.format(cal.getTime());
    }

    public static String getCurrentTime(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        String currentTime = sdf.format(date);
        return currentTime;
    }
    /**
     * 日期字符换转时间戳.
     *
     * @param strTime
     * @return
     */
    public static Long getTime(String strTime) {

        Long time = null;
        Date date;
        try {
            date = SDF_SERVER.parse(strTime);
            time = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }
}
