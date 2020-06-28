package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CloudDetailsActivity extends BaseActivity {

    private Context context = CloudDetailsActivity.this;

    @BindView(R.id.iv_agree)
    ImageView ivAgree;

    private boolean isXieyi = false;

    private String type = "1";//1乐橙  2萤石

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloud_details);

        StatusBarUtils.setStatusBar(CloudDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(CloudDetailsActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.ll_xieyi, R.id.ll_buy})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_xieyi:
                if(!isXieyi){
                    isXieyi = true;
                    ivAgree.setImageResource(R.mipmap.duihao_xieyi);
                }
                break;
            case R.id.ll_buy:
                if(isXieyi){
                    if(type.equals("1")){
                        intent.setClass(context, LcTaocanActivity.class);
                        startActivity(intent);
                    }else {
                        intent.setClass(context, YsTaocanActivity.class);
                        startActivity(intent);
                    }
                }else {
                    ToastUtil.showShort(context, "请勾选协议");
                }
                break;
        }
    }

}
