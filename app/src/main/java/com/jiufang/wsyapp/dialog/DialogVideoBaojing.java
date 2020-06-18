package com.jiufang.wsyapp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.jiufang.wsyapp.R;
import com.jiufang.wsyapp.net.NetUrl;
import com.jiufang.wsyapp.utils.SpUtils;
import com.jiufang.wsyapp.utils.ViseUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2020/6/1.
 */

public class DialogVideoBaojing extends Dialog {

    private Context context;
    private TextView tvCancel;
    private TextView tvSure;
    private ClickListener listener;

    private TextView tvName;
    private TextView tvPhone;
    private TextView tvAddress;

    private String name;
    private String phone;
    private String address;
    private String men;
    private String id;
    private String type;//1乐橙  2萤石
    private String startTime;
    private String endTime;
    private String playType;//true本地视频 false 云视频

    public DialogVideoBaojing(@NonNull Context context, String type, String playType, String startTime, String endTime, String id, String name, String phone, String address, String men, ClickListener listener) {
        super(context, R.style.RoundCornerDialog);
        this.context = context;
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.men = men;
        this.listener = listener;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.playType = playType;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        View view = LayoutInflater.from(context).inflate(R.layout.dialog_baojing, null);
        setContentView(view);

        tvCancel = view.findViewById(R.id.tv_cancel);
        tvSure = view.findViewById(R.id.tv_sure);
        tvName = view.findViewById(R.id.tv_name);
        tvPhone = view.findViewById(R.id.tv_phone);
        tvAddress = view.findViewById(R.id.tv_address);

        tvName.setText("联系人："+name);
        tvPhone.setText("手机号："+phone);
        tvAddress.setText("地址："+address+"-"+men);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type.equals("1")){

                }else {
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("deviceId", id);
                    map.put("isNative", playType);
                    map.put("userId", SpUtils.getUserId(context));
                    map.put("beginTime", startTime);
                    map.put("endTime", endTime);
                    ViseUtil.Post(context, NetUrl.createYsAlarm, map, new ViseUtil.ViseListener() {
                        @Override
                        public void onReturn(String s) {
                            listener.onClick();
                            dismiss();
                        }

                        @Override
                        public void onElse(String s) {

                        }
                    });
                }
            }
        });

    }

    public interface ClickListener{
        void onClick();
    }

}
