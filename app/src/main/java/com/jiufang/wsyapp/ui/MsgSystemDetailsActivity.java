package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MsgSystemDetailsActivity extends BaseActivity {

    private Context context = MsgSystemDetailsActivity.this;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_system_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(MsgSystemDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(MsgSystemDetailsActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("messageId", id);
        ViseUtil.Post(context, NetUrl.getSysMessageById, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {

            }
        });

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
