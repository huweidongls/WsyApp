package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.LoginByPasswordBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.VersionUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;
import com.vise.xsnow.http.ViseHttp;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private Context context = LoginActivity.this;

    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    private boolean isShowPwd = false;

    private Dialog dialog;

    private InputMethodManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        manager = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
        StatusBarUtils.setStatusBarTransparent(LoginActivity.this);
        ButterKnife.bind(LoginActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_eye, R.id.tv_register, R.id.tv_forget, R.id.btn_login, R.id.ll_phone, R.id.ll_pwd})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.ll_phone:
                etPhone.requestFocus();
                if (manager != null) manager.showSoftInput(etPhone, 0);
                break;
            case R.id.ll_pwd:
                etPwd.requestFocus();
                if (manager != null) manager.showSoftInput(etPwd, 0);
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
            case R.id.tv_register:
                intent.setClass(context, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_forget:
                intent.setClass(context, ForgetActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                String phone = etPhone.getText().toString();
                String pwd = etPwd.getText().toString();
                if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(pwd)){
                    ToastUtil.showShort(context, "手机号或密码不能为空");
                }else {
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("password", pwd);
                    map.put("username", phone);
                    ViseUtil.Post(context, NetUrl.loginByPassword, map, dialog, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            Gson gson = new Gson();
                            LoginByPasswordBean bean = gson.fromJson(s, LoginByPasswordBean.class);
                            SpUtils.setUserId(context, bean.getData().getUserId()+"");
                            SpUtils.setToken(context, bean.getData().getToken());
                            Map<String, String> map1 = new LinkedHashMap<>();
                            map1.put("token", bean.getData().getToken());
                            map1.put("app-version", VersionUtils.packageName(getApplicationContext()));
                            map1.put("device-type", "1");
                            map1.put("device-unique-id", MyApplication.deviceId);
                            map1.put("device-name", "");
                            ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
                                    .globalHeaders(map1);
                            Intent intent = new Intent();
                            intent.setClass(context, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }

                        @Override
                        public void onElse(String s) {

                        }
                    });
                }
                break;
        }
    }

}
