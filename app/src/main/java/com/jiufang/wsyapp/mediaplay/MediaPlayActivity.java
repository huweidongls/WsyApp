/*  
 * 项目名: YYS 
 * 文件名: MediaPlayActivity.java  
 * 版权声明:
 *      本系统的所有内容，包括源码、页面设计，文字、图像以及其他任何信息，
 *      如未经特殊说明，其版权均属大华技术股份有限公司所有。
 *      Copyright (c) 2015 大华技术股份有限公司
 *      版权所有
 */
package com.jiufang.wsyapp.mediaplay;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat.OnRequestPermissionsResultCallback;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.bean.GetBindDeviceDetailBean;
import com.jiufang.wsyapp.mediaplay.fragment.MediaPlayBackFragment;
import com.jiufang.wsyapp.mediaplay.fragment.MediaPlayFragment;
import com.jiufang.wsyapp.mediaplay.fragment.MediaPlayOnlineFragment;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.PermissionHelper;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 描述：视频播放Activity 作者： lc
 */
public class MediaPlayActivity extends FragmentActivity implements
        MediaPlayFragment.BackHandlerInterface,
        OnRequestPermissionsResultCallback {

    private Context context = MediaPlayActivity.this;

    private final static String tag = "MediaPlayActivity";
    private MediaPlayFragment mMediaPlayFragment;

    public static final int IS_VIDEO_ONLINE = 1;
    public static final int IS_VIDEO_REMOTE_RECORD = 2;
    public static final int IS_VIDEO_REMOTE_CLOUD_RECORD = 3;

    @BindView(R.id.rl_top)
    RelativeLayout rlTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_video);

        StatusBarUtils.setStatusBar(MediaPlayActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(MediaPlayActivity.this);

        PermissionHelper.requestPermission(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO});
        // 嵌入使用的帧
        MediaPlayFragment mediaPlayFragment; // 引用的布局帧
        Bundle b = new Bundle();
        String id; // 资源id号
        switch (getIntent().getIntExtra("TYPE", 0)) {
            case IS_VIDEO_ONLINE:
                mediaPlayFragment = new MediaPlayOnlineFragment();
                id = getIntent().getStringExtra("id");
                Map<String, String> map = new LinkedHashMap<>();
                map.put("bindDeviceId", id);
                map.put("userId", SpUtils.getUserId(context));
                ViseUtil.Post(context, NetUrl.getBindDeviceDetail, map, new ViseUtil.ViseListener() {
                    @Override
                    public void onReturn(String s) {
                        Logger.e("123123", s);
                        Gson gson = new Gson();
                        GetBindDeviceDetailBean bean = gson.fromJson(s, GetBindDeviceDetailBean.class);
                        b.putSerializable("bean", bean);
                        mediaPlayFragment.setArguments(b);
                        changeFragment(mediaPlayFragment, false);
                    }

                    @Override
                    public void onElse(String s) {

                    }
                });
//                b.putString("RESID", id);
//                mediaPlayFragment.setArguments(b);
//                changeFragment(mediaPlayFragment, false);
                break;
            case IS_VIDEO_REMOTE_RECORD:
                mediaPlayFragment = new MediaPlayBackFragment();
                id = getIntent().getStringExtra("ID");
                b.putString("RESID", id);
                mediaPlayFragment.setArguments(b);
                changeFragment(mediaPlayFragment, false);
                break;
            case IS_VIDEO_REMOTE_CLOUD_RECORD://云录像播放
                mediaPlayFragment = new MediaPlayBackFragment();
                id = getIntent().getStringExtra("ID");
                b.putString("RESID", id);
                mediaPlayFragment.setArguments(b);
                changeFragment(mediaPlayFragment, false);
                break;
            default:
                break;
        }

    }

    /**
     * boolean) 描述：切换fragment
     */
    public void changeFragment(Fragment targetFragment, boolean isAddToStack) {
        if (isAddToStack) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_content, targetFragment)
                    .addToBackStack(null).commitAllowingStateLoss();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_content, targetFragment)
                    .commitAllowingStateLoss();
        }

    }

    @Override
    public void onBackPressed() {
        if (mMediaPlayFragment == null || !mMediaPlayFragment.onBackPressed()) {
            Log.d(tag, "onBackPressed");
            super.onBackPressed();
        }
    }

    @Override
    public void setSelectedFragment(MediaPlayFragment backHandledFragment) {
        // TODO Auto-generated method stub
        this.mMediaPlayFragment = backHandledFragment;
    }

    // 横竖屏切换需要,隐藏标题栏
    public void toggleTitle(boolean isShow) {
        rlTop.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//		if (grantResults.length == 1
//				&& grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//		} else {
//			Toast.makeText(this, R.string.no_access_write_external_storage,
//					Toast.LENGTH_SHORT).show();
//		}

    }

}