package com.jiufang.wsyapp.ui;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.mediaplay.Business;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;
import com.lechange.common.configwifi.LCSmartConfig;
import com.lechange.common.log.Logger;
import com.lechange.opensdk.configwifi.LCOpenSDK_ConfigWifi;

import java.io.Serializable;
import java.util.List;

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

    private String type = "";//1乐橙 2萤石
    private String xlh = "";
    private String anquan = "";

    private boolean isShowPwd = false;

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
                    Intent intent = new Intent();
                    intent.setClass(context, AddDeviceSureActivity.class);
                    intent.putExtra("type", type);
                    intent.putExtra("xlh", xlh);
                    intent.putExtra("anquan", anquan);
                    startActivity(intent);
                    break;
                case DEVICE_SEARCH_FAILED:
                    Logger.d("123123", "deviceSearchFailed:" + msg.obj);
                    ToastUtil.showShort(context, "WiFi配置失败");
                    stopConfig();
                    break;
                default:
                    break;
            }
        }
    };

    private static final int CONFIG_WIFI_TIMEOUT_TIME = 120 * 1000;
    private static final int CONFIG_SEARCH_DEVICE_TIME = 120 * 1000;
    String ssid="";
    private String devType="";
    private Runnable progressRun = new Runnable() {
        @Override
        public void run() {
            com.jiufang.wsyapp.utils.Logger.e("123123", "WiFi配置超时");
            stopConfig();
            WeiboDialogUtils.closeDialog(dialog);
        }
    };

    private Dialog dialog;

    private WifiInfo mWifiInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_wifi);

        type = getIntent().getStringExtra("type");
        xlh = getIntent().getStringExtra("xlh");
        anquan = getIntent().getStringExtra("anquan");
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
        }

    }

    @OnClick({R.id.rl_back, R.id.rl_eye, R.id.btn_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_sure:
                String pwd = etPwd.getText().toString();
                if(StringUtils.isEmpty(pwd)){
                    ToastUtil.showShort(context, "密码不能为空");
                }else {
                    showWifiConfig();
                }
//                configWifi();
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
        }
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
        builder.setMessage("根据说明书操作设备的配对按钮，再点击确认进入配对"); // 设置内容
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
