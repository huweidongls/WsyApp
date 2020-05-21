package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeviceSuccessActivity extends BaseActivity {

    private Context context = AddDeviceSuccessActivity.this;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_success);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(AddDeviceSuccessActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddDeviceSuccessActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.btn_next})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_next:
                intent.setClass(context, AddDeviceAddressActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
        }
    }

}
