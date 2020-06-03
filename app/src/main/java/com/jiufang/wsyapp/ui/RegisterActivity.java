package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.LoginByCaptchaBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.DESUtil;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    private Context context = RegisterActivity.this;

    @BindView(R.id.tv_send)
    TextView tvGetCode;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;

    public TextView getCode_btn() {
        return tvGetCode;
    }

    private String phone = "";
    private String code = "";

    private InputMethodManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        manager = ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE));
        MyApplication.registerTimeCount.setActivity(RegisterActivity.this);
        StatusBarUtils.setStatusBarTransparent(RegisterActivity.this);
        ButterKnife.bind(RegisterActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.tv_send, R.id.btn_next, R.id.ll_phone, R.id.ll_pwd})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.ll_phone:
                etPhone.requestFocus();
                if (manager != null) manager.showSoftInput(etPhone, 0);
                break;
            case R.id.ll_pwd:
                etCode.requestFocus();
                if (manager != null) manager.showSoftInput(etCode, 0);
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_send:
                phone = etPhone.getText().toString();
                if(StringUtils.isEmpty(phone)){
                    ToastUtil.showShort(context, "手机号不能为空");
                }else if(!StringUtils.isPhoneNumberValid(phone)){
                    ToastUtil.showShort(context, "请输入正确格式的手机号码");
                }else {
                    MyApplication.registerTimeCount.start();
                    sendCode(phone);
                }
                break;
            case R.id.btn_next:
                phone = etPhone.getText().toString();
                code = etCode.getText().toString();
                if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(code)){
                    ToastUtil.showShort(context, "手机号或验证码不能为空");
                }else {
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("captcha", code);
                    map.put("phone", phone);
                    ViseUtil.Post(context, NetUrl.loginByCaptcha, map, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            Gson gson = new Gson();
                            LoginByCaptchaBean bean = gson.fromJson(s, LoginByCaptchaBean.class);
                            String userId = bean.getData().getUserId()+"";
                            String token = bean.getData().getToken();
                            SpUtils.setToken(context, bean.getData().getToken());
                            intent.setClass(context, RegisterSetPwdActivity.class);
                            intent.putExtra("userid", userId);
                            intent.putExtra("token", token);
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

    private void sendCode(String phone) {

        String key = "Hvc.2020";
        String s1 = DESUtil.encryptDES(phone, key);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("encryptionData", s1);
        map.put("phone", phone);
        ViseUtil.Post(context, NetUrl.sendCaptchaCodeWithSMS, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                ToastUtil.showShort(context, "验证码发送成功");
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

}
