package com.jiufang.wsyapp.fragment;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.LazyFragment;
import com.jiufang.wsyapp.bean.GetUserInfoBean;
import com.jiufang.wsyapp.dialog.DialogBohao;
import com.jiufang.wsyapp.dialog.DialogShiming;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.ui.AboutActivity;
import com.jiufang.wsyapp.ui.GoumaiJiluActivity;
import com.jiufang.wsyapp.ui.LoginActivity;
import com.jiufang.wsyapp.ui.MainActivity;
import com.jiufang.wsyapp.ui.MyDeviceActivity;
import com.jiufang.wsyapp.ui.MyFileActivity;
import com.jiufang.wsyapp.ui.PersonInformationActivity;
import com.jiufang.wsyapp.utils.GlideUtils;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;

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

    @OnClick({R.id.tv_login, R.id.rl1, R.id.rl_exit, R.id.rl_my_device, R.id.rl_goumaijilu, R.id.rl_person, R.id.rl_my_file,
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
                        String phone = "0451-88886666";
                        Intent intent1 = new Intent(Intent.ACTION_DIAL);
                        Uri data = Uri.parse("tel:" + phone);
                        intent1.setData(data);
                        startActivity(intent1);
                    }
                });
                dialogBohao.show();
                break;
            case R.id.rl_wifi:
                ToastUtil.showShort(getContext(), "功能开发中...");
                break;
            case R.id.rl_set:
                ToastUtil.showShort(getContext(), "功能开发中...");
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
                ToastUtil.showShort(getContext(), "功能开发中...");
                break;
            case R.id.rl_exit:
                Map<String, String> map = new LinkedHashMap<>();
                map.put("userId", SpUtils.getUserId(getContext()));
                ViseUtil.Post(getContext(), NetUrl.logout, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        SpUtils.clear(getContext());
                        ToastUtil.showShort(getContext(), "退出登录");
                        Intent intent = new Intent();
                        intent.setClass(getContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    @Override
                    public void onElse(String s) {

                    }
                });
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
