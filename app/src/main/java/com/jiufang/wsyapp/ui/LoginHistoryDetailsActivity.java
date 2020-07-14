package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.SystemUtil;

import java.lang.reflect.Method;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginHistoryDetailsActivity extends BaseActivity {

    private Context context = LoginHistoryDetailsActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_history_details);

        StatusBarUtils.setStatusBar(LoginHistoryDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(LoginHistoryDetailsActivity.this);
        initData();

    }

    private void initData() {

        Logger.e("123123", "pinpai--"+SystemUtil.getDeviceBrand()+"--model--"+SystemUtil.getSystemModel());

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
