/*  
 * 项目名: YYS 
 * 文件名: MediaPlayOnlineFragment.java  
 * 版权声明:
 *      本系统的所有内容，包括源码、页面设计，文字、图像以及其他任何信息，
 *      如未经特殊说明，其版权均属大华技术股份有限公司所有。
 *      Copyright (c) 2015 大华技术股份有限公司
 *      版权所有
 */
package com.jiufang.wsyapp.mediaplay.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.bean.GetBindDeviceDetailBean;
import com.jiufang.wsyapp.bean.GetBindDeviceUserInfoBean;
import com.jiufang.wsyapp.bean.GetStorageDetailInfoBean;
import com.jiufang.wsyapp.dialog.DialogBaojing;
import com.jiufang.wsyapp.dialog.DialogBaojingSuccess;
import com.jiufang.wsyapp.dialog.ProgressDialog;
import com.jiufang.wsyapp.mediaplay.Business;
import com.jiufang.wsyapp.mediaplay.entity.ChannelPTZInfo;
import com.jiufang.wsyapp.mediaplay.util.MediaPlayHelper;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.ui.AddDeviceAddressActivity;
import com.jiufang.wsyapp.ui.CloudLcVideoActivity;
import com.jiufang.wsyapp.ui.LocalLcVideoActivity;
import com.jiufang.wsyapp.utils.DensityTool;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.lechange.common.log.Logger;
import com.lechange.opensdk.listener.LCOpenSDK_EventListener;
import com.lechange.opensdk.listener.LCOpenSDK_TalkerListener;
import com.lechange.opensdk.media.LCOpenSDK_Talk;
import com.zyyoona7.popup.EasyPopup;

import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 描述：实时视频监控 作者： lc
 */
