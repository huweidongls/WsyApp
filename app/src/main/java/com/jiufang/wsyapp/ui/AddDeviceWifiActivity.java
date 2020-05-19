package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeviceWifiActivity extends BaseActivity {

    private Context context = AddDeviceWifiActivity.this;

    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    private String type = "";//1乐橙 2萤石
    private String xlh = "";
    private String anquan = "";

    private boolean isShowPwd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_wifi);

        type = getIntent().getStringExtra("type");
        xlh = getIntent().getStringExtra("xlh");
        anquan = getIntent().getStringExtra("anquan");
        StatusBarUtils.setStatusBar(AddDeviceWifiActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddDeviceWifiActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.rl_eye})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
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
        }
    }

}
