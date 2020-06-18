package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.OrientationEventListener;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.app.MyApplication;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetBindDeviceUserInfoBean;
import com.jiufang.wsyapp.dialog.DialogBaojing;
import com.jiufang.wsyapp.dialog.DialogBaojingSuccess;
import com.jiufang.wsyapp.dialog.DialogVideoBaojing;
import com.jiufang.wsyapp.mediaplay.RecoderSeekBar;
import com.jiufang.wsyapp.mediaplay.fragment.MediaPlayFragment;
import com.jiufang.wsyapp.mediaplay.util.MediaPlayHelper;
import com.jiufang.wsyapp.mediaplay.util.TimeHelper;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.videogo.exception.ErrorCode;
import com.videogo.openapi.EZConstants;
import com.videogo.openapi.EZPlayer;
import com.videogo.openapi.bean.EZCloudRecordFile;
import com.videogo.openapi.bean.EZDeviceRecordFile;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YsPlayActivity extends BaseActivity implements SurfaceHolder.Callback {

    private Context context = YsPlayActivity.this;

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
    @BindView(R.id.record_seekbar)
    RecoderSeekBar mRecordSeekbar;
    @BindView(R.id.record_startTime)
    TextView mRecordStartTime;
    @BindView(R.id.record_endTime)
    TextView mRecordEndTime;

    private EZPlayer mPlaybackPlayer = null;

    private String code = "";
    private int cameraNo;
    private String yanzheng = "";
    private EZCloudRecordFile cloudRecordFile;
    private EZDeviceRecordFile deviceRecordFile;

    private boolean isPlay = true;
    private boolean isPlayEnd = false;
    private boolean isFirst = true;

    public enum ORIENTATION {isPortRait, isLandScape, isNone}
    protected MediaPlayFragment.ORIENTATION mOrientation = MediaPlayFragment.ORIENTATION.isNone;

    // 屏幕方向改变监听器
    private OrientationEventListener mOrientationListener;

    private String playType = "";//0云录像  1本地录像
    private String id = "";

    public static final int UPDATA_UI = 101010;
    private Timer timer;

    private Handler playBackHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // showDownLoad();
            switch (msg.what) {
                // 片段播放完毕
                // 380061即开始时间>=结束时间，播放完成
                case ErrorCode.ERROR_CAS_RECORD_SEARCH_START_TIME_ERROR:

                    break;
                case EZConstants.EZPlaybackConstants.MSG_REMOTEPLAYBACK_PLAY_FINISH:
                    isPlay = false;
                    ivPlayOrPause.setImageResource(R.drawable.record_btn_play);
                    timer.cancel();
                    progress = 0;
                    mRecordSeekbar.setProgress(progress);
                    mRecordSeekbar.setCanTouchAble(false);
                    isPlayEnd = true;
                    break;
                // 画面显示第一帧
                case EZConstants.EZPlaybackConstants.MSG_REMOTEPLAYBACK_PLAY_SUCCUSS:
                    if(isFirst){
                        isFirst = false;
                        initSeekBar();
                    }
                    isPlayEnd = false;
                    mRecordSeekbar.setCanTouchAble(true);
                    break;
                case EZConstants.EZPlaybackConstants.MSG_REMOTEPLAYBACK_STOP_SUCCESS:

                    break;
                case EZConstants.EZPlaybackConstants.MSG_REMOTEPLAYBACK_PLAY_FAIL:

                    break;
                case UPDATA_UI:
                    if(progress<=max){
                        mRecordStartTime.setText(TimeHelper.getTimeHMS(beginTime+(progress*1000)));
                        mRecordSeekbar.setProgress(progress);
                    }
                    break;
                default:
                    break;
            }
        }

    };

    private long beginTime;
    private int max;
    private int progress = 0;

    /**
     * 初始化seekbar
     */
    private void initSeekBar() {

        String startTime;
        String endTime;
        if(playType.equals("0")){
            startTime = StringUtils.calendar2string(cloudRecordFile.getStartTime());
            endTime = StringUtils.calendar2string(cloudRecordFile.getStopTime());
            beginTime = TimeHelper.getTimeStamp(StringUtils.calendar2string(cloudRecordFile.getStartTime()));
        }else {
            startTime = StringUtils.calendar2string(deviceRecordFile.getStartTime());
            endTime = StringUtils.calendar2string(deviceRecordFile.getStopTime());
            beginTime = TimeHelper.getTimeStamp(StringUtils.calendar2string(deviceRecordFile.getStartTime()));
        }
        max = (int)(StringUtils.dateFormatToLong(endTime)-StringUtils.dateFormatToLong(startTime))/1000;
        mRecordSeekbar.setMax(max);
        mRecordSeekbar.setProgress(progress);
        timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if(isPlay){
                    progress = progress+1;
                    Message message = Message.obtain();
                    message.what = UPDATA_UI;
                    playBackHandler.sendMessage(message);
                }
            }
        };
        timer.schedule(timerTask, 1000, 1000);

        mRecordSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(b){
                    long trackTime = beginTime+(i*1000);
                    if (mPlaybackPlayer != null) {
                        Calendar seekTime = Calendar.getInstance();
                        seekTime.setTime(new Date(trackTime));
                        if(mPlaybackPlayer.seekPlayback(seekTime)){
                            mRecordStartTime.setText(TimeHelper.getTimeHMS(beginTime+(i*1000)));
                            mRecordSeekbar.setProgress(i);
                            progress = i;
                            isPlay = true;
                        }
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ys_play);

        id = getIntent().getStringExtra("id");
        code = getIntent().getStringExtra("code");
        cameraNo = getIntent().getIntExtra("cameraNo", 0);
        yanzheng = getIntent().getStringExtra("yanzheng");
        playType = getIntent().getStringExtra("type");
        StatusBarUtils.setStatusBar(YsPlayActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(YsPlayActivity.this);
        mRecordSeekbar.setCanTouchAble(false);
        startListener();
        initData();

    }

    private void initData() {

        if(playType.equals("0")){
            cloudRecordFile = getIntent().getParcelableExtra("bean");
        }else {
            deviceRecordFile = getIntent().getParcelableExtra("bean");
        }

        String startTime;
        String endTime;
        if(playType.equals("0")){
            startTime = StringUtils.calendar2string(cloudRecordFile.getStartTime());
            endTime = StringUtils.calendar2string(cloudRecordFile.getStopTime());
        }else {
            startTime = StringUtils.calendar2string(deviceRecordFile.getStartTime());
            endTime = StringUtils.calendar2string(deviceRecordFile.getStopTime());
        }
        if(startTime.contains(" ")&&endTime.contains(" ")){
            mRecordStartTime.setText(startTime.split(" ")[1]);
            mRecordEndTime.setText(endTime.split(" ")[1]);
        }

        surfaceView.getHolder().addCallback(this);
        mPlaybackPlayer = MyApplication.getOpenSDK().createPlayer(code, cameraNo);
        mPlaybackPlayer.setHandler(playBackHandler);
        mPlaybackPlayer.setSurfaceHold(surfaceView.getHolder());
        mPlaybackPlayer.setPlayVerifyCode(yanzheng);
        if(playType.equals("0")){
            mPlaybackPlayer.startPlayback(cloudRecordFile);
        }else {
            mPlaybackPlayer.startPlayback(deviceRecordFile);
        }

    }

    @OnClick({R.id.rl_back, R.id.record_play_pause, R.id.record_scale, R.id.rl_baojing})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_baojing:
                //一键报警
                Map<String, String> map = new LinkedHashMap<>();
                map.put("bindDeviceId", id);
                ViseUtil.Post(context, NetUrl.getBindDeviceUserInfo, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        com.jiufang.wsyapp.utils.Logger.e("123123", s);
                        Gson gson = new Gson();
                        GetBindDeviceUserInfoBean bean = gson.fromJson(s, GetBindDeviceUserInfoBean.class);
                        String areaName = bean.getData().getAreaName();
                        if(StringUtils.isEmpty(areaName)){
                            Intent intent1 = new Intent();
                            intent1.setClass(context, AddDeviceAddressActivity.class);
                            intent1.putExtra("id", id);
                            intent1.putExtra("type", "1");
                            startActivity(intent1);
                        }else {
                            String startTime = "";
                            String endTime = "";
                            String type = "";
                            if(playType.equals("0")){
                                type = "false";
                                startTime = StringUtils.calendar2string(cloudRecordFile.getStartTime());
                                endTime = StringUtils.calendar2string(cloudRecordFile.getStopTime());
                            }else {
                                type = "true";
                                startTime = StringUtils.calendar2string(deviceRecordFile.getStartTime());
                                endTime = StringUtils.calendar2string(deviceRecordFile.getStopTime());
                            }
                            DialogVideoBaojing dialogBaojing = new DialogVideoBaojing(context, "2", type, startTime, endTime, id, bean.getData().getPersonName(),
                                    bean.getData().getPersonPhone(), bean.getData().getAddress(), bean.getData().getHouseNumber(), new DialogVideoBaojing.ClickListener() {
                                @Override
                                public void onClick() {
                                    DialogBaojingSuccess dialogBaojingSuccess = new DialogBaojingSuccess(context);
                                    dialogBaojingSuccess.show();
                                }
                            });
                            dialogBaojing.show();
                        }
                    }

                    @Override
                    public void onElse(String s) {

                    }
                });
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.record_play_pause:
                if(isPlayEnd){
                    if(playType.equals("0")){
                        if(mPlaybackPlayer.startPlayback(cloudRecordFile)){
                            isPlay = true;
                            ivPlayOrPause.setImageResource(R.drawable.record_btn_pause);
                            initSeekBar();
                        }
                    }else {
                        if(mPlaybackPlayer.startPlayback(deviceRecordFile)){
                            isPlay = true;
                            ivPlayOrPause.setImageResource(R.drawable.record_btn_pause);
                            initSeekBar();
                        }
                    }
                }else {
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
