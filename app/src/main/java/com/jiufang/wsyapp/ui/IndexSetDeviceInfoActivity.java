package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetBindDeviceDetailBean;
import com.jiufang.wsyapp.bean.GetDeviceInfoBean;
import com.jiufang.wsyapp.bean.GetDeviceUpdateInfoBean;
import com.jiufang.wsyapp.dialog.DialogCustom;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndexSetDeviceInfoActivity extends BaseActivity {

    private Context context = IndexSetDeviceInfoActivity.this;

    @BindView(R.id.tv_device_name)
    TextView tvDeviceName;
    @BindView(R.id.tv_device_model)
    TextView tvDeviceModel;
    @BindView(R.id.tv_sncode)
    TextView tvSncode;
    @BindView(R.id.iv_fengmian)
    ImageView ivFengmian;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_shengji)
    TextView tvShengji;

    private String id = "";
    private String brandId = "";
    private String sncode = "";

    private Dialog dialog;

    private int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_set_device_info);

        id = getIntent().getStringExtra("id");
        brandId = getIntent().getStringExtra("brandId");
        StatusBarUtils.setStatusBar(IndexSetDeviceInfoActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(IndexSetDeviceInfoActivity.this);
        initData();

    }

    private void initData() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map1 = new LinkedHashMap<>();
        map1.put("deviceId", id);
        map1.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getDeviceInfo, map1, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Gson gson = new Gson();
                GetDeviceInfoBean bean = gson.fromJson(s, GetDeviceInfoBean.class);
                tvDeviceName.setText(bean.getData().getDeviceName());
                tvDeviceModel.setText(bean.getData().getDeviceModel());
                sncode = bean.getData().getDeviceSn();
                tvSncode.setText(bean.getData().getDeviceSn());
                tvVersion.setText(bean.getData().getCurrentVersion());
                String pic = bean.getData().getSnapImage();
                if(pic.length()>2){
                    if(pic.substring(0, 1).equals("/")){
                        pic = pic.substring(1, pic.length());
                    }
                    Glide.with(context).load(NetUrl.BASE_IMG_URL+pic).into(ivFengmian);
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

        Map<String, String> map = new LinkedHashMap<>();
        map.put("deviceId", id);
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getDeviceUpdateInfo, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
//                Logger.e("123123", s);
                Gson gson = new Gson();
                GetDeviceUpdateInfoBean bean = gson.fromJson(s, GetDeviceUpdateInfoBean.class);
                Logger.e("123123", brandId+"000");
                if(brandId.equals("1")){
                    int can = bean.getData().getCanBeUpgrade();
                    if(can == 1){
                        tvShengji.setVisibility(View.VISIBLE);
                    }
                }else if(brandId.equals("2")){
                    int can = bean.getData().getIsNeedUpgrade();
                    if(can == 1){
                        tvShengji.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.tv_copy, R.id.ll_fengmian, R.id.tv_shengji})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_shengji:
                String title = "升级过程将持续几分钟，升级时不能退出应用程序和锁屏，不能断开设备、网络与电源，确定现在升级？";
                DialogCustom dialogCustom = new DialogCustom(context, title, new DialogCustom.ClickListener() {
                    @Override
                    public void onSure() {
                        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("deviceId", id);
                        map.put("userId", SpUtils.getUserId(context));
                        ViseUtil.Post(context, NetUrl.upgradeDevice, map, dialog, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {

                            }

                            @Override
                            public void onElse(String s) {

                            }
                        });
                    }
                });
                dialogCustom.show();
                break;
            case R.id.ll_fengmian:
                //单选并剪裁
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setCrop(true)  // 设置是否使用图片剪切功能。
                        .setSingle(true)  //设置是否单选
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(this, REQUEST_CODE); // 打开相册
                break;
            case R.id.tv_copy:
                StringUtils.copy(sncode, context);
                ToastUtil.showShort(context, "已复制");
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            final ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);

            dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
            File file = new File(images.get(0));

            ViseHttp.UPLOAD(NetUrl.uploadDeviceCoverImage)
                    .addParam("deviceId", id)
                    .addParam("userId", SpUtils.getUserId(context))
                    .addFile("file", file)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            Logger.e("123123", data);
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optInt("code") == 200){
                                    ToastUtil.showShort(context, "封面上传成功");
                                    String pic = jsonObject.optString("data");
                                    if(pic.length()>2){
                                        if(pic.substring(0, 1).equals("/")){
                                            pic = pic.substring(1, pic.length());
                                        }
                                        Glide.with(context).load(NetUrl.BASE_IMG_URL+pic).into(ivFengmian);
                                    }
                                }
                                WeiboDialogUtils.closeDialog(dialog);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {
                            WeiboDialogUtils.closeDialog(dialog);
                        }
                    });

            /**
             * 是否是来自于相机拍照的图片，
             * 只有本次调用相机拍出来的照片，返回时才为true。
             * 当为true时，图片返回的结果有且只有一张图片。
             */
            boolean isCameraImage = data.getBooleanExtra(ImageSelector.IS_CAMERA_IMAGE, false);
        }

    }

}
