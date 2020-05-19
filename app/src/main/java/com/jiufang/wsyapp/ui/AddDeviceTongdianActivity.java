package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeviceTongdianActivity extends BaseActivity {

    private Context context = AddDeviceTongdianActivity.this;

    @BindView(R.id.iv_select)
    ImageView ivSelect;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_tongdian);

        StatusBarUtils.setStatusBar(AddDeviceTongdianActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddDeviceTongdianActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.ll_select, R.id.btn_next})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.ll_select:
                ivSelect.setImageResource(R.mipmap.gouxuan);
                btnNext.setBackgroundResource(R.drawable.bg_ffa16f_3dp);
                btnNext.setEnabled(true);
                break;
            case R.id.btn_next:

                break;
        }
    }

}
