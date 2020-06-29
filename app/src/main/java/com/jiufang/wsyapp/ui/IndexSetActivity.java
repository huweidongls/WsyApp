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

public class IndexSetActivity extends BaseActivity {

    private Context context = IndexSetActivity.this;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_set);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(IndexSetActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(IndexSetActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.ll_device_info, R.id.rl_zhence, R.id.rl_set_more_lc})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_set_more_lc:
                intent.setClass(context, IndexSetMoreLcActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_zhence:
                intent.setClass(context, ZhencebufangLcActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            case R.id.ll_device_info:
                intent.setClass(context, IndexSetDeviceInfoActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
