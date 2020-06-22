package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndexSetDeviceInfoActivity extends BaseActivity {

    private Context context = IndexSetDeviceInfoActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_set_device_info);

        StatusBarUtils.setStatusBar(IndexSetDeviceInfoActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(IndexSetDeviceInfoActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}