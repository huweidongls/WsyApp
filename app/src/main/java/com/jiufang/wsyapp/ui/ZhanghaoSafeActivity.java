package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhanghaoSafeActivity extends BaseActivity {

    private Context context = ZhanghaoSafeActivity.this;

    @BindView(R.id.tv_phone)
    TextView tvPhone;

    private String phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhanghao_safe);

        phone = getIntent().getStringExtra("phone");
        StatusBarUtils.setStatusBar(ZhanghaoSafeActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(ZhanghaoSafeActivity.this);
        initData();

    }

    private void initData() {

        if(phone.length() == 11){
            phone = phone.substring(0, 3) + "****" + phone.substring(7, phone.length());
            tvPhone.setText(phone);
        }

    }

    @OnClick({R.id.rl_back, R.id.rl_pwd, R.id.rl_login_history})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_login_history:
                intent.setClass(context, LoginHistoryActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_pwd:
                intent.setClass(context, ForgetActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
