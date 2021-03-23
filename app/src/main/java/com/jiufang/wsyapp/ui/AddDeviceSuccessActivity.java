package com.jiufang.wsyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.base.BaseActivity;
import com.jiufang.wsyapp.dialog.DialogBohao;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.Logger;
import com.jiufang.wsyapp.utils.StatusBarUtils;
import com.jiufang.wsyapp.utils.StringUtils;
import com.jiufang.wsyapp.utils.ToastUtil;
import com.jiufang.wsyapp.utils.UtilsDevicePic;
import com.jiufang.wsyapp.utils.ViseUtil;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddDeviceSuccessActivity extends BaseActivity {

    private Context context = AddDeviceSuccessActivity.this;

    @BindView(R.id.iv_title)
    ImageView ivTitle;

    private String id = "";
    private String xinghao = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device_success);

        id = getIntent().getStringExtra("id");
        xinghao = getIntent().getStringExtra("xinghao");
        StatusBarUtils.setStatusBar(AddDeviceSuccessActivity.this, getResources().getColor(R.color.white_ffffff));
        ButterKnife.bind(AddDeviceSuccessActivity.this);
        initData();

    }

    private void initData() {

        ivTitle.setImageResource(UtilsDevicePic.getDevicePic(xinghao));

    }

    @OnClick({R.id.rl_back, R.id.btn_next, R.id.rl_right})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_next:
                intent.setClass(context, AddDeviceAddressActivity.class);
                intent.putExtra("id", id);
                intent.putExtra("type", "0");
                startActivity(intent);
                break;
            case R.id.rl_right:
                DialogBohao dialogBohao = new DialogBohao(context, new DialogBohao.ClickListener() {
                    @Override
                    public void onSure() {
                        ViseUtil.Post(context, NetUrl.getServiceNumber, null, new ViseUtil.ViseListener() {
                            @Override
                            public void onReturn(String s) {
                                Logger.e("123123", s);
                                try {
                                    JSONObject jsonObject = new JSONObject(s);
                                    String phone = jsonObject.optString("data");
                                    if(StringUtils.isEmpty(phone)){
                                        ToastUtil.showShort(context, "客服电话维护中，暂时无法拨号");
                                    }else {
                                        Intent intent1 = new Intent(Intent.ACTION_DIAL);
                                        Uri data = Uri.parse("tel:" + phone);
                                        intent1.setData(data);
                                        startActivity(intent1);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onElse(String s) {

                            }
                        });
                    }
                });
                dialogBohao.show();
                break;
        }
    }

}
