package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.UtilsDevicePic;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeviceSuccessActivity extends BaseActivity {

    private Context context = AddDeviceSuccessActivity.this;

    @BindView(R.id.iv_title)
    ImageView ivTitle;

    private String id = "";
    private String xinghao = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_success);

        id = getIntent().getStringExtra("id");
        xinghao = getIntent().getStringExtra("xinghao");
        StatusBarUtils.setStatusBar(AddDeviceSuccessActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddDeviceSuccessActivity.this);
        initData();

    }

    private void initData() {

        ivTitle.setImageResource(UtilsDevicePic.getDevicePic(xinghao));

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
                intent.putExtra("type", "0");
                startActivity(intent);
                break;
        }
    }

}