public class MediaPlayOnlineFragment extends MediaPlayFragment implements
        OnClickListener {

    private static final String TAG = "LCopen_MediaPlayOnlineFragment";

    protected static final int MediaMain = 0; // 主码流
    protected static final int MediaAssist = 1; // 辅码流
    protected static final int RECORDER_TYPE_DAV = 3; // 录制格式DAV
    protected static final int RECORDER_TYPE_MP4 = 4; // 录制格式MP4

    public enum Cloud {
        up, down, left, right, leftUp, rightUp, leftDown, RightDown, zoomin, zoomout, stop
    }

    // 状态值
    private int bateMode = MediaMain;

    private enum AudioTalkStatus {
        talk_close, talk_opening, talk_open
    }

    private AudioTalkStatus mOpenTalk = AudioTalkStatus.talk_close; // 语音对讲状态
    private boolean isRecord = false; // 正在录制
    private boolean isOpenSound = false; // 声音打开
    private boolean isPlaying; // 正在播放
    private boolean IsPTZOpen = false; // 云台打开

    //	private ChannelInfo channelInfo;
    private GetBindDeviceDetailBean bean;

    private LinearLayout mLiveMenu;
    private ImageView mLiveMode;//SD/HD
    private ImageView mLivePtz;//云台控制
    private ImageView mLiveSound;//声音
    private ImageView mLiveScale;//全屏

    private LinearLayout mLiveUseLayout;
    private LinearLayout llBottom;
    private RelativeLayout mLiveScreenshot; //截屏
    private RelativeLayout mLiveTalk; //对讲
    private ImageView ivDuijiang;
    private RelativeLayout mLiveRecord; //录屏
    private ImageView ivLuxiang;

    private RelativeLayout rlBaojing;
    private RelativeLayout rlCloudVideo;
    private RelativeLayout rlLocalVideo;
    private RelativeLayout rlPtz;

    private EasyPopup easyPopup;

    private boolean mIsOnPtz = false;

    private String id = "";
    private int haveCloud = 0;

    private TextView tvCloudNum;
    private TextView tvBendiNum;
    private TextView tvIsHave;
    private TextView tvShengyu;

    /**
     * 描述：
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getArguments();
        if (b != null) {
//			String channelId = b.getString("bean");
//			channelInfo = Business.getInstance().getChannel(channelId);
            bean = (GetBindDeviceDetailBean) b.getSerializable("bean");
            id = b.getString("id");
            haveCloud = b.getInt("cloud", 0);
            com.jiufang.wsyapp.utils.Logger.e("123123", bean.getData().getDeviceAccessToken());
        }
        if (bean == null) {
            Logger.e(TAG, " **server returned channelInfo == null, leads to toast: Device NOT FOUND");
            toast("设备不存在");
            getActivity().setResult(-1);
            getActivity().finish();
        }
    }

    /**
     * android.view.ViewGroup, android.os.Bundle) 描述：
     *
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mView = inflater.inflate(R.layout.fragment_media_live, container,
                false);
        // 必须赋值，父类需要使用到
        mSurfaceParentView = (ViewGroup) mView.findViewById(R.id.live_window);
        // 初始化窗口大小
        LayoutParams mLayoutParams = (LayoutParams) mSurfaceParentView
                .getLayoutParams();
        DisplayMetrics metric = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metric);
        mLayoutParams.width = metric.widthPixels; // 屏幕宽度（像素）
        mLayoutParams.height = metric.widthPixels * 9 / 16;
        mLayoutParams.setMargins(0, 10, 0, 0);
        mSurfaceParentView.setLayoutParams(mLayoutParams);

        mPlayWin.initPlayWindow(this.getActivity(),
                (ViewGroup) mView.findViewById(R.id.live_window_content), 0);

        mProgressDialog = (ProgressDialog) mView
                .findViewById(R.id.live_play_load);
        mReplayTip = (TextView) mView.findViewById(R.id.live_play_pressed);

        mLiveMenu = (LinearLayout) mView.findViewById(R.id.live_menu);
        mLiveMode = (ImageView) mView.findViewById(R.id.live_mode);
        mLivePtz = (ImageView) mView.findViewById(R.id.live_ptz);
        mLiveSound = (ImageView) mView.findViewById(R.id.live_sound);
        mLiveUseLayout = (LinearLayout) mView
                .findViewById(R.id.live_use_layout);
        mLiveScale = (ImageView) mView.findViewById(R.id.live_scale);
        mLiveScreenshot = mView.findViewById(R.id.live_screenshot);
        mLiveTalk = mView.findViewById(R.id.live_talk);
        ivDuijiang = mView.findViewById(R.id.iv_duijiang);
        mLiveRecord = mView.findViewById(R.id.live_record);
        ivLuxiang = mView.findViewById(R.id.iv_luxiang);
        llBottom = mView.findViewById(R.id.ll_bottom);
        rlBaojing = mView.findViewById(R.id.rl_baojing);
        rlCloudVideo = mView.findViewById(R.id.rl_cloud_video);
        rlLocalVideo = mView.findViewById(R.id.rl_local_video);
        rlPtz = mView.findViewById(R.id.rl_ptz);
        tvCloudNum = mView.findViewById(R.id.tv_cloud_num);
        tvBendiNum = mView.findViewById(R.id.tv_bendi_num);
        tvIsHave = mView.findViewById(R.id.tv_ishave);
        tvShengyu = mView.findViewById(R.id.tv_shengyu);
        if(haveCloud == 0){
            rlPtz.setVisibility(View.GONE);
        }else if(haveCloud == 1){
            rlPtz.setVisibility(View.VISIBLE);
        }

        mReplayTip.setOnClickListener(this);
        mLiveMode.setOnClickListener(this);
        mLivePtz.setOnClickListener(this);
        mLiveSound.setOnClickListener(this);
        mLiveUseLayout.setOnClickListener(this);
        mLiveScale.setOnClickListener(this);
        mLiveScreenshot.setOnClickListener(this);
        mLiveTalk.setOnClickListener(this);
        mLiveRecord.setOnClickListener(this);
        rlBaojing.setOnClickListener(this);
        rlCloudVideo.setOnClickListener(this);
        rlLocalVideo.setOnClickListener(this);
        rlPtz.setOnClickListener(this);
        initCloud();

        return mView;

    }

    /**
     * 加载云存储信息
     */
    private void initCloud() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(getContext()));
        map.put("deviceId", id);
        ViseUtil.Post(getContext(), NetUrl.getStorageDetailInfo, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                com.jiufang.wsyapp.utils.Logger.e("123123", s);
                Gson gson = new Gson();
                GetStorageDetailInfoBean storageDetailInfoBean = gson.fromJson(s, GetStorageDetailInfoBean.class);
                tvCloudNum.setText(storageDetailInfoBean.getData().getCloudCount()+"条");
                tvBendiNum.setText(storageDetailInfoBean.getData().getNativeCount()+"条");
                int isHaveCloud = storageDetailInfoBean.getData().getIsHaveCloud();
                if(isHaveCloud == 0){
                    tvIsHave.setText("(未开通)");
                    int day = storageDetailInfoBean.getData().getDays();
                    if(day == -1){
                        tvShengyu.setTextColor(Color.parseColor("#FFA16F"));
                        tvShengyu.setText("(已过期)");
                    }else if(day == 0){
                        tvShengyu.setTextColor(Color.parseColor("#FFA16F"));
                        tvShengyu.setText("(请在套餐页面开通服务)");
                    }else {
                        tvShengyu.setTextColor(Color.parseColor("#ffffff"));
                        tvShengyu.setText("(剩余"+day+"天)");
                    }
                }else {
                    tvIsHave.setText("(已开通)");
                    int day = storageDetailInfoBean.getData().getDays();
                    if(day == -1){
                        tvShengyu.setTextColor(Color.parseColor("#FFA16F"));
                        tvShengyu.setText("(已过期)");
                    }else {
                        tvShengyu.setTextColor(Color.parseColor("#ffffff"));
                        tvShengyu.setText("(剩余"+day+"天)");
                    }
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    /**
     * 描述：MediaPlayActivity创建完毕
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listener = new MyBaseWindowListener();
        mPlayWin.setWindowListener(listener);//为播放窗口设置监听
        mPlayWin.openTouchListener();//开启收拾监听

        // 开启横竖屏切换
        startListener();
        //开启原因：无对讲能力及设备给出提示
        if (bean != null) {
            if (bean.getData().getAudioTalk() == 1) {

            } else {
                mLiveTalk.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toast("该设备不支持语音对讲功能");
                    }
                });
            }
        }
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        play(0);
    }

    @Override
    public void onPause() {
        super.onPause();
        // 停止实时视频,无效资源号
        stop(-1);
        // 关闭语音对讲
        if (mOpenTalk == AudioTalkStatus.talk_open) {
            stopTalk();
        }
    }

    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        mPlayWin.uninitPlayWindow();// 销毁底层资源
    }

    /**
     * 描述：实现个性化界面
     *
     * @param mConfiguration
     */
    @Override
    protected void resetViews(Configuration mConfiguration) {
        super.resetViews(mConfiguration);
        if (mConfiguration.orientation == Configuration.ORIENTATION_LANDSCAPE) { // 横屏
            mLiveUseLayout.setVisibility(View.GONE);
            llBottom.setVisibility(View.GONE);
            mLiveScale.setTag("LANDSCAPE");
            mLiveScale.setImageResource(R.drawable.live_btn_smallscreen);
        } else if (mConfiguration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLiveUseLayout.setVisibility(View.VISIBLE);
            llBottom.setVisibility(View.VISIBLE);
            mLiveScale.setTag("PORTRAIT");
            mLiveScale.setImageResource(R.drawable.live_btn_fullscreen);
        }
    }

    class MyBaseWindowListener extends LCOpenSDK_EventListener {
        @Override
        public void onPlayerResult(int index, String code, int type) {
            Logger.d(TAG, "index : " + index + "  code : " + code + " type : " + type);
            if (type == Business.RESULT_SOURCE_OPENAPI) {
                if (mHander != null) {
                    mHander.post(new Runnable() {
                        public void run() {
                            if (isAdded()) {
                                stop(R.string.video_monitor_play_error);
                            }
                        }
                    });
                }
            } else {
                if (type == Business.RESULT_SOURCE_TYPE_DHHTTP) {
                    if (!(code.equals(Business.PlayerResultCode.DHHTTPCode.STATE_DHHTTP_OK) || code.equals(Business.PlayerResultCode.DHHTTPCode.STATE_MSG_HTTPDH_PASSWORD_SALT))) {
                        if (mHander != null) {
                            mHander.post(new Runnable() {
                                public void run() {
                                    if (isAdded()) {
                                        stop(R.string.video_monitor_play_error);
                                    }
                                }
                            });
                        }
                    }
                } else if (type == Business.RESULT_SOURCE_TYPE_RTSP) {
                    if (code.equals(Business.PlayerResultCode.STATE_PACKET_FRAME_ERROR)
                            || code.equals(Business.PlayerResultCode.STATE_RTSP_TEARDOWN_ERROR)
                            || code.equals(Business.PlayerResultCode.STATE_RTSP_AUTHORIZATION_FAIL)
                            || code.equals(Business.PlayerResultCode.STATE_RTSP_KEY_MISMATCH)) {
                        if (mHander != null) {
                            mHander.post(new Runnable() {
                                public void run() {
                                    if (isAdded()) {
                                        stop(R.string.video_monitor_play_error);
                                    }
                                }
                            });
                        }
                    }
                }

            }

        }

        @Override
        public void onPlayBegan(int index) {
            // TODO Auto-generated method stub
            // 显示码率
            // if (mHander != null) {
            // mHander.post(MediaPlayOnlineFragment.this);
            // }
            Logger.d(TAG, "index : " + index + "onPlayBegan --------");
            isPlaying = true;
            // 建立码流,自动开启音频
            if (mHander != null) {
                mHander.post(new Runnable() {
                    @Override
                    public void run() {
                        if (isAdded()) {
                            // showLoading(R.string.video_monitor_data_cache);
                            onClick(mLiveSound);
                        }
                    }
                });
            }
            // 关闭播放加载控件
            mProgressDialog.setStop();
        }

        // public void onReceiveData(int len) {
        // // 流量统计
        // mTotalFlow += len;
        // }
        @Override
        public void onStreamCallback(int index, byte[] data, int len) {
            // Log.d(TAG, "LCOpenSDK_EventListener::onStreamCallback-size : " +
            // len);
            try {
                String path = Environment.getExternalStorageDirectory()
                        .getPath() + "/streamCallback.ts";
                FileOutputStream fout = new FileOutputStream(path, true);
                fout.write(data);
                fout.close();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

        @Override
        public void onWindowLongPressBegin(int index, Direction dir, float dx,
                                           float dy) {
            sendCloudOrder(direction2Cloud(dir), true);
        }

        @Override
        public void onWindowLongPressEnd(int index) {
            sendCloudOrder(Cloud.stop, false);
        }

        // 电子缩放
        @Override
        public void onZooming(int index, float dScale) {
            if (IsPTZOpen == false) {
                mPlayWin.doScale(dScale);
            }

        }

        // 云台缩放
        @Override
        public void onZoomEnd(int index, ZoomType zoom) {
            Logger.d(TAG, "onZoomEnd" + zoom);
            if (IsPTZOpen == false) {
                return;
            }
            // TODO Auto-generated method stub
            sendCloudOrder(zoom == ZoomType.ZOOM_IN ? Cloud.zoomin
                    : Cloud.zoomout, false);
        }

        // 滑动开始
        @Override
        public boolean onSlipBegin(int index, Direction dir, float dx, float dy) {
            if (IsPTZOpen == false && mPlayWin.getScale() > 1) {
                Logger.d(TAG, "onflingBegin ");
            }
            sendCloudOrder(direction2Cloud(dir), false);
            return true;
        }

        // 滑动中
        @Override
        public void onSlipping(int index, Direction dir, float prex,
                               float prey, float dx, float dy) {
            if (IsPTZOpen == false && mPlayWin.getScale() > 1) {
                Logger.d(TAG, "onflingBegin onFlinging");
                mPlayWin.doTranslate(dx, dy);
            }
        }

        // 滑动结束
        @Override
        public void onSlipEnd(int index, Direction dir, float dx, float dy) {
            if (IsPTZOpen == false && mPlayWin.getScale() > 1) {
                Logger.d(TAG, "onflingBegin onFlingEnd");
                return;
            }

            // sendCloudOrder(Cloud.stop, false);
        }

        private Cloud direction2Cloud(Direction dir) {
            Cloud cloud = null;
            switch (dir) {
                case Up:
                    cloud = Cloud.up;
                    break;
                case Down:
                    cloud = Cloud.down;
                    break;
                case Left:
                    cloud = Cloud.left;
                    break;
                case Right:
                    cloud = Cloud.right;
                    break;
                case Left_up:
                    cloud = Cloud.leftUp;
                    break;
                case Left_down:
                    cloud = Cloud.leftDown;
                    break;
                case Right_up:
                    cloud = Cloud.rightUp;
                    break;
                case Right_down:
                    cloud = Cloud.RightDown;
                    break;
                default:
                    break;
            }
            return cloud;
        }

        public void onWindowDBClick(int index, float dx, float dy) {
            // TODO Auto-generated method stub
            switch (mLiveMenu.getVisibility()) {
                case View.GONE:
                    mLiveMenu.setVisibility(View.VISIBLE);
                    break;
                case View.VISIBLE:
                    mLiveMenu.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }

    }

    private void sendCloudOrder(final Cloud mCloud, final boolean longclick) {
        if (IsPTZOpen && isPlaying) {
            // super.sendCloudOrder(mCloud, longclick);
            Logger.d(TAG, "-----is longclick:" + longclick);
            ChannelPTZInfo.Operation operation = ChannelPTZInfo.Operation.Move;
            ChannelPTZInfo.Direction direction = null;
            switch (mCloud) {
                case up:
                    direction = ChannelPTZInfo.Direction.Up;
                    Logger.d(TAG, "-----Up");
                    break;
                case down:
                    direction = ChannelPTZInfo.Direction.Down;
                    Logger.d(TAG, "-----Down");
                    break;
                case left:// 手势向左， 摄像头向右转
                    direction = ChannelPTZInfo.Direction.Left;
                    Logger.d(TAG, "-----case left");
                    break;
                case right:
                    direction = ChannelPTZInfo.Direction.Right;
                    Logger.d(TAG, "-----case right");
                    break;
                case zoomin:
                    direction = ChannelPTZInfo.Direction.ZoomIn;
                    Logger.d(TAG, "-----ZoomIn");
                    break;
                case zoomout:
                    direction = ChannelPTZInfo.Direction.ZoomOut;
                    Logger.d(TAG, "-----ZoomOut");
                    break;
                case stop:
                    Logger.d(TAG, "-----stop");
                    operation = ChannelPTZInfo.Operation.Stop;
                    // direction = ChannelPTZInfo.Direction.Down;
                    break;
                default:
                    break;
            }
            ChannelPTZInfo ptzInfo = new ChannelPTZInfo(operation, direction);
            ptzInfo.setDuration(ChannelPTZInfo.Duration.Short);
            if (longclick) {
                ptzInfo.setDuration(ChannelPTZInfo.Duration.Long);
            }

//			Business.getInstance().AsynControlPtz(channelInfo.getUuid(),
//					ptzInfo, new Handler() {
//						@Override
//						public void handleMessage(Message msg) {
//							if (!isAdded() || getActivity() == null) {
//								Logger.d(TAG, "*******page not exits");
//								return;
//							}
//							// Logger.d(TAG,
//							// "-----control callback what:"+msg.what);
//							Business.RetObject retObject = (Business.RetObject) msg.obj;
//							if (msg.what == 0) {
//								Logger.d(TAG, "-----control result: true");
//							} else
//								toast(retObject.mMsg);
//						}
//					});
        }
    }

    /**
     * 描述：开始播放
     */
    public void play(int strRes) {
        // if (isPlaying) {
        Logger.e(TAG, "startplay: --------------");
        stop(-1);
        // }
        if (strRes > 0) {
            showLoading(strRes);
        } else {
            showLoading(R.string.common_loading);
        }
        com.jiufang.wsyapp.utils.Logger.e("123123", "token--" + bean.getData().getDeviceAccessToken() + "--deviceId--" + bean.getData().getSnCode()
                + "--channelId--" + bean.getData().getChannelId());
        mPlayWin.playRtspReal(
                bean.getData().getDeviceAccessToken(),
                bean.getData().getSnCode(),
                bean.getData().getSnCode(),
                Integer.valueOf(bean.getData().getChannelId()), bateMode, bean.getData().isIsOpt());//最后一个参数为true，表示使用长连接
    }

    /**
     * 描述：停止播放
     */
    public void stop(final int res) {
        // 关闭播放加载控件
        mProgressDialog.setStop();

        if (isRecord) {
            stopRecord();// 关闭录像
        }

        if (isOpenSound) {
            closeAudio();// 关闭音频
            isOpenSound = false;
            mLiveSound.setImageResource(R.drawable.live_btn_sound_off);
        }
        mPlayWin.stopRtspReal();// 关闭视频
        isPlaying = false;

        if (mHander != null) {
            mHander.post(new Runnable() {
                public void run() {
                    if (isAdded()) {
                        if (res > 0)
                            showErrorTip(res);
                    }
                }
            });
        }
    }

    /**
     * 描述：抓拍图像
     */
    public String capture() {
        String captureFilePath = null;
        // 判断SD卡是否已存在
        // SD卡容量检查
        // FIXME 检查设备是否在线
        // 抓图
        String channelName = null;
        if (bean != null) {
            channelName = bean.getData().getDeviceName();
        } else {
            channelName = "";
        }

        // 去除通道中在目录中的非法字符
        channelName = channelName.replace("-", "");

        captureFilePath = MediaPlayHelper.getCaptureAndVideoPath(
                MediaPlayHelper.DHFilesType.DHImage, channelName);
        int ret = mPlayWin.snapShot(captureFilePath);
        if (ret == retOK) {
            // 扫描到相册中
            MediaScannerConnection.scanFile(getActivity(),
                    new String[]{captureFilePath}, null, null);
            Toast.makeText(getActivity(),
                    "截图成功",
                    Toast.LENGTH_SHORT).show();
        } else {
            captureFilePath = null;
            Toast.makeText(getActivity(),
                    "截图失败",
                    Toast.LENGTH_SHORT).show();
        }
        return captureFilePath;
    }

    /**
     * 描述：开始录制
     */
    String path = null;

    public boolean startRecord() {
        if (!isPlaying) {
            toast("录制失败");
            return false;
        }
        // 判断SD卡是否已存在
        // SD卡容量检查

        // FIXME 要做 ~~~~检查设备是否在线

        // 录像的路径
        String channelName = null;
        if (bean != null) {
            channelName = bean.getData().getDeviceName();
        } else {
            channelName = "";
        }

        // 去除通道中在目录中的非法字符
        channelName = channelName.replace("-", "");

        path = MediaPlayHelper.getCaptureAndVideoPath(MediaPlayHelper.DHFilesType.DHVideo,
                channelName);
        int recordType = 1;
        long spaceRemain = 0x7FFFFFFF; //保留字段
        // 开始录制 1
        int ret = mPlayWin.startRecord(path, recordType, spaceRemain);
        if (ret != retOK) {
            toast("录制失败");
            return false;
        }

        return true;
    }

    /**
     * 关闭录像
     */
    public void stopRecord() {
        mPlayWin.stopRecord();
        isRecord = false;
        toast("录制成功");
        ivLuxiang.setImageResource(R.mipmap.play_luxiang);

        MediaScannerConnection.scanFile(getActivity(), new String[]{path},
                null, null);
    }

    /**
     * 打开声音
     */
    public boolean openAudio() {
        return mPlayWin.playAudio() == retOK;
    }

    /**
     * 关闭声音
     */
    public boolean closeAudio() {
        return mPlayWin.stopAudio() == retOK;
    }

    /**
     * 描述：开始对讲
     */
    public void startTalk() {
        if (!isPlaying) {
            toast("对讲打开失败");
            return;
        }

//        toastWithImg("开始对讲",
//                R.drawable.live_pic_talkback);
        toast("开始对讲");

        // 替换图片
        ivDuijiang.setImageResource(R.mipmap.play_duijiang_y);
        mOpenTalk = AudioTalkStatus.talk_opening;
        // 关闭扬声器 默认为关
        if (isOpenSound) {
            closeAudio();
            mLiveSound.setImageResource(R.drawable.live_btn_sound_off);
        }
        mLiveSound.setClickable(false);
        LCOpenSDK_Talk.setListener(new AudioTalkerListener());//对讲前先设备监听
        LCOpenSDK_Talk.playTalk(
                bean.getData().getDeviceAccessToken(),
                bean.getData().getSnCode(),
                bean.getData().getSnCode(), bean.getData().isIsOpt());//对讲使用长连接
    }

    /**
     * 描述：停止对讲
     */
    public void stopTalk() {
        toast("对讲关闭");
        // 替换图片
        ivDuijiang.setImageResource(R.mipmap.play_duijiang);
        LCOpenSDK_Talk.stopTalk();
        // 解决gc回收问题
        LCOpenSDK_Talk.setListener(null);//停止对讲后对讲监听置为空
        mOpenTalk = AudioTalkStatus.talk_close;
        // 开启扬声器
        if (isOpenSound && isPlaying) {
            openAudio();
            mLiveSound.setImageResource(R.drawable.live_btn_sound_on);
        }
        mLiveSound.setClickable(true);
    }

    class AudioTalkerListener extends LCOpenSDK_TalkerListener {
        /**
         * 描述：对讲状态获取
         */
        @Override
        public void onTalkResult(String error, int type) {
            // TODO Auto-generated method stub
            if (type == Business.RESULT_SOURCE_OPENAPI
                    || error.equals(AUDIO_TALK_ERROR)
                    || error.equals(Business.PlayerResultCode.STATE_PACKET_FRAME_ERROR)
                    || error.equals(Business.PlayerResultCode.STATE_RTSP_TEARDOWN_ERROR)
                    || error.equals(Business.PlayerResultCode.STATE_RTSP_AUTHORIZATION_FAIL)) {
                if (mHander != null) {
                    mHander.post(new Runnable() {
                        public void run() {
                            if (isAdded()) {
                                // 提示对讲打开失败
                                toast("对讲打开失败");
                                stopTalk();// 关闭播放
                            }
                        }
                    });
                }
            } else if (error.equals(Business.PlayerResultCode.STATE_RTSP_PLAY_READY)) {
                if (mHander != null) {
                    mHander.post(new Runnable() {
                        public void run() {
                            if (isAdded()) {
                                // 提示对讲打开成功
                                toast("对讲打开成功");
                            }
                        }
                    });
                }
                mOpenTalk = AudioTalkStatus.talk_open;
            }

        }

        @Override
        public void onTalkPlayReady() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAudioRecord(byte[] pData, int length, int audioFormat, int sampleRate, int sampleDepth) {
//	            super.onAudioRecord(pData, length, audioFormat, sampleRate, sampleDepth);
//	            BufferedOutputStream bufferedOutputStream;
//	            FileOutputStream fileOutputStream;
//	            String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/AudioFile.aac";
//	            File file = new File(filePath);
//	            if (file.exists()) {
//	                try {
//	                    fileOutputStream = new FileOutputStream(file, true);
//	                    bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//	                    try {
//	                        bufferedOutputStream.write(pData);
//	                        bufferedOutputStream.flush();
//	                        bufferedOutputStream.close();
//	                    } catch (IOException e) {
//	                        e.printStackTrace();
//	                    }
//	                } catch (FileNotFoundException e) {
//	                    e.printStackTrace();
//	                } finally {
//
//	                }
//	            }else{
//	                try {
//	                    file.createNewFile();
//	                } catch (IOException e) {
//	                    e.printStackTrace();
//	                }
//	            }
        }

        @Override
        public void onAudioReceive(byte[] pData, int length, int audioFormat, int sampleRate, int sampleDepth) {
            super.onAudioReceive(pData, length, audioFormat, sampleRate, sampleDepth);
        }

    }

    private void openPtzPopupWindow() {

        easyPopup = EasyPopup.create(getContext())
                .setContentView(getContext(), R.layout.realplay_ptz_wnd)
                .setWidth(RelativeLayout.LayoutParams.MATCH_PARENT)
                .setHeight(DensityTool.dp2px(getContext(), 300))
                .setFocusAndOutsideEnable(true)
                //允许背景变暗
                .setBackgroundDimEnable(true)
                //变暗的透明度(0-1)，0为完全透明
                .setDimValue(0.5f)
                //变暗的背景颜色
                .setDimColor(Color.BLACK)
                .apply();
        easyPopup.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        LinearLayout mPtzControlLy = easyPopup.findViewById(R.id.ptz_control_ly);
        ImageButton ibClose = easyPopup.findViewById(R.id.ptz_close_btn);
        ImageButton ibTop = easyPopup.findViewById(R.id.ptz_top_btn);
        ImageButton ibLeft = easyPopup.findViewById(R.id.ptz_left_btn);
        ImageButton ibRight = easyPopup.findViewById(R.id.ptz_right_btn);
        ImageButton ibBottom = easyPopup.findViewById(R.id.ptz_bottom_btn);

        ibClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                easyPopup.dismiss();
            }
        });

        ibTop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPtzControlLy.setBackgroundResource(R.drawable.ptz_up_sel);
                Map<String, String> map = new LinkedHashMap<>();
                map.put("userId", SpUtils.getUserId(getContext()));
                map.put("deviceId", id);
                map.put("operation", "0");
                map.put("duration", "1000");
                ViseUtil.Post(getContext(), NetUrl.controlLcDeviceMovePTZ, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        mPtzControlLy.setBackgroundResource(R.drawable.ptz_bg);
                        com.jiufang.wsyapp.utils.Logger.e("123123", s);
                    }

                    @Override
                    public void onElse(String s) {
                        com.jiufang.wsyapp.utils.Logger.e("123123", s);
                    }
                });
            }
        });

        ibLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPtzControlLy.setBackgroundResource(R.drawable.ptz_left_sel);
                Map<String, String> map = new LinkedHashMap<>();
                map.put("userId", SpUtils.getUserId(getContext()));
                map.put("deviceId", id);
                map.put("operation", "2");
                map.put("duration", "1000");
                ViseUtil.Post(getContext(), NetUrl.controlLcDeviceMovePTZ, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        mPtzControlLy.setBackgroundResource(R.drawable.ptz_bg);
                        com.jiufang.wsyapp.utils.Logger.e("123123", s);
                    }

                    @Override
                    public void onElse(String s) {
                        com.jiufang.wsyapp.utils.Logger.e("123123", s);
                    }
                });
            }
        });

        ibRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPtzControlLy.setBackgroundResource(R.drawable.ptz_right_sel);
                Map<String, String> map = new LinkedHashMap<>();
                map.put("userId", SpUtils.getUserId(getContext()));
                map.put("deviceId", id);
                map.put("operation", "3");
                map.put("duration", "1000");
                ViseUtil.Post(getContext(), NetUrl.controlLcDeviceMovePTZ, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        mPtzControlLy.setBackgroundResource(R.drawable.ptz_bg);
                        com.jiufang.wsyapp.utils.Logger.e("123123", s);
                    }

                    @Override
                    public void onElse(String s) {
                        com.jiufang.wsyapp.utils.Logger.e("123123", s);
                    }
                });
            }
        });

        ibBottom.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPtzControlLy.setBackgroundResource(R.drawable.ptz_bottom_sel);
                Map<String, String> map = new LinkedHashMap<>();
                map.put("userId", SpUtils.getUserId(getContext()));
                map.put("deviceId", id);
                map.put("operation", "1");
                map.put("duration", "1000");
                ViseUtil.Post(getContext(), NetUrl.controlLcDeviceMovePTZ, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        mPtzControlLy.setBackgroundResource(R.drawable.ptz_bg);
                        com.jiufang.wsyapp.utils.Logger.e("123123", s);
                    }

                    @Override
                    public void onElse(String s) {
                        com.jiufang.wsyapp.utils.Logger.e("123123", s);
                    }
                });
            }
        });

    }

    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_ptz:
                openPtzPopupWindow();
                break;
            case R.id.rl_local_video:
                intent.setClass(getContext(), LocalLcVideoActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("bean", bean);
                startActivity(intent);
                break;
            case R.id.rl_cloud_video:
                intent.setClass(getContext(), CloudLcVideoActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("bean", bean);
                startActivity(intent);
                break;
            case R.id.rl_baojing:
                //一键报警
                Map<String, String> map = new LinkedHashMap<>();
                map.put("bindDeviceId", id);
                ViseUtil.Post(getContext(), NetUrl.getBindDeviceUserInfo, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        com.jiufang.wsyapp.utils.Logger.e("123123", s);
                        Gson gson = new Gson();
                        GetBindDeviceUserInfoBean bean = gson.fromJson(s, GetBindDeviceUserInfoBean.class);
                        String areaName = bean.getData().getAreaName();
                        if(StringUtils.isEmpty(areaName)){
                            Intent intent1 = new Intent();
                            intent1.setClass(getContext(), AddDeviceAddressActivity.class);
                            intent1.putExtra("id", id);
                            intent1.putExtra("type", "1");
                            startActivity(intent1);
                        }else {
                            DialogBaojing dialogBaojing = new DialogBaojing(getContext(), "1", id, bean.getData().getPersonName(),
                                    bean.getData().getPersonPhone(), bean.getData().getAddress(), bean.getData().getHouseNumber(), new DialogBaojing.ClickListener() {
                                @Override
                                public void onClick() {
                                    DialogBaojingSuccess dialogBaojingSuccess = new DialogBaojingSuccess(getContext());
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
            case R.id.live_ptz:
                if (!isPlaying) {
                    return;
                }
//                if ((channelInfo.getAbility() & ChannelInfo.Ability.PTZ) != 0) {
//                    if (IsPTZOpen) {
//                        // 测试专用
//                        mPlayWin.setStreamCallback(0);
//                        IsPTZOpen = false;
//                        mLivePtz.setImageResource(R.drawable.live_btn_ptz_off);
//                    } else {
//                        // 测试专用
//                        mPlayWin.setStreamCallback(1);
//                        IsPTZOpen = true;
//                        mLivePtz.setImageResource(R.drawable.live_btn_ptz_on);
//                    }
//                } else {
//                    toast(R.string.toast_device_ability_no_ptz);
//                }
                break;
            case R.id.live_scale://全屏
                if ("LANDSCAPE".equals(mLiveScale.getTag())) {
                    mOrientation = ORIENTATION.isPortRait;
                    getActivity().setRequestedOrientation(
                            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else {
                    mOrientation = ORIENTATION.isLandScape;
                    getActivity().setRequestedOrientation(
                            ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
                break;
            case R.id.live_mode://清晰度切换
                // if(isPlaying) //播放是个异步的,多次点击会使停止播放顺序乱掉
                if (mOpenTalk == AudioTalkStatus.talk_opening) {
                    stopTalk();
                }
                // 高清切换到流畅
                if (bateMode == MediaMain) {
                    bateMode = MediaAssist;
                    mLiveMode.setImageResource(R.drawable.live_btn_fluent);
                    play(R.string.video_monitor_change_stream_normal);
                }// 流畅切换到高清
                else if (bateMode == MediaAssist) {
                    bateMode = MediaMain;
                    mLiveMode.setImageResource(R.drawable.live_btn_hd);
                    play(R.string.video_monitor_change_stream_hd);
                }
                break;
            case R.id.live_talk://对讲
                switch (mOpenTalk) {
                    case talk_open:
                    case talk_opening:
                        stopTalk();
                        break;
                    case talk_close:

                        openTalk();
//				startTalk();
                        break;
                    default:
                        break;
                }
                break;
            case R.id.live_sound://开启关闭声音
                if (mOpenTalk != AudioTalkStatus.talk_close || !isPlaying) {
                    toast(R.string.video_monitor_load_talk_sound_error);
                } else {
                    if (isOpenSound) {
                        boolean result = closeAudio();
                        if (result) {
                            mLiveSound
                                    .setImageResource(R.drawable.live_btn_sound_off);
                            toast(R.string.video_monitor_sound_close);
                            isOpenSound = false;
                        }
                    } else {
                        boolean result = openAudio();
                        if (result) {
                            mLiveSound
                                    .setImageResource(R.drawable.live_btn_sound_on);
                            toast(R.string.video_monitor_sound_open);
                            isOpenSound = true;
                        }
                    }
                }
                break;
            case R.id.live_screenshot://截屏
                capture();
                break;
            case R.id.live_record://录像
                if (!isRecord) {
                    boolean result = startRecord();
                    if (result) {
                        toast("开始录制");
                        isRecord = true;
                        ivLuxiang.setImageResource(R.mipmap.play_luxiang_y);
                    }
                } else {
                    stopRecord();
                }
                break;
            case R.id.live_play_pressed:
                play(-1);
                break;
            default:
                break;
        }
    }

    /**
     * 权限检查）
     */
    public void openTalk() {
        boolean isMinSDKM = Build.VERSION.SDK_INT < 23;
        boolean isGranted = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        if (isMinSDKM || isGranted) {
            startTalk();
            // 对讲权限
            return;
        }
        requestRecordAudioPermission();
    }

    private void requestRecordAudioPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                Manifest.permission.RECORD_AUDIO)) {

            Logger.d("Uriah", "Uriah + shouldShowRequestPermission true");
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    1);
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.RECORD_AUDIO},
                    1);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startTalk();

        } else {
            if (requestCode == 1) {
                toast(getString(R.string.toast_permission_record_audio_forbidden));
            }

        }
    }
}
