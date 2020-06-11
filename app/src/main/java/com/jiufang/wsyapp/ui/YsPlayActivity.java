package com.jiufang.wsyapp.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZCloudRecordFile;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YsPlayActivity extends BaseActivity implements SurfaceHolder.Callback {

    @BindView(R.id.remote_playback_wnd_sv)
    SurfaceView surfaceView;

    private EZPlayer mPlaybackPlayer = null;
    private Handler mHandler = null;

    private String code = "";
    private int cameraNo;
    private String yanzheng = "";
    private EZCloudRecordFile cloudRecordFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ys_play);

        code = getIntent().getStringExtra("code");
        cameraNo = getIntent().getIntExtra("cameraNo", 0);
        yanzheng = getIntent().getStringExtra("yanzheng");
        cloudRecordFile = getIntent().getParcelableExtra("bean");
        StatusBarUtils.setStatusBar(YsPlayActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(YsPlayActivity.this);
        initData();

    }

    private void initData() {

        surfaceView.getHolder().addCallback(this);
        mPlaybackPlayer = MyApplication.getOpenSDK().createPlayer(code, cameraNo);
        mPlaybackPlayer.setHandler(mHandler);
        mPlaybackPlayer.setSurfaceHold(surfaceView.getHolder());
        mPlaybackPlayer.setPlayVerifyCode(yanzheng);
        mPlaybackPlayer.startPlayback(cloudRecordFile);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlaybackPlayer != null) {
            MyApplication.getOpenSDK().releasePlayer(mPlaybackPlayer);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (mPlaybackPlayer != null) {
            mPlaybackPlayer.setSurfaceHold(surfaceHolder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (mPlaybackPlayer != null) {
            mPlaybackPlayer.setSurfaceHold(null);
        }
    }

}
