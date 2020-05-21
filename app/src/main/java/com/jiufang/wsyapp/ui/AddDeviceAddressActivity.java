package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeviceAddressActivity extends BaseActivity {

    private Context context = AddDeviceAddressActivity.this;

    @BindView(R.id.et_device_name)
    EditText etDeviceName;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_map)
    EditText etMap;
    @BindView(R.id.et_address)
    EditText etAddress;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_address);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(AddDeviceAddressActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddDeviceAddressActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.btn_complete})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_complete:
//                onComplete();
                Intent intent = new Intent();
                intent.setClass(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    private void onComplete() {

        String deviceName = etDeviceName.getText().toString();
        String name = etName.getText().toString();
        String phone = etPhone.getText().toString();
        String map = etMap.getText().toString();
        String address = etAddress.getText().toString();

        if(StringUtils.isEmpty(deviceName)&&StringUtils.isEmpty(name)&&StringUtils.isEmpty(phone)
                &&StringUtils.isEmpty(map)&&StringUtils.isEmpty(address)){
            Intent intent = new Intent();
            intent.setClass(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if(!StringUtils.isEmpty(deviceName)&&StringUtils.isEmpty(name)&&StringUtils.isEmpty(phone)
                &&StringUtils.isEmpty(map)&&StringUtils.isEmpty(address)){

            Map<String, String> map1 = new LinkedHashMap<>();
            map1.put("", "");
            ViseUtil.Post(context, NetUrl.updateBindDeviceName, map1, new ViseUtil.ViseListener() {
                @Override
                public void onReturn(String s) {

                }

                @Override
                public void onElse(String s) {

                }
            });

        }else if(StringUtils.isEmpty(deviceName)&&(!StringUtils.isEmpty(name)||!StringUtils.isEmpty(phone)
                ||!StringUtils.isEmpty(map)||!StringUtils.isEmpty(address))){

        }else {

        }

    }

}
