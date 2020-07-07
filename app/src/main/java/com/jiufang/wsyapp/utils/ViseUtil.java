package com.jiufang.wsyapp.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.View;

import com.google.gson.Gson;
import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.bean.GetAndroidDownloadUrlBean;
import com.jiufang.wsyapp.bean.GetAndroidUpdateInfoBean;
import com.jiufang.wsyapp.dialog.DialogVersion;
import com.jiufang.wsyapp.dialog.ProgressUpdataDialog;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.ui.LoginActivity;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.vise.xsnow.http.mode.DownProgress;
import com.xuexiang.xupdate._XUpdate;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/10/11.
 */

public class ViseUtil {

    public static void Get(final Context context, String url, Map<String, String> map, final RefreshLayout refreshLayout, final int type, final ViseListener listener){

        ViseHttp.GET(url)
                .addParams(map)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("code").equals("200")){
                                listener.onReturn(data);
                            }else {
                                ToastUtil.showShort(context, jsonObject.optString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(type == 0){
                            refreshLayout.finishRefresh(500);
                        }else if(type == 1){
                            refreshLayout.finishLoadMore(500);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        ToastUtil.showShort(context, "网络异常");
                        if(type == 0){
                            refreshLayout.finishRefresh(500);
                        }else if(type == 1){
                            refreshLayout.finishLoadMore(500);
                        }
                    }
                });

    }

    public static void Get(final Context context, String url, Map<String, String> map, final ViseListener listener){

        ViseHttp.GET(url)
                .addParams(map)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("code").equals("200")){
                                listener.onReturn(data);
                            }else {
                                ToastUtil.showShort(context, jsonObject.optString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        ToastUtil.showShort(context, "网络异常");
                    }
                });

    }

    public static void Get(final Context context, String url, Map<String, String> map, final Dialog dialog, final ViseListener listener){

        ViseHttp.GET(url)
                .addParams(map)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("code").equals("200")){
                                listener.onReturn(data);
                            }else {
                                ToastUtil.showShort(context, jsonObject.optString("message"));
                            }
                            WeiboDialogUtils.closeDialog(dialog);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        ToastUtil.showShort(context, "网络异常");
                        WeiboDialogUtils.closeDialog(dialog);
                    }
                });

    }

    public static void Post(final Context context, String url, Map<String, String> map, final RefreshLayout refreshLayout, final int type, final ViseListener listener){

        ViseHttp.POST(url)
                .addParams(map)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("code").equals("200")){
                                listener.onReturn(data);
                            }else if(jsonObject.optString("code").equals("5107")){
                                Intent intent = new Intent();
                                intent.setClass(context, LoginActivity.class);
                                context.startActivity(intent);
                            }else if(jsonObject.optString("code").equals("5109")){
                                ToastUtil.showShort(context, "需要强制更新");
                                qiangzhi(context);
                            }else {
                                ToastUtil.showShort(context, jsonObject.optString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(type == 0){
                            refreshLayout.finishRefresh(500);
                        }else if(type == 1){
                            refreshLayout.finishLoadMore(500);
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        ToastUtil.showShort(context, "网络异常");
                        if(type == 0){
                            refreshLayout.finishRefresh(500);
                        }else if(type == 1){
                            refreshLayout.finishLoadMore(500);
                        }
                    }
                });

    }

    public static void Post(final Context context, String url, Map<String, String> map, final ViseListener listener){

        ViseHttp.POST(url)
                .addParams(map)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("code").equals("200")){
                                listener.onReturn(data);
                            }else if(jsonObject.optString("code").equals("5107")){
                                Intent intent = new Intent();
                                intent.setClass(context, LoginActivity.class);
                                context.startActivity(intent);
                            }else if(jsonObject.optString("code").equals("5109")){
                                ToastUtil.showShort(context, "需要强制更新");
                                qiangzhi(context);
                            }else {
                                listener.onElse(data);
                                ToastUtil.showShort(context, jsonObject.optString("message"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        ToastUtil.showShort(context, "网络异常");
                    }
                });

    }

    public static void Post(final Context context, String url, Map<String, String> map, final Dialog dialog, final ViseListener listener){

        ViseHttp.POST(url)
                .addParams(map)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optString("code").equals("200")){
                                listener.onReturn(data);
                            }else if(jsonObject.optString("code").equals("5107")){
                                Intent intent = new Intent();
                                intent.setClass(context, LoginActivity.class);
                                context.startActivity(intent);
                            }else if(jsonObject.optString("code").equals("5109")){
                                ToastUtil.showShort(context, "需要强制更新");
                                qiangzhi(context);
                            }else {
                                listener.onElse(data);
                                ToastUtil.showShort(context, jsonObject.optString("message"));
                            }
                            WeiboDialogUtils.closeDialog(dialog);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        ToastUtil.showShort(context, "网络异常");
                        WeiboDialogUtils.closeDialog(dialog);
                    }
                });

    }

    public static void qiangzhi(Context context){
        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getAndroidUpdateInfo, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetAndroidUpdateInfoBean bean = gson.fromJson(s, GetAndroidUpdateInfoBean.class);
                DialogVersion dialogVersion = new DialogVersion(context, bean.getData().getVersion(), bean.getData().getDescribe(), new DialogVersion.ClickListener() {
                    @Override
                    public void onSure() {
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("userId", SpUtils.getUserId(context));
                        ViseUtil.Post(context, NetUrl.getAndroidDownloadUrl, map, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                Logger.e("123123", s);
                                Gson gson = new Gson();
                                GetAndroidDownloadUrlBean bean1 = gson.fromJson(s, GetAndroidDownloadUrlBean.class);
                                final ProgressUpdataDialog progressDialog = new ProgressUpdataDialog(context);
                                progressDialog.setCancelable(false);
                                progressDialog.setCanceledOnTouchOutside(false);
                                progressDialog.show();
                                String downloadUrl = bean1.getData();
                                String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                                ViseHttp.DOWNLOAD(downloadUrl)
                                        .setRootName(path)
                                        .setDirName("Wsy")
                                        .setFileName("Wsy.apk")
                                        .request(new ACallback<DownProgress>() {
                                            @Override
                                            public void onSuccess(DownProgress data) {
                                                progressDialog.setInfo(data.getFormatStatusString(), data.getPercent());
                                                if (data.isDownComplete()){
                                                    progressDialog.dismiss();
                                                    String appFile = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Wsy/"+"Wsy.apk";
                                                    openAPK(context, appFile);
                                                }
                                            }

                                            @Override
                                            public void onFail(int errCode, String errMsg) {

                                            }
                                        });
                            }

                            @Override
                            public void onElse(String s) {

                            }
                        });
                    }

                    @Override
                    public void onCancel() {
                        MyApplication.getInstance().exit();
                    }
                });
                dialogVersion.setCancelable(false);
                dialogVersion.setCanceledOnTouchOutside(false);
                dialogVersion.show();
            }

            @Override
            public void onElse(String s) {
                Logger.e("123123", s);
            }
        });
    }

    /**
     * 安装apk
     */
    public static void openAPK(Context context, String fileSavePath){

        File file = new File(fileSavePath);
        _XUpdate.startInstallApk(context, file); //填写文件所在的路径

    }

    public interface ViseListener{
        void onReturn(String s);
        void onElse(String s);
    }

}
