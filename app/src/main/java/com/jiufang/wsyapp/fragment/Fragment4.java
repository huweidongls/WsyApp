package com.jiufang.wsyapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.Manifest;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.LazyFragment;
import com.jiufang.wsyapp.bean.GetUserInfoBean;
import com.jiufang.wsyapp.dialog.DialogBohao;
import com.jiufang.wsyapp.dialog.DialogShiming;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.ui.AboutActivity;
import com.jiufang.wsyapp.ui.CloudActivity;
import com.jiufang.wsyapp.ui.GoumaiJiluActivity;
import com.jiufang.wsyapp.ui.LoginActivity;
import com.jiufang.wsyapp.ui.MainActivity;
import com.jiufang.wsyapp.ui.MyDeviceActivity;
import com.jiufang.wsyapp.ui.MyFileActivity;
import com.jiufang.wsyapp.ui.PersonInformationActivity;
import com.jiufang.wsyapp.ui.SetActivity;
import com.jiufang.wsyapp.ui.WifiCheckActivity;
import com.jiufang.wsyapp.utils.GlideUtils;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

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
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    private String phoneNum = "";

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
    }

    private void initData() {

        ViseUtil.Post(getContext(), NetUrl.getServiceNumber, null, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String phone = jsonObject.optString("data");
                    tvPhone.setText(phone);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

        if(SpUtils.getUserId(getContext()).equals("0")){
            tvLogin.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.GONE);
            tvHy.setVisibility(View.GONE);
        }else {
            tvLogin.setVisibility(View.GONE);
            tvName.setVisibility(View.VISIBLE);
            tvHy.setVisibility(View.VISIBLE);
            Map<String, String> map = new LinkedHashMap<>();
            map.put("userId", SpUtils.getUserId(getContext()));
            ViseUtil.Post(getContext(), NetUrl.getUserInfo, map, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {
                    Logger.e("123123", s);
                    Gson gson = new Gson();
                    GetUserInfoBean bean = gson.fromJson(s, GetUserInfoBean.class);
                    phoneNum = bean.getData().getPhone();
                    GlideUtils.into(getContext(), NetUrl.BASE_IMG_URL+bean.getData().getHeadPicture(), ivAvatar);
                    tvName.setText(bean.getData().getNickName());
                    int shiming = bean.getData().getAuthentication();
                    if(shiming == 0){
                        DialogShiming dialogShiming = new DialogShiming(getContext(), new DialogShiming.ClickListener() {
                            @Override
                            public void onSure() {
                                Intent intent = new Intent();
                                intent.setClass(getContext(), PersonInformationActivity.class);
                                startActivity(intent);
                            }
                        });
                        dialogShiming.show();
                    }
                }

                @Override
                public void onElse(String s) {

                }
            });
        }

    }

    @OnClick({R.id.tv_login, R.id.rl1, R.id.rl_my_device, R.id.rl_goumaijilu, R.id.rl_person, R.id.rl_my_file,
    R.id.rl_wifi, R.id.rl_set, R.id.rl_changjian, R.id.rl_about})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_about:
                intent.setClass(getContext(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_changjian:
                DialogBohao dialogBohao = new DialogBohao(getContext(), new DialogBohao.ClickListener() {
                    @Override
                    public void onSure() {
                        ViseUtil.Post(getContext(), NetUrl.getServiceNumber, null, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                Logger.e("123123", s);
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    String phone = jsonObject.optString("data");
                                    if(StringUtils.isEmpty(phone)){
                                        ToastUtil.showShort(getContext(), "客服电话维护中，暂时无法拨号");
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
            case R.id.rl_wifi:
                PermissionManager.instance().request(getActivity(), new OnPermissionCallback() {
                    @Override
                    public void onRequestAllow(String permissionName) {
                        intent.setClass(getContext(), WifiCheckActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onRequestRefuse(String permissionName) {
                        ToastUtil.showShort(getContext(), "请打开文件读写权限");
                    }

                    @Override
                    public void onRequestNoAsk(String permissionName) {
                        intent.setClass(getContext(), WifiCheckActivity.class);
                        startActivity(intent);
                    }
                }, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
//                ToastUtil.showShort(getContext(), "功能开发中...");
//                Map<String, String> map1 = new LinkedHashMap<>();
//                map1.put("target", "DEVICE");
//                map1.put("targetValue", "094a5ef680b3481485b947e85f4c9e34");
//                map1.put("title", "测试");
//                map1.put("body", "测试body");
//                ViseUtil.Post(getContext(), NetUrl.testAliyunPushNotice, map1, new ViseUtil.ViseListener() {
//                    @Override
//                    public void onReturn(String s) {
//                        Logger.e("123123", s);
//                    }
//
//                    @Override
//                    public void onElse(String s) {
//                        Logger.e("123123", s);
//                    }
//                });
                break;
            case R.id.rl_set:
                if(SpUtils.getUserId(getContext()).equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), SetActivity.class);
                    intent.putExtra("phone", phoneNum);
                    startActivity(intent);
                }
//                ToastUtil.showShort(getContext(), "功能开发中...");
//                Map<String, String> map2 = new LinkedHashMap<>();
//                map2.put("target", "DEVICE");
//                map2.put("targetValue", "094a5ef680b3481485b947e85f4c9e34");
//                map2.put("title", "测试");
//                map2.put("body", "测试body");
//                ViseUtil.Post(getContext(), NetUrl.testAliyunPushMessage, map2, new ViseUtil.ViseListener() {
//                    @Override
//                    public void onReturn(String s) {
//                        Logger.e("123123", s);
//                    }
//
//                    @Override
//                    public void onElse(String s) {
//                        Logger.e("123123", s);
//                    }
//                });
                break;
            case R.id.rl_my_file:
                if(SpUtils.getUserId(getContext()).equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), MyFileActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_login:
                intent.setClass(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_person:
                if(SpUtils.getUserId(getContext()).equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), PersonInformationActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl_goumaijilu:
                if(SpUtils.getUserId(getContext()).equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), GoumaiJiluActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl_my_device:
                if(SpUtils.getUserId(getContext()).equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), MyDeviceActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl1:
                if(SpUtils.getUserId(getContext()).equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), CloudActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    @Override
    protected void onFragmentVisible() {
        super.onFragmentVisible();
        initData();
    }

    @Override
    protected void onFragmentHide() {
        super.onFragmentHide();
    }

}
