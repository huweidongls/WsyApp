package com.jiufang.wsyapp.ui;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.mediaplay.MediaPlayActivity;
import com.jiufang.wsyapp.mediaplay.fragment.MediaPlayFragment;
import com.jiufang.wsyapp.mediaplay.util.MediaPlayHelper;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZCloudRecordFile;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YsPlayActivity extends BaseActivity implements SurfaceHolder.Callback {

    @BindView(R.id.remote_playback_wnd_sv)
    SurfaceView surfaceView;
    @BindView(R.id.record_play_pause)
    ImageView ivPlayOrPause;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.vg_play_window)
    RelativeLayout rlPlayWindow;
    @BindView(R.id.record_scale)
    ImageView mRecordScale;

    private EZPlayer mPlaybackPlayer = null;
    private Handler mHandler = null;

    private String code = "";
    private int cameraNo;
    private String yanzheng = "";
    private EZCloudRecordFile cloudRecordFile;

    private boolean isPlay = true;

    public enum ORIENTATION {isPortRait, isLandScape, isNone}
    protected MediaPlayFragment.ORIENTATION mOrientation = MediaPlayFragment.ORIENTATION.isNone;

    // 屏幕方向改变监听器
    private OrientationEventListener mOrientationListener;

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
        startListener();
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

    @OnClick({R.id.rl_back, R.id.record_play_pause, R.id.record_scale})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.record_play_pause:
                if(isPlay){
                    if(mPlaybackPlayer.pausePlayback()){
                        isPlay = false;
                        ivPlayOrPause.setImageResource(R.drawable.record_btn_play);
                    }
                }else {
                    if(mPlaybackPlayer.resumePlayback()){
                        isPlay = true;
                        ivPlayOrPause.setImageResource(R.drawable.record_btn_pause);
                    }
                }
                break;
            case R.id.record_scale:
                if("LANDSCAPE".equals(mRecordScale.getTag())){
                    mOrientation = MediaPlayFragment.ORIENTATION.isPortRait;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }else{
                    mOrientation = MediaPlayFragment.ORIENTATION.isLandScape;
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                break;
        }
    }

    // 横竖屏切换需要,隐藏标题栏
    public void toggleTitle(boolean isShow) {
        rlTop.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPlaybackPlayer != null) {
            MyApplication.getOpenSDK().releasePlayer(mPlaybackPlayer);
        }
        if (mOrientationListener != null) {
            mOrientationListener.disable();
            mOrientationListener = null;
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

    /**
     * 描述：开启屏幕方向监听
     */
    protected final void startListener() {
        mOrientationListener = new OrientationEventListener(this, SensorManager.SENSOR_DELAY_NORMAL) {
            @Override
            public void onOrientationChanged(int rotation) {
                // 设置竖屏
                requestedOrientation(rotation);
            }
        };
        if (mOrientationListener.canDetectOrientation()) {
            mOrientationListener.enable();
        } else {
            mOrientationListener.disable();
        }

    }
    /**
     * 描述：改变屏幕方向
     */
    private void requestedOrientation(int rotation) {
        if (rotation < 10 || rotation > 350) {// 手机顶部向上
            setPortOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else if (rotation < 100 && rotation > 80) {// 手机右边向上
            setLandOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        } else if (rotation < 190 && rotation > 170) {// 手机低边向上
            setPortOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        } else if (rotation < 280 && rotation > 260) {// 手机左边向上
            setLandOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    private void setPortOrientation(int type) {
        switch(mOrientation){
            case isNone:
                setRequestedOrientation(type);
                break;
            case isPortRait:
                mOrientation = MediaPlayFragment.ORIENTATION.isNone;
                break;
            default:
                break;
        }
    }

    private void setLandOrientation(int type) {
        switch(mOrientation){
            case isNone:
                setRequestedOrientation(type);
                break;
            case isLandScape:
                mOrientation = MediaPlayFragment.ORIENTATION.isNone;
                break;
            default:
                break;
        }
    }

    /**
     * @see android.support.v4.app.Fragment#onConfigurationChanged(android.content.res.Configuration)
     *      描述：屏幕方向改变时重新绘制界面
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initWindow(newConfig);
        initSurFace(newConfig);
        resetViews(newConfig);
    }

    /**
     *
     *  实现个性化界面
     * @param mConfiguration
     */
    protected void resetViews(Configuration mConfiguration) {
        if (mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE) { // 横屏
            mRecordScale.setTag("LANDSCAPE");
            mRecordScale.setImageResource(R.drawable.record_btn_smallscreen);
        } else if (mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mRecordScale.setTag("PORTRAIT");
            mRecordScale.setImageResource(R.drawable.record_btn_fullscreen);
        }

    }

    /**
     * 描述：初始化playWindow
     */
    protected void initWindow(Configuration mConfiguration) {
        LinearLayout.LayoutParams mLayoutParams = (LinearLayout.LayoutParams) rlPlayWindow.getLayoutParams();
        if (mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE) { // 横屏
            DisplayMetrics metric = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metric);
            mLayoutParams.width = metric.widthPixels; // 屏幕宽度（像素）
            mLayoutParams.height = metric.heightPixels; // 屏幕高度（像素）
            mLayoutParams.setMargins(0, 0, 0, 0);
        } else if (mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            DisplayMetrics metric = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metric);
            mLayoutParams.width = metric.widthPixels; // 屏幕宽度（像素）
            mLayoutParams.height = metric.widthPixels * 9 / 16;
            mLayoutParams.setMargins(0, 10, 0, 0);
        }
        rlPlayWindow.setLayoutParams(mLayoutParams);
    }

    /**
     * 描述：初始化全屏或非全屏
     */
    private void initSurFace(Configuration mConfiguration) {
        if (mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE) { // 横屏
            toggleTitle(false);
            MediaPlayHelper.setFullScreen(YsPlayActivity.this);
        } else if (mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            toggleTitle(true);
            MediaPlayHelper.quitFullScreen(YsPlayActivity.this);
        }
    }

}
