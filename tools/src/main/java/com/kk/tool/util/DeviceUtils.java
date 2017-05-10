package com.kk.tool.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.WindowManager;

import java.io.File;
import java.io.FileFilter;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by guoning on 16/1/2.
 */
public class DeviceUtils {

    /**
     * User-Agent
     *
     * @return user-agent
     */
    public static String getUser_Agent(Context context) {

        StringBuffer sb = new StringBuffer();
        sb.append("KKUSER-")
                .append(PackageUtils.getVersionName(context))
                .append("-")
                .append(PackageUtils.getVersionCode(context))
                .append("/Android/")
                .append(getOSVersion())
                .append("/")
                .append(getVendor())
                .append("/")
                .append(getDevice());
//                .append("/")
//                .append(Build.MANUFACTURER);

        return sb.toString();
    }
    /**
     * 获取手机型号
     *
     * @return the user_Agent
     */
    public static String getDevice() {
        return Build.MODEL;
    }


    /**
     *  获取手机品牌
     *
     * @return the vENDOR
     */
    public static String getVendor() {
        return Build.BRAND;
    }


    /**
     * 获取Android SDK版本
     * @return the SDK version
     */
    public static int getSDKVersion() {
        return Build.VERSION.SDK_INT;
    }


    /**
     * 获取系统版本
     * @return the OS version
     */
    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    public static String getImei(Context context) {
        String imeiRes = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            imeiRes = telephonyManager.getDeviceId();
            if (imeiRes == null || imeiRes.trim().length() == 0 || imeiRes.matches("0+")) {
                imeiRes = (new StringBuilder("EMU")).append((new Random(System.currentTimeMillis())).nextLong())
                        .toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imeiRes;
    }

    /**
     * 获取CPU核心数
     *
     * @return
     */
    public static int getCPUCores() {
        class CpuFilter implements FileFilter {
            @Override
            public boolean accept(File pathname) {
                // Check if filename is "cpu", followed by a single digit number
                if (Pattern.matches("cpu[0-9]", pathname.getName())) {
                    return true;
                }
                return false;
            }
        }

        try {
            // Get directory containing CPU info
            File dir = new File("/sys/devices/system/cpu/");
            // Filter to only list the devices we care about
            File[] files = dir.listFiles(new CpuFilter());
            // Return the number of cores (virtual CPU devices)
            return files.length;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 获取屏幕的宽高
     *
     * @param ctx
     * @return
     */
    public static int[] getScreenWH(Context ctx) {
        WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
        Point point = new Point();
        wm.getDefaultDisplay().getSize(point);
        return new int[]{point.x, point.y};
    }

    public static int getStatusBarHeight(Context c) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int resourceId = c.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = c.getResources().getDimensionPixelSize(resourceId);
            }
        }
        return result;
    }

}
