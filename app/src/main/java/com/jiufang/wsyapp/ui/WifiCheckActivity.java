package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.net.NetSpeed;
import com.jiufang.wsyapp.net.NetSpeedTimer;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import butterknife.ButterKnife;

public class WifiCheckActivity extends BaseActivity {

    private Context context = WifiCheckActivity.this;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == NetSpeedTimer.NET_SPEED_TIMER_DEFAULT){
                String speed = (String) msg.obj;
                //打印你所需要的网速值，单位默认为kb/s
                Logger.e("123123", "current net speed  = " + speed);
            }
        }
    };
    private NetSpeedTimer mNetSpeedTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_check);

        StatusBarUtils.setStatusBar(WifiCheckActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(WifiCheckActivity.this);
        initNewWork();

    }

    private void initNewWork() {
        //创建NetSpeedTimer实例
        mNetSpeedTimer = new NetSpeedTimer(context, new NetSpeed(), mHandler).setDelayTime(1000).setPeriodTime(2000);
        //在想要开始执行的地方调用该段代码
        mNetSpeedTimer.startSpeedTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mNetSpeedTimer.stopSpeedTimer();
    }
}
