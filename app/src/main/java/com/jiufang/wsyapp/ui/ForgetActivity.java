package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetActivity extends BaseActivity {

    private Context context = ForgetActivity.this;

    @BindView(R.id.tv_send)
    TextView tvGetCode;

    public TextView getCode_btn() {
        return tvGetCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        MyApplication.forgetTimeCount.setActivity(ForgetActivity.this);
        StatusBarUtils.setStatusBarTransparent(ForgetActivity.this);
        ButterKnife.bind(ForgetActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.tv_send, R.id.btn_next})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_send:
                MyApplication.forgetTimeCount.start();
                break;
            case R.id.btn_next:
                intent.setClass(context, ForgetSetPwdActivity.class);
                startActivity(intent);
                break;
        }
    }

}
