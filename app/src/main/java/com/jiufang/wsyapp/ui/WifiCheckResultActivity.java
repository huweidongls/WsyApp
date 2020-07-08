package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WifiCheckResultActivity extends BaseActivity {

    private Context context = WifiCheckResultActivity.this;

    @BindView(R.id.tv_state)
    TextView tvState;
    @BindView(R.id.tv_delay)
    TextView tvDelay;
    @BindView(R.id.tv_download)
    TextView tvDownload;
    @BindView(R.id.tv_upload)
    TextView tvUpload;

    private String downloadResult = "";
    private String uploadResult = "";
    private String delay = "";
    private String state = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_check_result);

        downloadResult = getIntent().getStringExtra("download");
        uploadResult = getIntent().getStringExtra("upload");
        delay = getIntent().getStringExtra("delay");
        state = getIntent().getStringExtra("state");
        StatusBarUtils.setStatusBar(WifiCheckResultActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(WifiCheckResultActivity.this);
        initData();

    }

    private void initData() {

        tvState.setText("网络状态"+state);
        tvDelay.setText(delay+"ms");
        tvDownload.setText(downloadResult+"/s");
        tvUpload.setText(uploadResult+"/s");

    }

    @OnClick({R.id.rl_back, R.id.btn})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn:
                finish();
                break;
        }
    }

}
