package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.vise.xsnow.http.ViseHttp;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetSetPwdActivity extends BaseActivity {

    private Context context = ForgetSetPwdActivity.this;

    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd2)
    EditText etPwd2;

    private String userId = "";
    private String token = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_set_pwd);

        userId = getIntent().getStringExtra("userid");
        token = getIntent().getStringExtra("token");
        StatusBarUtils.setStatusBarTransparent(ForgetSetPwdActivity.this);
        ButterKnife.bind(ForgetSetPwdActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.btn_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_sure:
                String pwd = etPwd.getText().toString();
                String pwd2 = etPwd2.getText().toString();
                if(StringUtils.isEmpty(pwd)||StringUtils.isEmpty(pwd2)){
                    ToastUtil.showShort(context, "密码不能为空");
                }else if(!pwd.equals(pwd2)){
                    ToastUtil.showShort(context, "密码不一致");
                }else {
                    Map<String, String> map1 = new LinkedHashMap<>();
                    map1.put("token", token);
                    map1.put("app-version", "1.0.0");
                    map1.put("device-type", "1");
                    map1.put("device-unique-id", "123");
                    map1.put("device-name", "");
                    ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL)
                            .globalHeaders(map1);
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("userId", userId);
                    map.put("password", pwd);
                    ViseUtil.Post(context, NetUrl.setPassword, map, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            SpUtils.setUserId(context, userId);
                            SpUtils.setToken(context, token);
                            Intent intent = new Intent();
                            intent.setClass(context, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        }
                    });
                }
                break;
        }
    }

}
