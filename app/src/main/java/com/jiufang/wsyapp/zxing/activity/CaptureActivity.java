/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jiufang.wsyapp.zxing.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.Result;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.ui.AddDeviceTongdianActivity;
import com.jiufang.wsyapp.ui.AddShebeiActivity;
import com.jiufang.wsyapp.ui.IsBindingActivity;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.zxing.camera.CameraManager;
import com.jiufang.wsyapp.zxing.decode.DecodeThread;
import com.jiufang.wsyapp.zxing.utils.BeepManager;
import com.jiufang.wsyapp.zxing.utils.CaptureActivityHandler;
import com.jiufang.wsyapp.zxing.utils.InactivityTimer;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * This activity opens the camera and does the actual scanning on a background
 * thread. It draws a viewfinder to help the user place the barcode correctly,
 * shows feedback as the image processing is happening, and then overlays the
 * results when a scan is successful.
 *
 * @author dswitkin@google.com (Daniel Switkin)
 * @author Sean Owen
 */
public final class CaptureActivity extends Activity implements SurfaceHolder.Callback {

    private static final String TAG = CaptureActivity.class.getSimpleName();

    private Context context = CaptureActivity.this;

    private CameraManager cameraManager;
    private CaptureActivityHandler handler;
    private InactivityTimer inactivityTimer;
    private BeepManager beepManager;

    private SurfaceView scanPreview = null;
    private RelativeLayout scanContainer;
    private RelativeLayout scanCropView;
    private ImageView scanLine;

    /**
     * 控制闪光灯
     */
    private LinearLayout llDeng;
    private ImageView ivDeng;
    private TextView tvDeng;

    private Rect mCropRect = null;
    private boolean isHasSurface = false;

    public Handler getHandler() {
        return handler;
    }

    public CameraManager getCameraManager() {
        return cameraManager;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTitle("扫描二维码");
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_capture);

        StatusBarUtils.setStatusBar(CaptureActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(CaptureActivity.this);

        scanPreview = (SurfaceView) findViewById(R.id.capture_preview);
        scanContainer = (RelativeLayout) findViewById(R.id.capture_container);
        scanCropView = (RelativeLayout) findViewById(R.id.capture_crop_view);
        scanLine = (ImageView) findViewById(R.id.capture_scan_line);

        inactivityTimer = new InactivityTimer(this);
        beepManager = new BeepManager(this);

        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0.0f, Animation
                .RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT,
                0.9f);
        animation.setDuration(4500);
        animation.setRepeatCount(-1);
        animation.setRepeatMode(Animation.RESTART);
        scanLine.startAnimation(animation);

