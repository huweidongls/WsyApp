package com.jiufang.wsyapp.ysmediaplay;

import android.os.Environment;

public class DemoConfig {

    /**
     * 是否打开调试页面
     */
    public static final boolean isNeedJumpToTestPage = false;

    /**
     * 文件保存位置
     */
    private static final String DEMO_FOLDER = Environment.getExternalStorageDirectory().getPath() + "/Wsy";

    public static String getDemoFolder(){
        return DEMO_FOLDER;
    }

    public static String getRecordsFolder(){
        return DEMO_FOLDER + "/Records";
    }

    public static String getCapturesFolder(){
        return DEMO_FOLDER + "/Captures";
    }

}
