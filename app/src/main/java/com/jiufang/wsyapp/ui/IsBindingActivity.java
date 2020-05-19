package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class IsBindingActivity extends BaseActivity {

    private Context context = IsBindingActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_is_binding);

        StatusBarUtils.setStatusBar(IsBindingActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(IsBindingActivity.this);
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
