package com.jiufang.wsyapp.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezviz.sdk.configwifi.EZConfigWifiErrorEnum;
import com.ezviz.sdk.configwifi.EZConfigWifiInfoEnum;
import com.ezviz.sdk.configwifi.EZWiFiConfigManager;
import com.ezviz.sdk.configwifi.ap.ApConfigParam;
import com.ezviz.sdk.configwifi.common.EZConfigWifiCallback;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.dialog.DialogBohao;
import com.jiufang.wsyapp.mediaplay.Business;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.NetUtil;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;
import com.lechange.common.configwifi.LCSmartConfig;
import com.lechange.common.log.Logger;
import com.lechange.opensdk.configwifi.LCOpenSDK_ConfigWifi;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeviceWifiActivity extends BaseActivity {

    private Context context = AddDeviceWifiActivity.this;

    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.tv_ssid)
    TextView tvSsid;
    @BindView(R.id.iv_jizhu)
    ImageView ivJizhu;

    private String tiaozhuanType = "";//1绑定 2设备设置
    private String type = "";//1乐橙 2萤石
    private String xlh = "";
    private String anquan = "";
    private String xinghao = "";

    private boolean isShowPwd = false;
    private boolean isJizhu = true;

    private final int DEVICE_SEARCH_SUCCESS = 0x1B;
    private final int DEVICE_SEARCH_FAILED = 0x1C;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Logger.d("123123", "msg.what  " + msg.what);
            switch (msg.what) {
                case DEVICE_SEARCH_SUCCESS:
                    Logger.d("123123", "deviceSearchFailed:" + msg.obj);
                    ToastUtil.showShort(context, "WiFi配置成功");
                    stopConfig();
                    WeiboDialogUtils.closeDialog(dialog);
                    if(tiaozhuanType.equals("1")){
                        Intent intent = new Intent();
                        intent.setClass(context, AddDeviceSureActivity.class);
                        intent.putExtra("type", type);
                        intent.putExtra("xlh", xlh);
                        intent.putExtra("anquan", anquan);
                        intent.putExtra("xinghao", xinghao);
                        startActivity(intent);
                    }else {
                        AddDeviceWifiActivity.this.finish();
                    }
                    break;
                case DEVICE_SEARCH_FAILED:
                    Logger.d("123123", "deviceSearchFailed:" + msg.obj);
                    ToastUtil.showShort(context, "WiFi配置失败");
                    WeiboDialogUtils.closeDialog(dialog);
                    stopConfig();
                    break;
                default:
                    break;
            }
        }
    };

    private static final int CONFIG_WIFI_TIMEOUT_TIME = 60 * 1000;
    private static final int CONFIG_SEARCH_DEVICE_TIME = 60 * 1000;
    String ssid="";
    private String devType="";
    private Runnable progressRun = new Runnable() {
        @Override
        public void run() {
            com.jiufang.wsyapp.utils.Logger.e("123123", "WiFi配置超时");
            stopConfig();
        }
    };

    private Dialog dialog;

    private WifiInfo mWifiInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_wifi);

        tiaozhuanType = getIntent().getStringExtra("tiaozhuan");
        type = getIntent().getStringExtra("type");
        xlh = getIntent().getStringExtra("xlh");
        anquan = getIntent().getStringExtra("anquan");
        xinghao = getIntent().getStringExtra("xinghao");
        StatusBarUtils.setStatusBar(AddDeviceWifiActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddDeviceWifiActivity.this);
        initData();

    }

    private void initData() {

        WifiManager mWifiManager = (WifiManager) getApplicationContext().getSystemService(Activity.WIFI_SERVICE);
        mWifiInfo = mWifiManager.getConnectionInfo();
        if (mWifiInfo != null) {
            tvSsid.setText(mWifiInfo.getSSID().replaceAll("\"", ""));
            ssid=mWifiInfo.getSSID().replaceAll("\"", "");
            Map<String, String> map = SpUtils.getWifi(context);
            if(map != null){
                String pwd = map.get(ssid);
                etPwd.setText(pwd);
            }else {
                etPwd.setText(null);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 10001){
            WifiManager mWifiManager = (WifiManager) getApplicationContext().getSystemService(Activity.WIFI_SERVICE);
            mWifiInfo = mWifiManager.getConnectionInfo();
            if (mWifiInfo != null) {
                tvSsid.setText(mWifiInfo.getSSID().replaceAll("\"", ""));
                ssid=mWifiInfo.getSSID().replaceAll("\"", "");
                Map<String, String> map = SpUtils.getWifi(context);
                if(map != null){
                    String pwd = map.get(ssid);
                    etPwd.setText(pwd);
                }else {
                    etPwd.setText(null);
                }
            }
        }
    }

    @OnClick({R.id.rl_back, R.id.rl_eye, R.id.btn_sure, R.id.rl_wifi, R.id.ll_jizhu, R.id.rl_right})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_jizhu:
                if(isJizhu){
                    isJizhu = false;
                    ivJizhu.setImageResource(R.mipmap.duihao_null);
                }else {
                    isJizhu = true;
                    ivJizhu.setImageResource(R.mipmap.duihao_blue);
                }
                break;
            case R.id.rl_wifi:
                Intent intent = new Intent();
                intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
                startActivityForResult(intent, 10001);
                break;
            case R.id.btn_sure:
                PermissionManager.instance().request(AddDeviceWifiActivity.this, new OnPermissionCallback() {
                    @Override
                    public void onRequestAllow(String permissionName) {
                        String pwd = etPwd.getText().toString();
                        if(StringUtils.isEmpty(pwd)){
                            ToastUtil.showShort(context, "密码不能为空");
                        }else {
                            if(NetUtil.isWifi5G(context)){
                                //提示去切换wifi
                                toDialog(context, "提示", "当前连接wifi为5G，是否切换到非5G网络", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        Intent intent = new Intent();
                                        intent.setAction("android.net.wifi.PICK_WIFI_NETWORK");
                                        startActivityForResult(intent, 10001);
                                    }
                                }, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                            }else{
                                if(isJizhu){
                                    Map<String, String> map = SpUtils.getWifi(context);
                                    if(map == null){
                                        map = new LinkedHashMap<>();
                                    }
                                    map.put(ssid, pwd);
                                    SpUtils.setWifi(context, map);
                                }
                                if(type.equals("1")){
                                    showWifiConfig();
                                }else if(type.equals("2")){
                                    Logger.e("123123", "type--"+type+"--xlh--"+xlh+"--anquan--"+anquan);
                                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                                    YsWifiConfig();
                                }
                            }
                        }
//                configWifi();
                    }

                    @Override
                    public void onRequestRefuse(String permissionName) {
                        ToastUtil.showShort(context, "请打开定位权限完成设备配网");
                    }

                    @Override
                    public void onRequestNoAsk(String permissionName) {
                        String pwd = etPwd.getText().toString();
                        if(StringUtils.isEmpty(pwd)){
                            ToastUtil.showShort(context, "密码不能为空");
                        }else {
                            if(type.equals("1")){
                                showWifiConfig();
                            }else if(type.equals("2")){
                                Logger.e("123123", "type--"+type+"--xlh--"+xlh+"--anquan--"+anquan);
                                dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                                YsWifiConfig();
                            }
                        }
//                configWifi();
                    }
                }, Manifest.permission.ACCESS_FINE_LOCATION);
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_eye:
                if (!isShowPwd) {
                    isShowPwd = true;
                    ivEye.setImageResource(R.mipmap.eye);
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPwd.setSelection(etPwd.getText().length());
                } else {
                    isShowPwd = false;
                    ivEye.setImageResource(R.mipmap.biyan);
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPwd.setSelection(etPwd.getText().length());
                }
                break;
            case R.id.rl_right:
                DialogBohao dialogBohao = new DialogBohao(context, new DialogBohao.ClickListener() {
                    @Override
                    public void onSure() {
                        ViseUtil.Post(context, NetUrl.getServiceNumber, null, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                com.jiufang.wsyapp.utils.Logger.e("123123", s);
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    String phone = jsonObject.optString("data");
                                    if(StringUtils.isEmpty(phone)){
                                        ToastUtil.showShort(context, "客服电话维护中，暂时无法拨号");
                                    }else {
                                        Intent intent1 = new Intent(Intent.ACTION_DIAL);
                                        Uri data = Uri.parse("tel:" + phone);
                                        intent1.setData(data);
                                        startActivity(intent1);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onElse(String s) {

                            }
                        });
                    }
                });
                dialogBohao.show();
                break;
        }
    }

    /**
     * 配置萤石设备wifi
     */
    private void YsWifiConfig() {

        // 开启日志
        EZWiFiConfigManager.showLog(true);

        // step1.准备配网参数
        ApConfigParam param = new ApConfigParam();
        param.routerWifiSsid /*路由器wifi名称*/= tvSsid.getText().toString();
        param.routerWifiPwd /*路由器wifi密码*/= etPwd.getText().toString();
        param.deviceSerial /*设备序列号*/= xlh;
        param.deviceVerifyCode /*设备验证码*/= anquan;
        param.deviceHotspotSsid /*设备热点名称*/= "ezviz";
        param.deviceHotspotPwd /*设备热点密码*/= "123456";
        param.autoConnect /*是否自动连接到设备热点*/= false;

        // step2.开始配网
        EZWiFiConfigManager.startAPConfig(getApplication(), param, new EZConfigWifiCallback(){
            @Override
            public void onInfo(int code, String message) {
                super.onInfo(code, message);

                // step3.结束配网
                if (code == EZConfigWifiInfoEnum.CONNECTING_SENT_CONFIGURATION_TO_DEVICE.code){
                    // todo 提示用户配网成功
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            WeiboDialogUtils.closeDialog(dialog);
                            // 配网成功后，需要停止配网
                            Logger.e("123123", "设备配网成功");
                            ToastUtil.showShort(context, "设备配网成功");
                            EZWiFiConfigManager.stopAPConfig();
                            if(tiaozhuanType.equals("1")){
                                Intent intent = new Intent();
                                intent.setClass(context, AddDeviceSureActivity.class);
                                intent.putExtra("type", type);
                                intent.putExtra("xlh", xlh);
                                intent.putExtra("anquan", anquan);
                                intent.putExtra("xinghao", xinghao);
                                startActivity(intent);
                            }else {
                                AddDeviceWifiActivity.this.finish();
                            }
                        }
                    });
                }
            }
            @Override
            public void onError(int code, String description) {
                super.onError(code, description);
                WeiboDialogUtils.closeDialog(dialog);
                // step3.结束配网
                if (code == EZConfigWifiErrorEnum.CONFIG_TIMEOUT.code){
                    // todo 提示用户配网超时
                    // 配网失败后，需要停止配网
                    Logger.e("123123", "配网超时");
                    EZWiFiConfigManager.stopAPConfig();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(context, "配网超时");
                        }
                    });
                }else if(code == EZConfigWifiErrorEnum.MAY_LACK_LOCATION_PERMISSION.code){
                    // todo 提示用户授予app定位权限，并打开定位开关
                    Logger.e("123123", "请授予定位权限并打开定位开关");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(context, "请授予定位权限并打开定位开关");
                        }
                    });
                }else if (code == EZConfigWifiErrorEnum.WRONG_DEVICE_VERIFY_CODE.code){
                    // todo 提示用户验证码输入错误
                    Logger.e("123123", "设备验证码错误");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtil.showShort(context, "设备验证码错误");
                        }
                    });
                }
            }
        });

    }

    /**
     * 请求存储权限
     *
     */
    private void requestStoragePermission(){
        boolean isMinSDKM = Build.VERSION.SDK_INT < 23;
        boolean isGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        if (isMinSDKM || isGranted) {
            startConfig();
            // 存储权限
            return;
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            Logger.d("Uriah", "Uriah + shouldShowRequestPermission true");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
        }

    }

    /**
     * 关闭无线配对
     */
    private void stopConfig() {
        mHandler.removeCallbacks(progressRun);
        LCOpenSDK_ConfigWifi.configWifiStop();// 调用smartConfig停止接口
    }

    /**
     * 开启无线配网流程（权限检查，配对说明）
     */
    public void showWifiConfig() {
        boolean isMinSDKM = Build.VERSION.SDK_INT < 23;
        boolean isGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if (isMinSDKM || isGranted) {

            showPairDescription();
            // 开启无线配对
            return;
        }

        requestLocationPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // TODO Auto-generated method stub
        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (requestCode == 1) {
                showPairDescription();
            } else if (requestCode == 2) {
//                startActivityForResult(new Intent(DeviceConfigWifiActivity.this, CaptureActivity.class), 0x11111);
            }else if(requestCode == 3){
                showPairDescription();
            }

        } else {
//            if (requestCode == 1) {
//                toast(getString(R.string.toast_permission_location_forbidden));
//            } else if (requestCode == 2) {
//                toast(getString(R.string.toast_permission_camera_forbidden));
//            } else if(requestCode == 3){
//                toast(getString(R.string.toast_permission_storage_forbidden));
//            }

        }

    }

    /**
     * 请求相关权限
     */
    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            Log.d("Uriah", "Uriah + shouldShowRequestPermission true");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    /**
     * 显示配对说明
     */
    private void showPairDescription() {
        DialogInterface.OnClickListener dialogOnclicListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case Dialog.BUTTON_POSITIVE:
                        requestStoragePermission();
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        break;
                    case Dialog.BUTTON_NEUTRAL:
                        break;
                }
            }
        };
        // dialog参数设置
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // 先得到构造器
        builder.setTitle("提示"); // 设置标题
        builder.setMessage("调大手机音量，并将手机喇叭对准设备麦克风，确认听到“布谷”声"); // 设置内容
        builder.setPositiveButton("确定",
                dialogOnclicListener);
        builder.setNegativeButton("取消",
                dialogOnclicListener);
        builder.create().show();
    }

    /**
     * 启动无线配对
     */
    private void startConfig() {
        // 开启播放加载控件
        dialog = WeiboDialogUtils.createLoadingDialog(context, "无线配网搜索设备中...");//无线配网搜索设备中...

        String ssid = tvSsid.getText().toString();
        String ssid_pwd = etPwd.getText().toString();
        String code = xlh;

        String mCapabilities = getWifiCapabilities(ssid);
        // 无线超时任务
        mHandler.postDelayed(progressRun, CONFIG_WIFI_TIMEOUT_TIME);
        // 调用接口，开始通过smartConfig匹配 (频率由11000-->17000)
        LCOpenSDK_ConfigWifi.configWifiStart(code, ssid, ssid_pwd, "WPA2", LCSmartConfig.ConfigType.LCConfigWifi_Type_ALL,true,11000,1);
//        searchDevice(CONFIG_SEARCH_DEVICE_TIME);//搜索设备及超时时间
        searchDevice(CONFIG_SEARCH_DEVICE_TIME);//搜索设备及超时时间
    }

    /**
     * 获取wifi加密信息
     */
    private String getWifiCapabilities(String ssid) {
        String mCapabilities = null;
        ScanResult mScanResult = null;
        WifiManager mWifiManager = (WifiManager) getApplicationContext().getSystemService(Activity.WIFI_SERVICE);
        if (mWifiManager != null) {
            WifiInfo mWifi = mWifiManager.getConnectionInfo();
            if (mWifi != null) {
                // 判断SSID是否�?��
                if (mWifi.getSSID() != null
                        && mWifi.getSSID().replaceAll("\"", "").equals(ssid)) {
                    List<ScanResult> mList = mWifiManager.getScanResults();
                    if (mList != null) {
                        for (ScanResult s : mList) {
                            if (s.SSID.replaceAll("\"", "").equals(ssid)) {
                                mScanResult = s;
                                break;
                            }
                        }
                    }
                }
            }
        }
        mCapabilities = mScanResult != null ? mScanResult.capabilities : null;
        return mCapabilities;
    }

    private void searchDevice(int timeout) {
        final String deviceId = xlh;
        Business.getInstance().searchDevice(deviceId, timeout, new Handler() {
            public void handleMessage(final Message msg) {
                if (msg.what < 0) {
                    if (msg.what == -2)
                        mHandler.obtainMessage(DEVICE_SEARCH_FAILED, "device not found").sendToTarget();
                    else
                        mHandler.obtainMessage(DEVICE_SEARCH_FAILED, "StartSearchDevices failed").sendToTarget();
                    return;
                }

                mHandler.obtainMessage(DEVICE_SEARCH_SUCCESS, msg.obj).sendToTarget();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopConfig();
    }

}
