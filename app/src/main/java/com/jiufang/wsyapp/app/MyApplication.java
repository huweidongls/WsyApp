package com.jiufang.wsyapp.app;

import android.app.Activity;
import android.app.Application;

import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.ForgetTimeCount;
import com.jiufang.wsyapp.utils.RegisterTimeCount;
import com.jiufang.wsyapp.utils.SpUtils;
import com.lechange.opensdk.api.LCOpenSDK_Api;
import com.lechange.opensdk.utils.LogUtils;
import com.vise.xsnow.http.ViseHttp;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/9/29.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private List<Activity> mList = new LinkedList<Activity>();
    // 修改密码获取验证码倒计时
    public static RegisterTimeCount registerTimeCount;
    public static ForgetTimeCount forgetTimeCount;

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Map<String, String> map = new LinkedHashMap<>();
//        map.put("Content-Type", "application/x-www-form-urlencoded");
        map.put("token", SpUtils.getToken(getApplicationContext()));
        map.put("app-version", "1.0.0");
        map.put("device-type", "1");
        map.put("device-unique-id", "123");
        map.put("device-name", "");
        ViseHttp.init(this);
        ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
        .globalHeaders(map);
        registerTimeCount = new RegisterTimeCount(120000, 1000);
        forgetTimeCount = new ForgetTimeCount(120000, 1000);
//        RichText.initCacheDir(this);

        LCOpenSDK_Api.setHost("https://openapi.lechange.cn:443/");
        /*******Android 4.4 等部分版本需要在此处特殊处理，单独加载so库文件 *****/
        try {
            System.loadLibrary("netsdk");
            System.loadLibrary("configsdk");
            System.loadLibrary("jninetsdk");
            System.loadLibrary("gnustl_shared");
            System.loadLibrary("LechangeSDK");
            System.loadLibrary("SmartConfig");
            //日志开关
            LogUtils.OpenLog();
        } catch (Exception var1) {
            System.err.println("loadLibrary Exception"+var1.toString());
        } catch (Error var2) {
            System.err.println("loadLibrary Exception"+var2.toString());
        }

    }

    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

}