        llDeng = findViewById(R.id.ll_deng);
        ivDeng = findViewById(R.id.iv_deng);
        tvDeng = findViewById(R.id.tv_deng);
//        setPramaFrontLight(false);
        llDeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!cameraManager.getOpenFlash()){
                    //打开
                    cameraManager.openFlash();
                    ivDeng.setImageResource(R.mipmap.zhaoliang);
                    tvDeng.setText("轻触关闭");
                }else {
                    //关闭
                    cameraManager.openFlash();
                    ivDeng.setImageResource(R.mipmap.zhaomie);
                    tvDeng.setText("轻触照亮");
                }
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.tv_xlh})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_xlh:
                intent.setClass(context, AddShebeiActivity.class);
                startActivity(intent);
                break;
        }
    }

    public static final String KEY_FRONT_LIGHT = "preferences_front_light";
    private boolean getPramaFrontLight() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(CaptureActivity.this);
        boolean currentSetting = prefs.getBoolean(KEY_FRONT_LIGHT, false);
        return currentSetting;
    }

    private void setPramaFrontLight(boolean isChecked) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(CaptureActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_FRONT_LIGHT, isChecked);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // CameraManager must be initialized here, not in onCreate(). This is
        // necessary because we don't
        // want to open the camera driver and measure the screen size if we're
        // going to show the help on
        // first launch. That led to bugs where the scanning rectangle was the
        // wrong size and partially
        // off screen.
        cameraManager = new CameraManager(getApplication());

        handler = null;

        if (isHasSurface) {
            // The activity was paused but not stopped, so the surface still
            // exists. Therefore
            // surfaceCreated() won't be called, so init the camera here.
            initCamera(scanPreview.getHolder());
        } else {
            // Install the callback and wait for surfaceCreated() to init the
            // camera.
            scanPreview.getHolder().addCallback(this);
        }

        inactivityTimer.onResume();
    }

    @Override
    protected void onPause() {
        if (handler != null) {
            handler.quitSynchronously();
            handler = null;
        }
        inactivityTimer.onPause();
        beepManager.close();
        cameraManager.closeDriver();
        if (!isHasSurface) {
            scanPreview.getHolder().removeCallback(this);
        }
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        inactivityTimer.shutdown();
        super.onDestroy();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (holder == null) {
            Log.e(TAG, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!isHasSurface) {
            isHasSurface = true;
            initCamera(holder);
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isHasSurface = false;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * A valid barcode has been found, so give an indication of success and show
     * the results.
     *
     * @param rawResult The contents of the barcode.
     * @param bundle    The extras
     */
    public void handleDecode(Result rawResult, Bundle bundle) {
        inactivityTimer.onActivity();
        beepManager.playBeepSoundAndVibrate();

//        Intent resultIntent = new Intent();
//        bundle.putInt("width", mCropRect.width());
//        bundle.putInt("height", mCropRect.height());
//        bundle.putString("result", rawResult.getText());
//        resultIntent.putExtras(bundle);
//        this.setResult(RESULT_OK, resultIntent);
//        CaptureActivity.this.finish();

        String result = rawResult.getText();
        if(result.contains("{")&&result.contains(":")&&result.contains("}")){
            //乐橙设备
            jiexiLc(result);
        }else {
            //萤石设备
            jiexiYs(result);
        }

    }

    private void jiexiLc(String result){

        String devSn="";
        String devType="";
        if (result.contains(",")) {
            devSn = result.split(",")[0].split(":")[1];
        }else if(result.contains(":")){
            devSn=result.split(":")[0];
            devType=result.split(":")[1];
        }
        if(devSn!=null&&devSn.length()!=15){
            devSn= result.substring(result.indexOf(":")+1,result.indexOf("}"));
        }
//        mSnText.setText(devSn);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("snCode", devSn);
        map.put("userId", SpUtils.getUserId(context));
        String finalDevSn = devSn;
        ViseUtil.Post(context, NetUrl.checkDeviceBindStatus, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Intent intent = new Intent();
                intent.setClass(context, AddDeviceTongdianActivity.class);
                intent.putExtra("type", "1");
                intent.putExtra("xlh", finalDevSn);
                startActivity(intent);
            }

            @Override
            public void onElse(String s) {
                Intent intent = new Intent();
                intent.setClass(context, IsBindingActivity.class);
                intent.putExtra("type", "1");
                intent.putExtra("s", s);
                startActivity(intent);
            }
        });

    }

    private void jiexiYs(String resultString) {
        //ys7C78047095TQJATVCS-C1HC-1D1WFR
        // 初始化数据
        String mSerialNoStr = "";
        String mSerialVeryCodeStr = "";
        String deviceType = "";
        Log.e("123123", "resultString = " + resultString);
        // CS-F1-1WPFR
        // CS-A1-1WPFR
        // CS-C1-1FPFR
        // resultString = "www.xxx.com\n456654855\nABCDEF\nCS-C3-21PPFR\n";
        // 字符集合
        String[] newlineCharacterSet = {
                "\n\r", "\r\n", "\r", "\n"};
        String stringOrigin = resultString;
        // 寻找第一次出现的位置
        int a = -1;
        int firstLength = 1;
        for (String string : newlineCharacterSet) {
            if (a == -1) {
                a = resultString.indexOf(string);
                if (a > stringOrigin.length() - 3) {
                    a = -1;
                }
                if (a != -1) {
                    firstLength = string.length();
                }
            }
        }

        // 扣去第一次出现回车的字符串后，剩余的是第二行以及以后的
        if (a != -1) {
            resultString = resultString.substring(a + firstLength);
        }
        // 寻找最后一次出现的位置
        int b = -1;
        for (String string : newlineCharacterSet) {
            if (b == -1) {
                b = resultString.indexOf(string);
                if (b != -1) {
                    mSerialNoStr = resultString.substring(0, b);
                    firstLength = string.length();
                }
            }
        }

        // 寻找遗失的验证码阶段
        if (mSerialNoStr != null && b != -1 && (b + firstLength) <= resultString.length()) {
            resultString = resultString.substring(b + firstLength);
        }

        // 再次寻找回车键最后一次出现的位置
        int c = -1;
        for (String string : newlineCharacterSet) {
            if (c == -1) {
                c = resultString.indexOf(string);
                if (c != -1) {
                    mSerialVeryCodeStr = resultString.substring(0, c);
                }
            }
        }

        // 寻找CS-C2-21WPFR 判断是否支持wifi
        if (mSerialNoStr != null && c != -1 && (c + firstLength) <= resultString.length()) {
            resultString = resultString.substring(c + firstLength);
        }
        if (resultString != null && resultString.length() > 0) {
            deviceType = resultString;
        }

        if (b == -1) {
            mSerialNoStr = resultString;
        }

        if (mSerialNoStr == null) {
            mSerialNoStr = stringOrigin;
        }
        Log.e("123123", "mSerialNoStr = " + mSerialNoStr + ",mSerialVeryCodeStr = " + mSerialVeryCodeStr
                + ",deviceType = " + deviceType);

    }

    private void initCamera(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        }
        if (cameraManager.isOpen()) {
            Log.w(TAG, "initCamera() while already open -- late SurfaceView callback?");
            return;
        }
        try {
            cameraManager.openDriver(surfaceHolder);
            // Creating the handler starts the preview, which can also throw a
            // RuntimeException.
            if (handler == null) {
                handler = new CaptureActivityHandler(this, cameraManager, DecodeThread.ALL_MODE);
            }

            initCrop();
        } catch (IOException ioe) {
            Log.w(TAG, ioe);
            displayFrameworkBugMessageAndExit();
        } catch (RuntimeException e) {
            // Barcode Scanner has seen crashes in the wild of this variety:
            // java.?lang.?RuntimeException: Fail to connect to camera service
            Log.w(TAG, "Unexpected error initializing camera", e);
            displayFrameworkBugMessageAndExit();
        }
    }

    private void displayFrameworkBugMessageAndExit() {
        // camera error
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("打开相机");
        builder.setMessage("Camera error");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
        builder.show();
    }

    public void restartPreviewAfterDelay(long delayMS) {
        if (handler != null) {
            handler.sendEmptyMessageDelayed(R.id.restart_preview, delayMS);
        }
    }

    public Rect getCropRect() {
        return mCropRect;
    }

    /**
     * 初始化截取的矩形区域
     */
    private void initCrop() {
        int cameraWidth = cameraManager.getCameraResolution().y;
        int cameraHeight = cameraManager.getCameraResolution().x;

        /** 获取布局中扫描框的位置信息 */
        int[] location = new int[2];
        scanCropView.getLocationInWindow(location);

        int cropLeft = location[0];
        int cropTop = location[1] - getStatusBarHeight();

        int cropWidth = scanCropView.getWidth();
        int cropHeight = scanCropView.getHeight();

        /** 获取布局容器的宽高 */
        int containerWidth = scanContainer.getWidth();
        int containerHeight = scanContainer.getHeight();

        /** 计算最终截取的矩形的左上角顶点x坐标 */
        int x = cropLeft * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的左上角顶点y坐标 */
        int y = cropTop * cameraHeight / containerHeight;

        /** 计算最终截取的矩形的宽度 */
        int width = cropWidth * cameraWidth / containerWidth;
        /** 计算最终截取的矩形的高度 */
        int height = cropHeight * cameraHeight / containerHeight;

        /** 生成最终的截取的矩形 */
        mCropRect = new Rect(x, y, width + x, height + y);
    }

    private int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}