package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetAndroidDownloadUrlBean;
import com.jiufang.wsyapp.bean.GetAndroidUpdateInfoBean;
import com.jiufang.wsyapp.dialog.DialogVersion;
import com.jiufang.wsyapp.dialog.ProgressUpdataDialog;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.VersionUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.vise.xsnow.http.mode.DownProgress;
import com.xuexiang.xupdate.XUpdate;
import com.xuexiang.xupdate._XUpdate;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    private Context context = AboutActivity.this;

    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.btn)
    Button btn;
    @BindView(R.id.tv_time)
    TextView tvTime;

    private GetAndroidUpdateInfoBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        StatusBarUtils.setStatusBar(AboutActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AboutActivity.this);
        initData();

    }

    private void initData() {

        tvVersion.setText("V "+ VersionUtils.packageName(context));

        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getAndroidUpdateInfo, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                bean = gson.fromJson(s, GetAndroidUpdateInfoBean.class);
                int versionCode = VersionUtils.packageCode(context);
                tvTime.setText("上次更新日期："+bean.getTime());
                if(bean.getData().getCode()>versionCode){
                    btn.setVisibility(View.VISIBLE);
                }else {
                    btn.setVisibility(View.GONE);
                }
            }

            @Override
            public void onElse(String s) {
                Logger.e("123123", s);
            }
        });

    }

    @OnClick({R.id.rl_back, R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn:
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
                                                    openAPK(appFile);
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

                    }
                });
                dialogVersion.setCancelable(false);
                dialogVersion.setCanceledOnTouchOutside(false);
                dialogVersion.show();
                break;
        }
    }

    /**
     * 安装apk
     * @param fileSavePath
     */
    private void openAPK(String fileSavePath){

        File file = new File(fileSavePath);
        _XUpdate.startInstallApk(context, file); //填写文件所在的路径

    }

}
