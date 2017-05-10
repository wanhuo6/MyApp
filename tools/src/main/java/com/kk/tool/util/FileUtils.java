package com.kk.tool.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

/**
 * Created by Administrator on 2016-08-06.
 */
public class FileUtils {

    /**
     * 生成时间戳文件名
     *
     * @return
     */
    public static String getRandomName() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        StringBuilder builder = new StringBuilder();
        builder.append(cal.get(Calendar.YEAR));
        builder.append(cal.get(Calendar.MONTH) + 1);
        builder.append(cal.get(Calendar.DAY_OF_MONTH));
        builder.append(cal.get(Calendar.HOUR_OF_DAY));
        builder.append(cal.get(Calendar.MINUTE));
        builder.append(cal.get(Calendar.SECOND));
        builder.append(cal.get(Calendar.MILLISECOND));
        return builder.toString();
    }

    /**
     * 获取剩余空间
     *
     * @param filePath
     * @return
     */
    public static long getFreeBytes(String filePath) {
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    public static String initTempPath(int requestSize) {
        String sdState = Environment.getExternalStorageState();
        if (!sdState.equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        long freeBytes = FileUtils.getFreeBytes(path);
        long myRequestSize = requestSize > 0 ? requestSize : 4 * 1024 * 1024L;
        if (freeBytes < myRequestSize) {
            return null;
        }
        String tempPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "kktemp" + File.separator;
        File f = new File(tempPath);
        if (!f.exists()) {
            f.mkdir();
        } else if (!f.isDirectory()) {
            f.delete();
            f.mkdir();
        }
        return tempPath;
    }

    /**
     * 根据视频的保存路径创建缩略图
     *
     * @param path
     * @return
     */
    public static String createVideoThumb(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Images.Thumbnails.MINI_KIND);
        int end = path.lastIndexOf(".");
        String thumbPath = path.substring(0, end) + "_thumb.jpg";
        File fThumb = new File(thumbPath);
        try {
            fThumb.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fThumb);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fos);
        try {
            if (fos != null) {
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (fos != null) {
                fos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return thumbPath;
    }


    /**
     * 获取音频文件的长度
     *
     * @param context
     * @param audioPath
     * @return
     */
    public static int getAudioDuring(Context context, String audioPath) {
        MediaPlayer mp = MediaPlayer.create(context, Uri.parse(audioPath));
        int duration = mp.getDuration() / 1000;
        mp.release();
        return duration;
    }

    /**
     * 读取资源文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String readAssets(Context context, String fileName) {
        InputStream is = null;
        String content = null;
        try {
            is = context.getAssets().open(fileName);
            if (is != null) {

                byte[] buffer = new byte[1024];
                ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
                while (true) {
                    int readLength = is.read(buffer);
                    if (readLength == -1) break;
                    arrayOutputStream.write(buffer, 0, readLength);
                }
                is.close();
                arrayOutputStream.close();
                content = new String(arrayOutputStream.toByteArray());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            content = null;
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return content;
    }
}
