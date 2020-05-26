/* 
 * @ProjectName VideoGo
 * @Copyright null
 * 
 * @FileName UIUtils.java
 * @Description 这里对文件进行描述
 * 
 * @author chenxingyf1
 * @data 2015-4-23
 * 
 * @note 这里写本文件的详细功能描述和注释
 * @note 历史记录
 * 
 * @warning 这里写本文件的相关警告
 */
package com.jiufang.wsyapp.ysmediaplay.util;

import android.app.Activity;
import android.content.Intent;

import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.ui.MainActivity;

public class ActivityUtils {
    public static void handleSessionException(Activity activity) {
        goToLoginAgain(activity);
    }

    public static void goToLoginAgain(Activity activity) {
        MyApplication.getOpenSDK().openLoginPage();
    }

    public static void goToMainPage(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
    }

}