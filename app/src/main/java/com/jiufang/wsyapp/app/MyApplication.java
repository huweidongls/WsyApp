package com.jiufang.wsyapp.app;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import com.alibaba.sdk.android.push.CloudPushService;
import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.ForgetTimeCount;
import com.jiufang.wsyapp.utils.RegisterTimeCount;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.SystemUtil;
import com.jiufang.wsyapp.utils.VersionUtils;
import com.lechange.opensdk.api.LCOpenSDK_Api;
import com.lechange.opensdk.utils.LogUtils;
import com.videogo.openapi.EZGlobalSDK;
import com.videogo.openapi.EZOpenSDK;
import com.videogo.openapi.EzvizAPI;
import com.vise.xsnow.http.ViseHttp;
import com.xuexiang.xupdate.XUpdate;

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
    public static String deviceId = "";

    public MyApplication() {
    }

    public static EZOpenSDK getOpenSDK() {
        if (EzvizAPI.getInstance().isUsingGlobalSDK()){
            return EZGlobalSDK.getInstance();
        }else{
            return EZOpenSDK.getInstance();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        XUpdate.get().init(this);
        createNotificationChannel();
        initCloudChannel(this);
        Map<String, String> map = new LinkedHashMap<>();
//        map.put("Content-Type", "application/x-www-form-urlencoded");
        map.put("token", SpUtils.getToken(getApplicationContext()));
        map.put("app-version", VersionUtils.packageName(getApplicationContext()));
        map.put("device-type", "1");
        map.put("device-unique-id", "123");
        map.put("device-name", "");
        map.put("os-name", SystemUtil.getDeviceBrand());//品牌名称
        map.put("os-version", SystemUtil.getSystemModel());//设备型号
        ViseHttp.init(this);
        ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
        .globalHeaders(map);
        registerTimeCount = new RegisterTimeCount(120000, 1000);
        forgetTimeCount = new ForgetTimeCount(120000, 1000);
//        RichText.initCacheDir(this);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        /** * sdk日志开关，正式发布需要去掉 */
        EZOpenSDK.showSDKLog(true);
        /** * 设置是否支持P2P取流,详见api */
        EZOpenSDK.enableP2P(false);

        /** * APP_KEY请替换成自己申请的 */
        EZOpenSDK.initLib(this, NetUrl.YS_APP_KEY);

        LCOpenSDK_Api.setHost("openapi.lechange.cn:443");
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

    /**
     * 初始化云推送通道
     * @param applicationContext
     */
    private void initCloudChannel(Context applicationContext) {
        PushServiceFactory.init(applicationContext);
        CloudPushService pushService = PushServiceFactory.getCloudPushService();
//        pushService.setNotificationSmallIcon(R.mipmap.ic_launcher);
        pushService.register(applicationContext, new CommonCallback() {
            @Override
            public void onSuccess(String response) {
                deviceId = pushService.getDeviceId();
                Log.d("cloudchannel", "init cloudchannel success -- "+deviceId);
                Map<String, String> map1 = new LinkedHashMap<>();
                map1.put("token", SpUtils.getToken(getApplicationContext()));
                map1.put("app-version", VersionUtils.packageName(getApplicationContext()));
                map1.put("device-type", "1");
                map1.put("device-unique-id", deviceId);
                map1.put("device-name", "");
                map1.put("os-name", SystemUtil.getDeviceBrand());//品牌名称
                map1.put("os-version", SystemUtil.getSystemModel());//设备型号
                ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
                        .globalHeaders(map1);
            }
            @Override
            public void onFailed(String errorCode, String errorMessage) {
                Log.d("cloudchannel", "init cloudchannel failed -- errorcode:" + errorCode + " -- errorMessage:" + errorMessage);
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            // 通知渠道的id
            String id = "1";
            // 用户可以看到的通知渠道的名字.
            CharSequence name = "notification channel";
            // 用户可以看到的通知渠道的描述
            String description = "notification description";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(id, name, importance);
            // 配置通知渠道的属性
            mChannel.setDescription(description);
            // 设置通知出现时的闪灯（如果 android 设备支持的话）
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            // 设置通知出现时的震动（如果 android 设备支持的话）
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            //最后在notificationmanager中创建该通知渠道
            mNotificationManager.createNotificationChannel(mChannel);
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
