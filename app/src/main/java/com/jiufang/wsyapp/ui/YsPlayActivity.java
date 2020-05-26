package com.jiufang.wsyapp.ui;

import android.os.Bundle;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import butterknife.ButterKnife;

public class YsPlayActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ys_play);

        StatusBarUtils.setStatusBar(YsPlayActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(YsPlayActivity.this);

    }

}
