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
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.WeiboDialogUtils;

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

    private BasicCustomPushNotification notification;

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

        notification = new BasicCustomPushNotification();
        notification.setServerOptionFirst(true);
//        remindType = notification.getRemindType();
        if(notification.getRemindType() == BasicCustomPushNotification.REMIND_TYPE_VIBRATE_AND_SOUND){
            //声音+震动
            ivSound.setImageResource(R.mipmap.turn_on);
            ivVibrate.setImageResource(R.mipmap.turn_on);
        }else if(notification.getRemindType() == BasicCustomPushNotification.REMIND_TYPE_SOUND){
            //声音
            ivSound.setImageResource(R.mipmap.turn_on);
            ivVibrate.setImageResource(R.mipmap.turn_off);
        }else if(notification.getRemindType() == BasicCustomPushNotification.REMIND_TYPE_VIBRATE){
            //震动
            ivSound.setImageResource(R.mipmap.turn_off);
            ivVibrate.setImageResource(R.mipmap.turn_on);
        }else if(notification.getRemindType() == BasicCustomPushNotification.REMIND_TYPE_SILENT){
            //静默
            ivSound.setImageResource(R.mipmap.turn_off);
            ivVibrate.setImageResource(R.mipmap.turn_off);
        }

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

    @OnClick({R.id.rl_back, R.id.iv_push, R.id.iv_sound, R.id.iv_vibrate, R.id.rl_time})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_time:
                intent.setClass(context, MsgPushTimeActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_vibrate:
                if(notification.getRemindType() == BasicCustomPushNotification.REMIND_TYPE_VIBRATE_AND_SOUND){
                    //声音+震动
//                    BasicCustomPushNotification notification = new BasicCustomPushNotification();
                    notification.setRemindType(BasicCustomPushNotification.REMIND_TYPE_SOUND);
                    boolean res = CustomNotificationBuilder.getInstance().setCustomNotification(1, notification);
                    if(res){
                        ivVibrate.setImageResource(R.mipmap.turn_off);
//                        remindType = BasicCustomPushNotification.REMIND_TYPE_SOUND;
                    }
                }else if(notification.getRemindType() == BasicCustomPushNotification.REMIND_TYPE_SOUND){
                    //声音
//                    BasicCustomPushNotification notification = new BasicCustomPushNotification();
                    notification.setRemindType(BasicCustomPushNotification.REMIND_TYPE_VIBRATE_AND_SOUND);
                    boolean res = CustomNotificationBuilder.getInstance().setCustomNotification(2, notification);
//                    if(res) {
                        ivVibrate.setImageResource(R.mipmap.turn_on);
//                        remindType = BasicCustomPushNotification.REMIND_TYPE_VIBRATE_AND_SOUND;
//                    }
                }else if(notification.getRemindType() == BasicCustomPushNotification.REMIND_TYPE_VIBRATE){
                    //震动
//                    BasicCustomPushNotification notification = new BasicCustomPushNotification();
                    notification.setRemindType(BasicCustomPushNotification.REMIND_TYPE_SILENT);
                    boolean res = CustomNotificationBuilder.getInstance().setCustomNotification(3, notification);
//                    if(res) {
                        ivVibrate.setImageResource(R.mipmap.turn_off);
//                        remindType = BasicCustomPushNotification.REMIND_TYPE_SILENT;
//                    }
                }else if(notification.getRemindType() == BasicCustomPushNotification.REMIND_TYPE_SILENT){
                    //静默
//                    BasicCustomPushNotification notification = new BasicCustomPushNotification();
                    notification.setRemindType(BasicCustomPushNotification.REMIND_TYPE_VIBRATE);
                    boolean res = CustomNotificationBuilder.getInstance().setCustomNotification(4, notification);
//                    if(res) {
                        ivVibrate.setImageResource(R.mipmap.turn_on);
//                        remindType = BasicCustomPushNotification.REMIND_TYPE_VIBRATE;
//                    }
                }
                break;
            case R.id.iv_sound:
                if(notification.getRemindType() == BasicCustomPushNotification.REMIND_TYPE_VIBRATE_AND_SOUND){
                    //声音+震动
//                    BasicCustomPushNotification notification = new BasicCustomPushNotification();
                    notification.setRemindType(BasicCustomPushNotification.REMIND_TYPE_VIBRATE);
                    boolean res = CustomNotificationBuilder.getInstance().setCustomNotification(5, notification);
//                    if(res) {
                        ivSound.setImageResource(R.mipmap.turn_off);
//                        remindType = BasicCustomPushNotification.REMIND_TYPE_VIBRATE;
//                    }
                }else if(notification.getRemindType() == BasicCustomPushNotification.REMIND_TYPE_SOUND){
                    //声音
//                    BasicCustomPushNotification notification = new BasicCustomPushNotification();
                    notification.setRemindType(BasicCustomPushNotification.REMIND_TYPE_SILENT);
                    boolean res = CustomNotificationBuilder.getInstance().setCustomNotification(6, notification);
//                    if(res) {
                        ivSound.setImageResource(R.mipmap.turn_off);
//                        remindType = BasicCustomPushNotification.REMIND_TYPE_SILENT;
//                    }
                }else if(notification.getRemindType() == BasicCustomPushNotification.REMIND_TYPE_VIBRATE){
                    //震动
//                    BasicCustomPushNotification notification = new BasicCustomPushNotification();
                    notification.setRemindType(BasicCustomPushNotification.REMIND_TYPE_VIBRATE_AND_SOUND);
                    boolean res = CustomNotificationBuilder.getInstance().setCustomNotification(7, notification);
//                    if(res) {
                        ivSound.setImageResource(R.mipmap.turn_on);
//                        remindType = BasicCustomPushNotification.REMIND_TYPE_VIBRATE_AND_SOUND;
//                    }
                }else if(notification.getRemindType() == BasicCustomPushNotification.REMIND_TYPE_SILENT){
                    //静默
//                    BasicCustomPushNotification notification = new BasicCustomPushNotification();
                    notification.setRemindType(BasicCustomPushNotification.REMIND_TYPE_SOUND);
                    boolean res = CustomNotificationBuilder.getInstance().setCustomNotification(8, notification);
//                    if(res) {
                        ivSound.setImageResource(R.mipmap.turn_on);
//                        remindType = BasicCustomPushNotification.REMIND_TYPE_SOUND;
//                    }
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
