package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetBindDeviceDetailBean;
import com.jiufang.wsyapp.mediaplay.Business;
import com.jiufang.wsyapp.mediaplay.fragment.MediaPlayOnlineFragment;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.lechange.opensdk.listener.LCOpenSDK_EventListener;
import com.lechange.opensdk.media.LCOpenSDK_PlayWindow;

import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LcPlayActivity extends BaseActivity {

    private Context context = LcPlayActivity.this;

    private String id = "";

    private GetBindDeviceDetailBean bean;
    protected LCOpenSDK_PlayWindow mPlayWin;
    protected LCOpenSDK_EventListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lc_play);

        listener = new MyBaseWindowListener();
        mPlayWin = new LCOpenSDK_PlayWindow();
        mPlayWin.initPlayWindow(LcPlayActivity.this, findViewById(R.id.live_window_content), 0);
        mPlayWin.setWindowListener(listener);//为播放窗口设置监听
//        mPlayWin.openTouchListener();//开启收拾监听

        id = getIntent().getStringExtra("id");
        StatusBarUtils.setStatusBar(LcPlayActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(LcPlayActivity.this);
        initData();

    }

    private void initData() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("bindDeviceId", id);
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getBindDeviceDetail, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                bean = gson.fromJson(s, GetBindDeviceDetailBean.class);
                play();
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    /**
     * 描述：开始播放
     */
    public void play() {
        com.jiufang.wsyapp.utils.Logger.e("123123", "token--"+bean.getData().getUserToken()+"--deviceId--"+bean.getData().getSnCode()
                +"--channelId--"+bean.getData().getChannelId());
        mPlayWin.playRtspReal(
                bean.getData().getUserToken(),
                bean.getData().getSnCode(),
                bean.getData().getSnCode(),
                Integer.valueOf(bean.getData().getChannelId()), bean.getData().getBateMode(), bean.getData().isIsOpt());//最后一个参数为true，表示使用长连接
//        mPlayWin.playRtspReal(
//                "Ut_00001e563c41000348b7856ef58770fd",
//                "5L09076PAJ513BD",
//                "5L09076PAJ513BD",
//                0, 1, true);//最后一个参数为true，表示使用长连接
    }

    class MyBaseWindowListener extends LCOpenSDK_EventListener {
        @Override
        public void onPlayerResult(int index, String code, int type) {
            com.lechange.common.log.Logger.d("123123", "index : " + index + "  code : " + code + " type : " + type);
//            if (type == Business.RESULT_SOURCE_OPENAPI) {
//                if (mHander != null) {
//                    mHander.post(new Runnable() {
//                        public void run() {
//                            if (isAdded()) {
//                                stop(R.string.video_monitor_play_error);
//                            }
//                        }
//                    });
//                }
//            } else {
//                if (type == Business.RESULT_SOURCE_TYPE_DHHTTP) {
//                    if (!(code.equals(Business.PlayerResultCode.DHHTTPCode.STATE_DHHTTP_OK) || code.equals(Business.PlayerResultCode.DHHTTPCode.STATE_MSG_HTTPDH_PASSWORD_SALT))) {
//                        if (mHander != null) {
//                            mHander.post(new Runnable() {
//                                public void run() {
//                                    if (isAdded()) {
//                                        stop(R.string.video_monitor_play_error);
//                                    }
//                                }
//                            });
//                        }
//                    }
//                } else if (type == Business.RESULT_SOURCE_TYPE_RTSP) {
//                    if (code.equals(Business.PlayerResultCode.STATE_PACKET_FRAME_ERROR)
//                            || code.equals(Business.PlayerResultCode.STATE_RTSP_TEARDOWN_ERROR)
//                            || code.equals(Business.PlayerResultCode.STATE_RTSP_AUTHORIZATION_FAIL)
//                            || code.equals(Business.PlayerResultCode.STATE_RTSP_KEY_MISMATCH)) {
//                        if (mHander != null) {
//                            mHander.post(new Runnable() {
//                                public void run() {
//                                    if (isAdded()) {
//                                        stop(R.string.video_monitor_play_error);
//                                    }
//                                }
//                            });
//                        }
//                    }
//                }
//
//            }

        }

        @Override
        public void onPlayBegan(int index) {
            // TODO Auto-generated method stub
            // 显示码率
            // if (mHander != null) {
            // mHander.post(MediaPlayOnlineFragment.this);
            // }
            com.lechange.common.log.Logger.d("123123", "index : " + index + "onPlayBegan --------");
//            isPlaying = true;
//            // 建立码流,自动开启音频
//            if (mHander != null) {
//                mHander.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (isAdded()) {
//                            // showLoading(R.string.video_monitor_data_cache);
//                            onClick(mLiveSound);
//                        }
//                    }
//                });
//            }
//            // 关闭播放加载控件
//            mProgressDialog.setStop();
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
//            sendCloudOrder(direction2Cloud(dir), true);
        }

        @Override
        public void onWindowLongPressEnd(int index) {
//            sendCloudOrder(MediaPlayOnlineFragment.Cloud.stop, false);
        }

        // 电子缩放
        @Override
        public void onZooming(int index, float dScale) {
//            if (IsPTZOpen == false) {
//                mPlayWin.doScale(dScale);
//            }

        }

        // 云台缩放
        @Override
        public void onZoomEnd(int index, ZoomType zoom) {
            com.lechange.common.log.Logger.d("123123", "onZoomEnd" + zoom);
//            if (IsPTZOpen == false) {
//                return;
//            }
//            // TODO Auto-generated method stub
//            sendCloudOrder(zoom == ZoomType.ZOOM_IN ? MediaPlayOnlineFragment.Cloud.zoomin
//                    : MediaPlayOnlineFragment.Cloud.zoomout, false);
        }

        // 滑动开始
        @Override
        public boolean onSlipBegin(int index, Direction dir, float dx, float dy) {
//            if (IsPTZOpen == false && mPlayWin.getScale() > 1) {
//                com.lechange.common.log.Logger.d(TAG, "onflingBegin ");
//            }
//            sendCloudOrder(direction2Cloud(dir), false);
            return true;
        }

        // 滑动中
        @Override
        public void onSlipping(int index, Direction dir, float prex,
                               float prey, float dx, float dy) {
//            if (IsPTZOpen == false && mPlayWin.getScale() > 1) {
//                com.lechange.common.log.Logger.d(TAG, "onflingBegin onFlinging");
//                mPlayWin.doTranslate(dx, dy);
//            }
        }

        // 滑动结束
        @Override
        public void onSlipEnd(int index, Direction dir, float dx, float dy) {
//            if (IsPTZOpen == false && mPlayWin.getScale() > 1) {
//                com.lechange.common.log.Logger.d(TAG, "onflingBegin onFlingEnd");
//                return;
//            }

            // sendCloudOrder(Cloud.stop, false);
        }
    }

    /**
     * 描述：停止播放
     */
    public void stop() {
        mPlayWin.stopRtspReal();// 关闭视频
    }

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

    private void jiebang() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("bindDeviceId", id);
        ViseUtil.Post(context, NetUrl.bindLcDeviceLive, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
            }

            @Override
            public void onElse(String s) {

            }
        });

    }

}
