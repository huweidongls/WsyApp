package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LcPlayActivity extends BaseActivity {

    private Context context = LcPlayActivity.this;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lc_play);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(LcPlayActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(LcPlayActivity.this);
        initData();

    }

    private void initData() {



    }

    @OnClick({R.id.rl_back, R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn:
                jiebang();
                break;
        }
    }

    private void jiebang() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("bindDeviceId", id);
        ViseUtil.Post(context, NetUrl.unBindDevice, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
            }
        });

    }

}
