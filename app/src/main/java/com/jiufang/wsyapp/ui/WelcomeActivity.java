package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {

    private Context context = WelcomeActivity.this;

    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;

    private int num = 3;
    private Timer timer;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    if(num <= 0){
                        toMain();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        StatusBarUtils.setStatusBarTransparent(WelcomeActivity.this);
        ButterKnife.bind(WelcomeActivity.this);
        initData();

    }

    private void initData() {

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(num>0){
                    Message msg = Message.obtain();
                    msg.what = 100;
                    handler.sendMessage(msg);
                    num = num - 1;
                }
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 1000, 1000);

    }

    @OnClick({R.id.tv, R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv:
                timer.cancel();
                toMain();
                break;
            case R.id.btn:
                timer.cancel();
                toMain();
                break;
        }
    }

    private synchronized void toMain(){
        Intent intent = new Intent();
        intent.setClass(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

}
