package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.bean.GetAndroidUpdateInfoBean;
import com.jiufang.wsyapp.dialog.DialogExit;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.VersionUtils;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {

    private Context context = SetActivity.this;

    @BindView(R.id.tv_version)
    TextView tvVersion;

    private String phone = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        phone = getIntent().getStringExtra("phone");
        StatusBarUtils.setStatusBar(SetActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(SetActivity.this);
        initData();

    }

    private void initData() {

        tvVersion.setText("V "+ VersionUtils.packageName(context));

    }

    @OnClick({R.id.rl_back, R.id.btn_exit, R.id.rl_safe, R.id.rl_check_version, R.id.rl_msg, R.id.rl_yhxy, R.id.rl_yszc})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_yszc:
                intent.setClass(context, YinsizhengceActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_yhxy:
                intent.setClass(context, YonghuxieyiActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_msg:
                intent.setClass(context, MsgPushActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_check_version:
                checkVersion();
                break;
            case R.id.rl_safe:
                intent.setClass(context, ZhanghaoSafeActivity.class);
                intent.putExtra("phone", phone);
                startActivity(intent);
                break;
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_exit:
                DialogExit dialogExit = new DialogExit(context, new DialogExit.ClickListener() {
                    @Override
                    public void onSure() {
                        Map<String, String> map = new LinkedHashMap<>();
                        map.put("userId", SpUtils.getUserId(context));
                        ViseUtil.Post(context, NetUrl.logout, map, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                SpUtils.clear(context);
                                Intent intent = new Intent();
                                intent.setClass(context, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }

                            @Override
                            public void onElse(String s) {

                            }
                        });
                    }
                });
                dialogExit.show();
                break;
        }
    }

    /**
     * 检查是否有新版本
     */
    private void checkVersion() {

        Map<String, String> map = new LinkedHashMap<>();
        map.put("userId", SpUtils.getUserId(context));
        ViseUtil.Post(context, NetUrl.getAndroidUpdateInfo, map, new ViseUtil.ViseListener() {
            @Override
            public void onReturn(String s) {
                Logger.e("123123", s);
                Gson gson = new Gson();
                GetAndroidUpdateInfoBean bean = gson.fromJson(s, GetAndroidUpdateInfoBean.class);
                int versionCode = VersionUtils.packageCode(context);
                if(bean.getData().getCode()>versionCode){
                    ToastUtil.showShort(context, "当前有新版本");
                }else {
                    ToastUtil.showShort(context, "当前为最新版本");
                }
            }

            @Override
            public void onElse(String s) {
                Logger.e("123123", s);
            }
        });

    }

}
