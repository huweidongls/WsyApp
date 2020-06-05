package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetAreaByNameBean;
import com.jiufang.wsyapp.bean.GetUserInfoBean;
import com.jiufang.wsyapp.dialog.DialogNickname;
import com.jiufang.wsyapp.dialog.DialogShiming;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.GlideUtils;
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

public class PersonInformationActivity extends BaseActivity {

    private Context context = PersonInformationActivity.this;

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_realname)
    EditText etRealname;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_men)
    EditText etMen;

    private String area = "";
    private Dialog dialog;

    private int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_information);

        StatusBarUtils.setStatusBar(PersonInformationActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(PersonInformationActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getUserInfo, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetUserInfoBean bean = gson.fromJson(s, GetUserInfoBean.class);
                GlideUtils.into(context, NetUrl.BASE_IMG_URL+bean.getData().getHeadPicture(), ivAvatar);
                tvNickname.setText(bean.getData().getNickName());
                tvPhone.setText(bean.getData().getPhone());
                String realname = bean.getData().getRealName();
                if(!StringUtils.isEmpty(realname)){
                    etRealname.setText(realname);
                    etRealname.setSelection(realname.length());
                }
                String address = bean.getData().getAddress();
                if(!StringUtils.isEmpty(address)){
                    tvAddress.setText(address);
                }
                String men = bean.getData().getHouseNumber();
                if(!StringUtils.isEmpty(men)){
                    etMen.setText(men);
                    etMen.setSelection(men.length());
                }
                area = bean.getData().getAreaName();

            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl_map, R.id.rl_save, R.id.rl_avatar, R.id.rl_nickname})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_nickname:
                DialogNickname dialogNickname = new DialogNickname(context, new DialogNickname.ClickListener() {
                    @Override
                    public void onSure(String nickname) {
                        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待");
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("nickName", nickname);
                        map.put("userId", SpUtils.getUserId(context));
                        ViseUtil.Post(context, NetUrl.updateNickname, map, dialog, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                ToastUtil.showShort(context, "修改成功");
                                tvNickname.setText(nickname);
                            }

                            @Override
                            public void onElse(String s) {

                            }
                        });
                    }
                });
                dialogNickname.show();
                break;
            case R.id.rl_avatar:
                //单选并剪裁
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setCrop(true)  // 设置是否使用图片剪切功能。
                        .setSingle(true)  //设置是否单选
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(this, REQUEST_CODE); // 打开相册
                break;
            case R.id.rl_save:
                String realname = etRealname.getText().toString();
                String address = tvAddress.getText().toString();
                String men = etMen.getText().toString();
                if(StringUtils.isEmpty(realname)||StringUtils.isEmpty(address)||StringUtils.isEmpty(men)
                        ||StringUtils.isEmpty(area)){
                    ToastUtil.showShort(context, "请完善实名信息后提交");
                }else {
                    onSave();
                }
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_map:
                intent.setClass(context, AddDeviceMapActivity.class);
                startActivityForResult(intent, 1001);
                break;
        }
    }

    private void onSave() {

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待");
        Map<String, String> map1 = new LinkedHashMap<>();
        map1.put("areaName", area);
        ViseUtil.Post(context, NetUrl.getAreaByName, map1, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetAreaByNameBean bean = gson.fromJson(s, GetAreaByNameBean.class);
                int areaId = bean.getData().getId();
                Map<String, String> map = new LinkedHashMap<>();
                map.put("address", tvAddress.getText().toString());
                map.put("areaId", areaId+"");
                map.put("houseNumber", etMen.getText().toString());
                map.put("realName", etRealname.getText().toString());
                map.put("userId", SpUtils.getUserId(context));
                ViseUtil.Post(context, NetUrl.updateUserRealInfo, map, dialog, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        ToastUtil.showShort(context, "保存成功");
                        finish();
                    }

                    @Override
                    public void onElse(String s) {

                    }
                });
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null&&requestCode == 1001){
            PoiInfo poiInfo = data.getParcelableExtra("bean");
            tvAddress.setText(poiInfo.getAddress()+"-"+poiInfo.getName());
            area = poiInfo.getCity();
            Logger.e("123123", area);
        }
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            final ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);

            File file = new File(images.get(0));

            ViseHttp.UPLOAD(NetUrl.upload)
                    .addParam("userId", SpUtils.getUserId(context))
                    .addFile("file", file)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optInt("code") == 200){
                                    ToastUtil.showShort(context, "头像上传成功");
                                    Glide.with(context).load(jsonObject.optString("data")).into(ivAvatar);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

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
