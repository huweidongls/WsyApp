package com.jiufang.wsyapp.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.imageselector.utils.ImageSelector;
import com.donkingliang.imageselector.utils.ImageSelectorUtils;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.LazyFragment;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2020/4/27.
 */

public class Fragment4 extends LazyFragment {

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_hy)
    TextView tvHy;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    private int REQUEST_CODE = 101;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment4;
    }

    @Override
    protected void initView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
        initData();
    }

    private void initData() {

        if(SpUtils.getUserId(getContext()).equals("0")){
            tvLogin.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.GONE);
            tvHy.setVisibility(View.GONE);
        }else {
            tvLogin.setVisibility(View.GONE);
            tvName.setVisibility(View.VISIBLE);
            tvHy.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({R.id.iv_avatar, R.id.rl1})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_avatar:
                //单选并剪裁
                ImageSelector.builder()
                        .useCamera(true) // 设置是否使用拍照
                        .setCrop(true)  // 设置是否使用图片剪切功能。
                        .setSingle(true)  //设置是否单选
                        .setViewImage(true) //是否点击放大图片查看,，默认为true
                        .start(this, REQUEST_CODE); // 打开相册
                break;
            case R.id.rl1:

                break;
        }
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && data != null) {
            //获取选择器返回的数据
            final ArrayList<String> images = data.getStringArrayListExtra(
                    ImageSelectorUtils.SELECT_RESULT);

            File file = new File(images.get(0));

            ViseHttp.UPLOAD(NetUrl.upload)
                    .addParam("userId", SpUtils.getUserId(getContext()))
                    .addFile("file", file)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if(jsonObject.optInt("code") == 200){
                                    ToastUtil.showShort(getContext(), "头像上传成功");
                                    Glide.with(getContext()).load(jsonObject.optString("data")).into(ivAvatar);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {
                            Logger.e("123123", errMsg);
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
