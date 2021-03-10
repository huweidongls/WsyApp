package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.net.NetSpeed;
import com.jiufang.wsyapp.net.NetSpeedTimer;
import com.jiufang.wsyapp.utils.FileUtils;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.vise.xsnow.common.GsonUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.vise.xsnow.http.callback.UCallback;
import com.vise.xsnow.http.mode.DownProgress;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WifiCheckActivity extends BaseActivity {

    private Context context = WifiCheckActivity.this;

    private long start = 0;
    private long end = 0;

    private String uploadResult = "";
    private String downloadResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_check);

        StatusBarUtils.setStatusBar(WifiCheckActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(WifiCheckActivity.this);
        upload();

    }

    private void ping() {
        String delay = new String();
        Process p = null;
        String url = "www.baidu.com";                        //指向服务端IP
        try {
            p = Runtime.getRuntime().exec("ping -c 4 " + url);//PING 该地址 4次
            BufferedReader buf = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String str = new String();
            while ((str = buf.readLine()) != null) {
                if (str.contains("avg")) {
                    int i = str.indexOf("/", 20);
                    int j = str.indexOf(".", i);
                    System.out.println("--->str:" + str);
                    delay = str.substring(i + 1, j);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String state = "普通";
        if (delay != null && !TextUtils.isEmpty(delay)) {
            int lag = Integer.parseInt(delay);
            if (0 < lag && lag < 30) {            // 1~30ms：极快，几乎察觉不出有延迟，玩任何游戏速度都特别顺畅
                state = "极快";
            } else if (30 <= lag && lag < 50) {     // 31~50ms：良好，可以正常游戏，没有明显的延迟情况
                state = "良好";
            } else if (50 <= lag && lag < 100) {    // 51~100ms：普通，对抗类游戏在一定水平以上能感觉出延迟，偶尔感觉到停顿
                state = "普通";
            } else if (100 <= lag && lag < 200) {   // 100ms~200ms：较差，无法正常游玩对抗类游戏，有明显卡顿，偶尔出现丢包和掉线现象
                state = "较差";
            } else if (200 <= lag && lag < 500) {   // 200ms~500ms：很差，访问网页有明显的延迟和卡顿，经常出现丢包或无法访问
                state = "很差";
            } else if (500 <= lag && lag < 1000) {  // >500ms：极差，难以接受的延迟和丢包，甚至无法访问网页
                state = "极差";
            } else {                       // >1000ms：基本无法访问
                state = "无法访问";
            }
        }
        if(WifiCheckActivity.this.isFinishing()&&WifiCheckActivity.this.isDestroyed()){

        }else {
            Intent intent = new Intent();
            intent.setClass(context, WifiCheckResultActivity.class);
            intent.putExtra("download", downloadResult);
            intent.putExtra("upload", uploadResult);
            intent.putExtra("delay", delay);
            intent.putExtra("state", state);
            startActivity(intent);
            finish();
        }
    }

    private void upload() {

        try {

            InputStream is = getResources().getAssets().open("wifitest.file");
            String appFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Wsy/" + "wifitest.file";
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Wsy";
            File file = new File(path);
            //判断文件夹是否存在,如果不存在则创建文件夹
            if (!file.exists()) {
                file.mkdir();
            }
            File file1 = new File(appFile);
            writeBytesToFile(is, file1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initData() {

        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        start = System.currentTimeMillis();
        ViseHttp.DOWNLOAD("https://hvc.jiufangkeji.com/resource/wifitest/wifitest.file")
                .setRootName(path)
                .setDirName("Wsy")
                .setFileName("wifitest.file")
                .request(new ACallback<DownProgress>() {
                    @Override
                    public void onSuccess(DownProgress data) {
                        Logger.e("123123", data.getPercent());
                        if (data.isDownComplete()) {
                            end = System.currentTimeMillis();
                            String appFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Wsy/" + "wifitest.file";
                            File file = new File(appFile);
                            try {
                                long size = FileUtils.getFileSize(file);
                                long sudu = size / (end - start) * 1000;
                                downloadResult = FileUtils.formatFileSize(sudu);
                                Logger.e("123123", "size--" + size + "--sudu--" + sudu + "--result--" + downloadResult);
                                file.delete();
                                ping();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    @OnClick({R.id.rl_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
        }
    }

    public void writeBytesToFile(InputStream is, File file) throws IOException{
        FileOutputStream fos = null;
        try {
            byte[] data = new byte[2048];
            int nbread = 0;
            fos = new FileOutputStream(file);
            while((nbread=is.read(data))>-1){
                fos.write(data,0,nbread);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally{
            if (fos!=null){
                fos.close();
            }
            start = System.currentTimeMillis();
            ViseHttp.UPLOAD("/wifitest/uploadFile")
                    .addFile("file", file, new UCallback() {
                        @Override
                        public void onProgress(long currentLength, long totalLength, float percent) {
                            Logger.e("123123", percent+"");
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    })
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
//                            Logger.e("123123", data);
                            end = System.currentTimeMillis();
                            long size = 0;
                            try {
                                size = FileUtils.getFileSize(file);
                                long sudu = size / (end - start) * 1000;
                                uploadResult = FileUtils.formatFileSize(sudu);
//                                Logger.e("123123", "size--" + size + "--sudu--" + sudu + "--result--" + uploadResult);
                                file.delete();
                                initData();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {
                            Logger.e("123123", "upload"+errMsg);
                        }
                    });
        }
    }

}
