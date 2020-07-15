package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetLoginLogByIdBean;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.SystemUtil;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginHistoryDetailsActivity extends BaseActivity {

    private Context context = LoginHistoryDetailsActivity.this;

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tv3)
    TextView tv3;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_history_details);

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(LoginHistoryDetailsActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(LoginHistoryDetailsActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        ViseUtil.Post(context, NetUrl.getLoginLogById, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetLoginLogByIdBean bean = gson.fromJson(s, GetLoginLogByIdBean.class);
                tvTime.setText(bean.getData().getLoginTime());
                tv1.setText("你的物视云账号已于"+bean.getData().getLoginTime()+" 在已下手机登录：");
                tv2.setText("手机类型："+bean.getData().getOsName());
                tv3.setText("手机型号："+bean.getData().getOsVersion());
            }

            @Override
            public void onElse(String s) {
                Logger.e("123123", s);
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
