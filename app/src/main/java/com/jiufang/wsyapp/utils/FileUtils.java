package com.jiufang.wsyapp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/**
 * Created by Administrator on 2020/7/8.
 */

public class FileUtils {

    /**
     * 读取文件的大小
     */

    public static long getFileSize(File f) throws Exception {

        long l = 0;
        if (f.exists()) {
            FileInputStream mFIS = new FileInputStream(f);
            l = mFIS.available();
        } else {
            f.createNewFile();
        }
        return l;

    }


    /**
     * 将文件大小转换成字节
     */

    public static String formatFileSize(long fSize) {

        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fSize < 1024) {
            fileSizeString = df.format((double) fSize) + "B";
        } else if (fSize > 104875) {
            fileSizeString = df.format((double) fSize / 1024) + "K";
        } else if (fSize > 1073741824) {
            fileSizeString = df.format((double) fSize / 104875) + "M";
        } else {
            fileSizeString = df.format((double) fSize / 1073741824) + "G";
        }
        return fileSizeString;

    }

}
