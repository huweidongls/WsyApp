package com.jiufang.wsyapp.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.sdk.android.push.CommonCallback;
import com.alibaba.sdk.android.push.noonesdk.PushServiceFactory;
import com.alibaba.sdk.android.push.notification.BasicCustomPushNotification;
import com.alibaba.sdk.android.push.notification.CustomNotificationBuilder;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ViseUtil;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MsgPushActivity extends BaseActivity {

    private Context context = MsgPushActivity.this;

    @BindView(R.id.iv_push)
    ImageView ivPush;
    @BindView(R.id.iv_sound)
    ImageView ivSound;
    @BindView(R.id.iv_vibrate)
    ImageView ivVibrate;
    @BindView(R.id.tv_time)
    TextView tvTime;

    private String pushType = "";
//    private int remindType = 0;

    private Dialog dialog;

    private String type = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_push);

        StatusBarUtils.setStatusBar(MsgPushActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(MsgPushActivity.this);
        initData();

    }

    @Override
    protected void onStart() {
        super.onStart();
        tvTime.setText(SpUtils.getPushTime(context));
    }

    private void initData() {

        Map<String, String> map = new HashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getUserNotifyType, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    type = jsonObject.optString("data");
//                    if(type.equals("notifyType=BOTH")){
//                        //声音+震动
//                        ivSound.setImageResource(R.mipmap.turn_on);
//                        ivVibrate.setImageResource(R.mipmap.turn_on);
//                    }else if(type.equals("notifyType=SOUND")){
//                        //声音
//                        ivSound.setImageResource(R.mipmap.turn_on);
//                        ivVibrate.setImageResource(R.mipmap.turn_off);
//                    }else if(type.equals("notifyType=VIBRATE")){
//                        //震动
//                        ivSound.setImageResource(R.mipmap.turn_off);
//                        ivVibrate.setImageResource(R.mipmap.turn_on);
//                    }else if(type.equals("notifyType=NONE")){
//                        //静默
//                        ivSound.setImageResource(R.mipmap.turn_off);
//                        ivVibrate.setImageResource(R.mipmap.turn_off);
//                    }
                    if(type.equals("BOTH")){
                        //声音+震动
                        ivSound.setImageResource(R.mipmap.turn_on);
                        ivVibrate.setImageResource(R.mipmap.turn_on);
                    }else if(type.equals("SOUND")){
                        //声音
                        ivSound.setImageResource(R.mipmap.turn_on);
                        ivVibrate.setImageResource(R.mipmap.turn_off);
                    }else if(type.equals("VIBRATE")){
                        //震动
                        ivSound.setImageResource(R.mipmap.turn_off);
                        ivVibrate.setImageResource(R.mipmap.turn_on);
                    }else if(type.equals("NONE")){
                        //静默
                        ivSound.setImageResource(R.mipmap.turn_off);
                        ivVibrate.setImageResource(R.mipmap.turn_off);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onElse(String s) {

            }
        });

        PushServiceFactory.getCloudPushService().checkPushChannelStatus(new CommonCallback() {
            @Override
            public void onSuccess(String s) {
                if(s.equals("on")){
                    pushType = "on";
                    ivPush.setImageResource(R.mipmap.turn_on);
                }else if(s.equals("off")){
                    pushType = "off";
                    ivPush.setImageResource(R.mipmap.turn_off);
                }
            }

            @Override
            public void onFailed(String s, String s1) {

            }
        });

    }

    private void setType(String type){

        dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
        Map<String, String> map = new HashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        map.put("notifyType", type);
        ViseUtil.Post(context, NetUrl.setUserNotifyType, map, dialog, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {

            }

            @Override
            public void onElse(String s) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.iv_push, R.id.iv_sound, R.id.iv_vibrate, R.id.rl_time})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_time:
                intent.setClass(context, MsgPushTimeActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_vibrate:
                if(type.equals("BOTH")){
                    //声音+震动
                    setType("SOUND");
                    ivVibrate.setImageResource(R.mipmap.turn_off);
                    type = "SOUND";
                }else if(type.equals("SOUND")){
                    //声音
                    setType("BOTH");
                    ivVibrate.setImageResource(R.mipmap.turn_on);
                    type = "BOTH";
                }else if(type.equals("VIBRATE")){
                    //震动
                    setType("NONE");
                    ivVibrate.setImageResource(R.mipmap.turn_off);
                    type = "NONE";
                }else if(type.equals("NONE")){
                    //静默
                    setType("VIBRATE");
                    ivVibrate.setImageResource(R.mipmap.turn_on);
                    type = "VIBRATE";
                }
                break;
            case R.id.iv_sound:
                if(type.equals("BOTH")){
                    //声音+震动
                    setType("VIBRATE");
                    ivSound.setImageResource(R.mipmap.turn_off);
                    type = "VIBRATE";
                }else if(type.equals("SOUND")){
                    //声音
                    setType("NONE");
                    ivSound.setImageResource(R.mipmap.turn_off);
                    type = "NONE";
                }else if(type.equals("VIBRATE")){
                    //震动
                    setType("BOTH");
                    ivSound.setImageResource(R.mipmap.turn_on);
                    type = "BOTH";
                }else if(type.equals("NONE")){
                    //静默
                    setType("SOUND");
                    ivSound.setImageResource(R.mipmap.turn_on);
                    type = "SOUND";
                }
                break;
            case R.id.iv_push:
                if(pushType.equals("on")){
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    PushServiceFactory.getCloudPushService().turnOffPushChannel(new CommonCallback() {
                        @Override
                        public void onSuccess(String s) {
                            Logger.e("123123", s);
                            pushType = "off";
                            ivPush.setImageResource(R.mipmap.turn_off);
                            WeiboDialogUtils.closeDialog(dialog);
                        }

                        @Override
                        public void onFailed(String s, String s1) {
                            WeiboDialogUtils.closeDialog(dialog);
                        }
                    });
                }else if(pushType.equals("off")){
                    dialog = WeiboDialogUtils.createLoadingDialog(context, "请等待...");
                    PushServiceFactory.getCloudPushService().turnOnPushChannel(new CommonCallback() {
                        @Override
                        public void onSuccess(String s) {
                            Logger.e("123123", s);
                            pushType = "on";
                            ivPush.setImageResource(R.mipmap.turn_on);
                            WeiboDialogUtils.closeDialog(dialog);
                        }

                        @Override
                        public void onFailed(String s, String s1) {
                            WeiboDialogUtils.closeDialog(dialog);
                        }
                    });
                }
                break;
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
